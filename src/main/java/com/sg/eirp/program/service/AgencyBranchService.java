package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyBranchService {
    TutorAgencyBranchDto save(TutorAgencyBranchDto dto);

    Optional<List<TutorAgencyBranchDto>> saveAll(List<TutorAgencyBranchDto> dtoList);

    Optional<List<TutorAgencyBranchDto>> getBranchesByAgencyId(UUID agencyId);

    Optional<List<TutorAgencyBranchDto>> getAll();
}
