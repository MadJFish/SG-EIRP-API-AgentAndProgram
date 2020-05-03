package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.model.AgencyBranch;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgencyBranchMapper extends DtoEntityMapper<TutorAgencyBranchDto, AgencyBranch> {
    @Override
    public AgencyBranch dtoToEntity(TutorAgencyBranchDto dto) {
        if (dto == null) {
            return null;
        }

        AgencyBranch ab = new AgencyBranch();

        if (dto.getId() != null) {
            ab.setId(UUID.fromString(dto.getId()));
        }

        ab.setName(dto.getName());
        ab.setContact(dto.getPhone());
        ab.setEmail(dto.getEmail());
        ab.setNearbyMRT(dto.getNearbyMRT());

        return ab;
    }

    @Override
    public TutorAgencyBranchDto entityToDto(AgencyBranch agencyBranch) {
        if (agencyBranch == null) {
            return null;
        }

        TutorAgencyBranchDto dto = new TutorAgencyBranchDto();

        if (agencyBranch.getId() != null) {
            dto.setId(agencyBranch.getId().toString());
        }

        dto.setName(agencyBranch.getName());
        dto.setPhone(agencyBranch.getContact());
        dto.setEmail(agencyBranch.getEmail());
        dto.setNearbyMRT(agencyBranch.getNearbyMRT());

        return dto;
    }
}
