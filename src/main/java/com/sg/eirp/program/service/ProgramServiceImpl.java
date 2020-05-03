package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.program.mapper.ProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Program;
import com.sg.eirp.program.repo.ProgramRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramRepo programRepo;

	@Autowired
	private ProgramMapper programMapper;
	
	@Override
	public ProgramDto save(ProgramDto programDto) {
		if (programDto != null) {
			programDto = programMapper.entityToDto(programRepo.save(programMapper.dtoToEntity(programDto)));
		}

		return programDto;
	}

	@Override
	public Optional<List<ProgramDto>> getAllPrograms() {
		Iterable<Program> programIterable = programRepo.findAll();
		
		// convert the iterable to list
        List<Program> programList = StreamSupport.stream(programIterable.spliterator(), false)
        								.collect(Collectors.toList());

        return programMapper.entitiesToDtos(Optional.ofNullable(programList));
	}

	@Override
	public ProgramDto getByProgramId(UUID programId) {
		Program program = null;
		programRepo.findById(programId).ifPresent(program::equals);
		return programMapper.entityToDto(program);
	}
}
