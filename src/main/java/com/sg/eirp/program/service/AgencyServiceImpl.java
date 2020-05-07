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
import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;
import com.sg.eirp.common.dto.common.BulletinDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.common.exceptions.BusinessValidationException;
import com.sg.eirp.program.mapper.AgencyDetailsMapper;
import com.sg.eirp.program.mapper.AgencyMapper;
import com.sg.eirp.program.util.CommonConstants;
import com.sg.eirp.program.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.repo.AgencyRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyServiceImpl implements AgencyService, CommonConstants {

	private static Logger logger = LoggerFactory.getLogger(AgencyServiceImpl.class);

	@Autowired
	private AgencyRepo agencyRepo;

	@Autowired
	private AgencyMapper agencyMapper;

	@Autowired
	private AgencyDetailsMapper agencyDetailsMapper;

	@Autowired
	private AddressService addressService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private BulletinService bulletinService;

	@Autowired
	private AgencyBranchService agencyBranchService;

	@Autowired
	private AgencyLeadershipService agencyLeadershipService;

	@Autowired
	private ProgramService programService;
	
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
	public Optional<List<TutorAgencyBranchDto>> saveTutorAgencyBranchAll(List<TutorAgencyBranchDto> branchDtoList) {
		if (branchDtoList == null || branchDtoList.isEmpty()) {
			return null;
		}

		// get agency id, agency id must be valid
		UUID agencyId = CommonUtil.convertIdtoUUID(branchDtoList.get(0).getAgencyId());
		if (agencyId == null) {
			throw new BusinessValidationException();
		}

		// get Agency by agency id, agency must be valid
		Agency agency = null;
		Optional<Agency> agencyOptional = agencyRepo.findById(agencyId);
		if (agencyOptional.isPresent()) {
			agency = agencyOptional.get();
		} else {
			throw new BusinessValidationException();
		}

		return agencyBranchService.saveAll(branchDtoList);
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
	public TutorAgencyDetailDto getDetailsByAgencyId(UUID agencyId) {
		// get Agency
		Agency agency = null;
		Optional<Agency> agencyOptional = agencyRepo.findById(agencyId);
		if (agencyOptional.isPresent()) {
			agency = agencyOptional.get();
		}
		TutorAgencyDetailDto dto = agencyDetailsMapper.entityToDto(agency);

		// get Agency Programs
		Optional<List<ProgramDto>> programDtoListOptional = programService.getProgramsByAgencyId(agencyId);
		List<ProgramDto> programDtoList = null;
		if (programDtoListOptional.isPresent()) {
			programDtoList = programDtoListOptional.get();
		}

		if (dto != null) {
			dto.setPrograms(programDtoList);
		}

		logger.info("###### get agency branch");
		// get Agency Branches
		Optional<List<TutorAgencyBranchDto>> agencyBranchDtoListOptional = agencyBranchService.getBranchesByAgencyId(agencyId);
		List<TutorAgencyBranchDto> agencyBranchDtoList = null;
		if (agencyBranchDtoListOptional.isPresent()) {
			agencyBranchDtoList = agencyBranchDtoListOptional.get();
			logger.info("###### agency branch: " + agencyBranchDtoList.size());
		}

		if (dto != null) {
			dto.setBranches(agencyBranchDtoList);
		}

		// get Agency Leaderships
		Optional<List<TutorAgencyLeadershipDto>> tutorAgencyLeadershipDtoListOptional =  agencyLeadershipService.getByAgencyId(agencyId);
		List<TutorAgencyLeadershipDto> tutorAgencyLeadershipDtoList = null;
		if (tutorAgencyLeadershipDtoListOptional.isPresent()) {
			tutorAgencyLeadershipDtoList = tutorAgencyLeadershipDtoListOptional.get();
		}

		if (dto != null) {
			dto.setLeadershipTeam(tutorAgencyLeadershipDtoList);
		}

		// get Agency Bulletins
		Optional<List<BulletinDto>> bulletinDtoListOptional = bulletinService.getByReference(CommonConstants.AGENCY_TABLE, agencyId);
		List<BulletinDto> bulletinDtoList = null;
		if (bulletinDtoListOptional.isPresent()) {
			bulletinDtoList = bulletinDtoListOptional.get();
		}

		if (dto != null) {
			dto.setWhatsUpNews(bulletinDtoList);
		}

		// get Agency Documents
		return (TutorAgencyDetailDto) getTutorAgencyDocuments((TutorAgencyDto) dto);
	}

	@Override
	public TutorAgencyDetailDto updateDetails(TutorAgencyDetailDto dto) {
		if (dto == null) {
			logger.debug("Tutor agency dto cannot be null in order to update details.");
			throw new BusinessValidationException();
		}

		UUID uuid = CommonUtil.convertIdtoUUID(dto.getId());
		if (uuid == null) {
			logger.debug("Tutor agency dto id cannot be null in order to update details.");
			throw new BusinessValidationException();
		}

		if (!agencyRepo.findById(uuid).isPresent()) {
			logger.debug("Tutor agency needs to be valid in order to update details.");
		}

		// update tutor agency;
		Agency agency = agencyRepo.save(agencyDetailsMapper.dtoToEntity(dto));

		// update agency hq address;
		AddressDto hqAddressDto = addressService.save(dto.getHqAddress());

		// update tutor agency branch
		List<TutorAgencyBranchDto> branchDtoList = dto.getBranches();
		List<TutorAgencyBranchDto> newBranchDtoList = new ArrayList<>();
		if (agency != null && branchDtoList != null) {
			for (TutorAgencyBranchDto branchDto : branchDtoList) {
				newBranchDtoList.add(agencyBranchService.save(branchDto));
			}
		}

		dto = agencyDetailsMapper.entityToDto(agency);
		if (dto != null) {
			dto.setBranches(newBranchDtoList);
		}

		//

		return dto;
	}

	private TutorAgencyDto getTutorAgencyDocuments(TutorAgencyDto dto) {
		if (dto == null) {
			logger.debug("Tutor agency dto cannot be null.");
			throw new BusinessValidationException();
		}

		UUID uuid = CommonUtil.convertIdtoUUID(dto.getId());
		if (uuid == null) {
			logger.debug("Tutor agency dto id cannot be null.");
			throw new BusinessValidationException();
		}

		// get document dto
		Optional<List<DocumentDto>> documentDtoOptional = documentService.getDocumentsByReference(DOCUMENT_TABLE, uuid);
		if (documentDtoOptional.isPresent()) {
			dto.setTutorAgencyDocuments(documentDtoOptional.get());
		}

		return dto;
	}

}
