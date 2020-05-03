package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.common.dto.program.ProgramSessionDto;
import com.sg.eirp.program.mapper.ProgramMapper;
import com.sg.eirp.program.model.ProgramSession;
import com.sg.eirp.program.util.CommonConstants;
import com.sg.eirp.program.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Program;
import com.sg.eirp.program.repo.ProgramRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProgramServiceImpl implements ProgramService, CommonConstants {

	private Logger logger = LoggerFactory.getLogger(ProgramServiceImpl.class);

	@Autowired
	private ProgramRepo programRepo;

	@Autowired
	private ProgramMapper programMapper;

	@Autowired
	private ProgramSessionService programSessionService;

	@Autowired
	private DocumentService documentService;
	
	@Override
	public ProgramDto save(ProgramDto programDto) {

		if (programDto == null) {
			return null;
		}

		List<ProgramSessionDto> programSessionDtoList = programDto.getSessions();
		List<DocumentDto> documentDtoList = programDto.getProgramDocuments();

		logger.info("programDto: " + CommonUtil.convertObjectToJSON(programDto));

		// convert programDto to program
		Program program = programMapper.dtoToEntity(programDto);
		logger.info("program: " + CommonUtil.convertObjectToJSON(program));

		// save program to database
		program = programRepo.save(program);

		// get programId;
		UUID programId = null;
		if (program != null) {
			programId = program.getId();
		}
		logger.info("programId: " + (programId == null ? "null" : programId.toString()));

		// convert program to programDto
		programDto = programMapper.entityToDto(program);
		logger.info("program: " + CommonUtil.convertObjectToJSON(programDto));

		// save program session to database
		if (programSessionDtoList != null) {
			programSessionDtoList = programSessionService.saveAll(programSessionDtoList, programId);
			programDto.setSessions(programSessionDtoList);
		}

		// save program documents to database
		if (documentDtoList != null) {
			documentDtoList = documentService.saveAll(documentDtoList, DOCUMENT_TABLE, programId);
			programDto.setProgramDocuments(documentDtoList);
		}

		return programDto;
	}

	@Override
	public Optional<List<ProgramDto>> getAllPrograms() {
		Iterable<Program> programIterable = programRepo.findAll();
		
		// convert the iterable to list
		List<Program> programList = StreamSupport.stream(programIterable.spliterator(), false)
				.collect(Collectors.toList());

        List<ProgramDto> programDtoList = programMapper.entitiesToDtos(Optional.ofNullable(programList)).get();

        if (programDtoList != null) {
			programDtoList = programDtoList.stream()
					.map(dto -> getProgramDtoDetails(dto))
					.collect(Collectors.toList());
		}

        return Optional.ofNullable(programDtoList);
	}

	@Override
	public ProgramDto getByProgramId(UUID programId) {
		logger.info("programId: " + programId.toString());

		// get program dto
		Optional<Program> programOptional = programRepo.getById(programId);

		ProgramDto dto = null;
		if (programOptional.isPresent()) {
			dto = programMapper.entityToDto(programOptional.get());
		}

		return getProgramDtoDetails(dto);
	}

	private ProgramDto getProgramDtoDetails(ProgramDto dto) {
		if (dto == null) {
			return null;
		}

		String id = dto.getProgramId();
		if (id == null) {
			return null;
		}

		UUID programId = UUID.fromString(id);

		// get program sessions dto
		Optional<List<ProgramSessionDto>> programSessionDtoOptional = programSessionService.getByProgramId(programId);
		if (programSessionDtoOptional.isPresent()) {
			dto.setSessions(programSessionDtoOptional.get());
		}

		// get document dto
		Optional<List<DocumentDto>> documentDtoOptional = documentService.getDocumentsByReference(DOCUMENT_TABLE, programId);
		if (documentDtoOptional.isPresent()) {
			dto.setProgramDocuments(documentDtoOptional.get());
		}

		return dto;
	}
}
