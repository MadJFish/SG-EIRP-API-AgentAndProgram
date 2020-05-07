package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.AgencyLeadership;
import com.sg.eirp.program.util.CommonUtil;
import org.springframework.stereotype.Component;

@Component
public class AgencyLeadershipMapper extends DtoEntityMapper<TutorAgencyLeadershipDto, AgencyLeadership> {
    @Override
    public AgencyLeadership dtoToEntity(TutorAgencyLeadershipDto tutorAgencyLeadershipDto) {
        if (tutorAgencyLeadershipDto == null) {
            return null;
        }

        AgencyLeadership agencyLeadership = new AgencyLeadership();

        if (tutorAgencyLeadershipDto.getId() != null) {
            agencyLeadership.setId(CommonUtil.convertIdtoUUID(tutorAgencyLeadershipDto.getId()));
        }

        if (tutorAgencyLeadershipDto.getAgencyId() != null) {
            agencyLeadership.setAgencyId(CommonUtil.convertIdtoUUID(tutorAgencyLeadershipDto.getAgencyId()));
        }

        agencyLeadership.setName(tutorAgencyLeadershipDto.getName());

        agencyLeadership.setDesignation(tutorAgencyLeadershipDto.getDesignation());

        agencyLeadership.setDescription(tutorAgencyLeadershipDto.getDescription());

        return agencyLeadership;
    }

    @Override
    public TutorAgencyLeadershipDto entityToDto(AgencyLeadership agencyLeadership) {
        if (agencyLeadership == null) {
            return null;
        }

        TutorAgencyLeadershipDto dto = new TutorAgencyLeadershipDto();

        if (agencyLeadership.getId() != null) {
            dto.setId(agencyLeadership.getId().toString());
        }

        if (agencyLeadership.getAgencyId() != null) {
            dto.setAgencyId(agencyLeadership.getAgencyId().toString());
        }

        dto.setName(agencyLeadership.getName());
        dto.setDesignation(agencyLeadership.getDesignation());
        dto.setDescription(agencyLeadership.getDescription());

        return dto;
    }
}
