package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Address;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.model.AgencyBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgencyBranchMapper extends DtoEntityMapper<TutorAgencyBranchDto, AgencyBranch> {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AgencyBranch dtoToEntity(TutorAgencyBranchDto dto) {
        if (dto == null) {
            return null;
        }

        AgencyBranch ab = new AgencyBranch();

        if (dto.getId() != null) {
            ab.setId(UUID.fromString(dto.getId()));
        }

        if (dto.getAgencyId() != null) {
            ab.setAgencyId(UUID.fromString(dto.getAgencyId()));
        }

        ab.setName(dto.getName());
        ab.setContact(dto.getPhone());
        ab.setEmail(dto.getEmail());
        ab.setNearbyMRT(dto.getNearbyMRT());

        /*
        // set address
        AddressDto addressDto = dto.getAddress();
        if (addressDto != null) {
            ab.setAddress(addressMapper.dtoToEntity(addressDto));
        }
        */

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

        // get agency id
        if (agencyBranch.getAgencyId() != null) {
            dto.setAgencyId(agencyBranch.getAgencyId().toString());
        }

        dto.setName(agencyBranch.getName());
        dto.setPhone(agencyBranch.getContact());
        dto.setEmail(agencyBranch.getEmail());
        dto.setNearbyMRT(agencyBranch.getNearbyMRT());

        /*
        // set address
        Address address = agencyBranch.getAddress();
        if (address != null) {
            dto.setAddress(addressMapper.entityToDto(address));
        }
        */

        return dto;
    }
}
