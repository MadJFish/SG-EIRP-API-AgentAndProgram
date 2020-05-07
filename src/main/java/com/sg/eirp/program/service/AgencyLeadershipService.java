package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyLeadershipService {

    Optional<List<TutorAgencyLeadershipDto>> saveAll(List<TutorAgencyLeadershipDto> tutorAgencyLeadershipDtoList);

    Optional<List<TutorAgencyLeadershipDto>> getByAgencyId(UUID agencyId);

}
