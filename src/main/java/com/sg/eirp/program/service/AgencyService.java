package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sg.eirp.program.model.Agency;

public interface AgencyService {
	Agency save(Agency agency);
	
	List<Agency> getAgencies();
	
	Optional<Agency> getByAgencyId(UUID agencyId);
}
