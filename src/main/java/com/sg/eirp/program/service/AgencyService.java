package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.program.model.Agency;

public interface AgencyService {
	TutorAgencyDto save(TutorAgencyDto tutorAgencyDto);
	
	Optional<List<TutorAgencyDto>> getAgencies();

	TutorAgencyDto getByAgencyId(UUID agencyId);
}
