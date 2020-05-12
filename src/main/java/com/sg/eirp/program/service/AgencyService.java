package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDetailDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.program.model.Agency;

public interface AgencyService {
	TutorAgencyDto save(TutorAgencyDto tutorAgencyDto);

	Optional<List<TutorAgencyBranchDto>>  saveTutorAgencyBranchAll(List<TutorAgencyBranchDto> branchDtoList);
	
	Optional<List<TutorAgencyDto>> getAllAgencies();

	Optional<List<TutorAgencyDto>> getAgenciesByFeatured(boolean featured);

	TutorAgencyDto getByAgencyId(UUID agencyId);

	// Optional<List<TutorAgencyDto>> getByUserId(UUID userId);

	TutorAgencyDetailDto getDetailsByAgencyId(UUID agencyId);

	TutorAgencyDetailDto updateDetails(TutorAgencyDetailDto dto);
}
