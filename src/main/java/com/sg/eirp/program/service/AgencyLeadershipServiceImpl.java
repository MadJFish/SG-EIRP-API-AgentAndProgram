package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.mapper.AgencyLeadershipMapper;
import com.sg.eirp.program.model.AgencyLeadership;
import com.sg.eirp.program.repo.AgencyLeadershipRepo;
import com.sg.eirp.program.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private DocumentService documentService;

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
        Optional<List<TutorAgencyLeadershipDto>> leadershipDtoListOptional = agencyLeadershipMapper.entitiesToDtos(agencyLeadershipOptional);

        List<TutorAgencyLeadershipDto> leadershipDtoList = null;
        if (leadershipDtoListOptional.isPresent()) {
            leadershipDtoList = leadershipDtoListOptional.get()
                                    .stream()
                                    .map(leadershipDto -> {
                                        String id = leadershipDto.getId();
                                        UUID uuid = UUID.fromString(id);
                                        Optional<List<DocumentDto>> documentDtoListOptional = documentService.getDocumentsByReference(CommonConstants.AGENCY_LEADERSHIP_TABLE, uuid);
                                        if (documentDtoListOptional.isPresent()) {
                                            Optional<DocumentDto> profileImageDtoOptional = documentDtoListOptional.get()
                                                                            .stream()
                                                                            .filter(documentDto -> documentDto.getDocumentType() != null && documentDto.getDocumentType().equalsIgnoreCase(CommonConstants.PROFILE_IMAGE))
                                                                            .findFirst();
                                            DocumentDto profileImageDto = null;
                                            if (profileImageDtoOptional.isPresent()) {
                                                profileImageDto = profileImageDtoOptional.get();
                                                leadershipDto.setImageUrl(profileImageDto.getDocumentUrl());
                                            }
                                        }
                                        return leadershipDto;
                                    })
                                    .collect(Collectors.toList());
        }

        return Optional.ofNullable(leadershipDtoList);
    }
}
