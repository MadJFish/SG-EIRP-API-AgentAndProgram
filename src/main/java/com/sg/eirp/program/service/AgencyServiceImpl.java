package com.sg.eirp.program.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDetailDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.common.dto.program.ProgramSessionDto;
import com.sg.eirp.program.mapper.AgencyDetailsMapper;
import com.sg.eirp.program.mapper.AgencyMapper;
import com.sg.eirp.program.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.repo.AgencyRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyServiceImpl implements AgencyService, CommonConstants {

	@Autowired
	private AgencyRepo agencyRepo;

	@Autowired
	AgencyMapper agencyMapper;

	@Autowired
	AgencyDetailsMapper agencyDetailsMapper;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private AgencyBranchService agencyBranchService;
	
	@Override
	public TutorAgencyDto save(TutorAgencyDto tutorAgencyDto) {
		if (tutorAgencyDto == null) {
			return null;
		}

		List<DocumentDto> documentDtoList = tutorAgencyDto.getTutorAgencyDocuments();

		Agency agency = agencyRepo.save(agencyMapper.dtoToEntity(tutorAgencyDto));

		// get agencyId;
		UUID agencyId = null;
		if (agency != null) {
			agencyId = agency.getId();
		}

		tutorAgencyDto = agencyMapper.entityToDto(agency);

		// save tutor agency documents to database
		if (documentDtoList != null) {
			documentDtoList = documentService.saveAll(documentDtoList, DOCUMENT_TABLE, agencyId);
			tutorAgencyDto.setTutorAgencyDocuments(documentDtoList);
		}

		return tutorAgencyDto;
	}

	@Override
	public Optional<List<TutorAgencyDto>> getAllAgencies() {
		Iterable<Agency> agencyIterable = agencyRepo.findAll();
		
		// convert the iterable to list
        List<Agency> agencyList = StreamSupport.stream(agencyIterable.spliterator(), false)
        								.collect(Collectors.toList());

		Optional<List<TutorAgencyDto>> optional = agencyMapper.entitiesToDtos(Optional.ofNullable(agencyList));

		List<TutorAgencyDto> resultList = new ArrayList<>();
		if (optional.isPresent()) {
			resultList = optional.get()
							.stream()
							.map(dto -> getTutorAgencyDocuments(dto))
							.collect(Collectors.toList());
		}
		return Optional.ofNullable(resultList);
	}

	@Override
	public Optional<List<TutorAgencyDto>> getAgenciesByFeatured(boolean featured) {
		List<TutorAgencyDto> list = new ArrayList<>();
		Optional<List<TutorAgencyDto>> optional = getAllAgencies();
		if (optional.isPresent()) {
			list = optional.get()
				.stream()
				.filter(dto -> dto.getFeatured() == featured)
				.collect(Collectors.toList());
		}
		return Optional.ofNullable(list);
	}

	@Override
	public TutorAgencyDto getByAgencyId(UUID agencyId) {
		Agency agency = null;
		Optional<Agency> agencyOptional = agencyRepo.findById(agencyId);

		if (agencyOptional.isPresent()) {
			agency = agencyOptional.get();
		}

		TutorAgencyDto dto = agencyMapper.entityToDto(agency);

		return getTutorAgencyDocuments(dto);
	}

	@Override
	public TutorAgencyDetailDto updateDetails(TutorAgencyDetailDto dto) {
		if (dto == null) {
			return null;
		}

		String id = dto.getId();
		if (id == null) {
			return null;
		}

		UUID uuid = null;
		try {
			uuid = UUID.fromString(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Agency agency = null;
		if (uuid != null && agencyRepo.findById(uuid).isPresent()){
			agency = agencyRepo.save(agencyDetailsMapper.dtoToEntity(dto));
		}

		List<TutorAgencyBranchDto> branchDtoList = dto.getBranches();
		List<TutorAgencyBranchDto> newBranchDtoList = new ArrayList<>();
		if (agency != null && branchDtoList != null) {
			for (TutorAgencyBranchDto branchDto : branchDtoList) {
				newBranchDtoList.add(agencyBranchService.save(branchDto, agency));
			}
		}

		dto = agencyDetailsMapper.entityToDto(agency);
		if (dto != null) {
			dto.setBranches(newBranchDtoList);
		}

		return dto;
	}

	private TutorAgencyDto getTutorAgencyDocuments(TutorAgencyDto dto) {
		if (dto == null) {
			return null;
		}

		String id = dto.getId();
		if (id == null) {
			return null;
		}

		UUID uuid = UUID.fromString(id);

		// get document dto
		Optional<List<DocumentDto>> documentDtoOptional = documentService.getDocumentsByReference(DOCUMENT_TABLE, uuid);
		if (documentDtoOptional.isPresent()) {
			dto.setTutorAgencyDocuments(documentDtoOptional.get());
		}

		return dto;
	}

}
