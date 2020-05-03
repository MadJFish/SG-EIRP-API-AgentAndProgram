package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.program.mapper.AgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.repo.AgencyRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyRepo agencyRepo;

	@Autowired
	AgencyMapper agencyMapper;
	
	@Override
	public TutorAgencyDto save(TutorAgencyDto tutorAgencyDto) {
		if (tutorAgencyDto != null) {
			tutorAgencyDto = agencyMapper.entityToDto(agencyRepo.save(agencyMapper.dtoToEntity(tutorAgencyDto)));
		}

		return tutorAgencyDto;
	}

	@Override
	public Optional<List<TutorAgencyDto>> getAgencies() {
		Iterable<Agency> agencyIterable = agencyRepo.findAll();
		
		// convert the iterable to list
        List<Agency> agencyList = StreamSupport.stream(agencyIterable.spliterator(), false)
        								.collect(Collectors.toList());

		return agencyMapper.entitiesToDtos(Optional.ofNullable(agencyList));
	}

	@Override
	public TutorAgencyDto getByAgencyId(UUID agencyId) {
		Agency agency = null;
		agencyRepo.findById(agencyId).ifPresent(agency::equals);
		return agencyMapper.entityToDto(agency);
	}

}
