package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	
	@Override
	public Agency save(Agency agency) {
		return agencyRepo.save(agency);
	}

	@Override
	public List<Agency> getAgencies() {
		Iterable<Agency> agencyIterable = agencyRepo.findAll();
		
		// convert the iterable to list
        List<Agency> agencyList = StreamSupport.stream(agencyIterable.spliterator(), false)
        								.collect(Collectors.toList());
        
        return agencyList;
	}

	@Override
	public Optional<Agency> getByAgencyId(UUID agencyId) {
		return agencyRepo.findById(agencyId);
	}

}
