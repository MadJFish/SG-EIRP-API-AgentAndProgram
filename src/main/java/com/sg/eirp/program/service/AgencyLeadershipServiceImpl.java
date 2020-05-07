package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;
import com.sg.eirp.program.mapper.AgencyLeadershipMapper;
import com.sg.eirp.program.model.AgencyLeadership;
import com.sg.eirp.program.repo.AgencyLeadershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyLeadershipServiceImpl implements AgencyLeadershipService {

    @Autowired
    private AgencyLeadershipRepo agencyLeadershipRepo;

    @Autowired
    private AgencyLeadershipMapper agencyLeadershipMapper;

    @Override
    public Optional<List<TutorAgencyLeadershipDto>> saveAll(List<TutorAgencyLeadershipDto> tutorAgencyLeadershipDtoList) {
        if (tutorAgencyLeadershipDtoList == null) {
            return null;
        }

        Optional<List<AgencyLeadership>> agencyLeadershipListOptional = agencyLeadershipMapper.dtosToEntities(Optional.ofNullable(tutorAgencyLeadershipDtoList));
        List<AgencyLeadership> agencyLeadershipList = null;
        if (agencyLeadershipListOptional.isPresent()) {
            agencyLeadershipList = agencyLeadershipListOptional.get();
        }

        Iterable<AgencyLeadership> agencyLeadershipIterable = agencyLeadershipRepo.saveAll(agencyLeadershipList);

        // convert the iterable to list
        List<AgencyLeadership> leadershipList = StreamSupport.stream(agencyLeadershipIterable.spliterator(), false)
                .collect(Collectors.toList());

        return agencyLeadershipMapper.entitiesToDtos(Optional.ofNullable(leadershipList));
    }

    @Override
    public Optional<List<TutorAgencyLeadershipDto>> getByAgencyId(UUID agencyId) {
        if (agencyId == null) {
            return null;
        }

        Optional<List<AgencyLeadership>> agencyLeadershipOptional = agencyLeadershipRepo.getByAgencyId(agencyId);

        return agencyLeadershipMapper.entitiesToDtos(agencyLeadershipOptional);
    }
}
