package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgencyMapper extends DtoEntityMapper<TutorAgencyDto, Agency> {

    @Override
    public Agency dtoToEntity(TutorAgencyDto dto) {
        if (dto == null) {
            return null;
        }

        Agency agency = new Agency();

        if (dto.getId() != null) {
            agency.setId(UUID.fromString(dto.getId()));
        }

        agency.setName(dto.getName());
        agency.setPromoText(dto.getPromoText());
        agency.setFeatured(CommonUtil.convertBooleanToBit(dto.getFeatured()));
        agency.setTargetEducationLevel(CommonUtil.convertListToString(dto.getTargetEduLevels()));
        agency.setContact(dto.getPhone());
        agency.setEmail(dto.getEmail());
        agency.setAboutUs(dto.getAboutUs());
        agency.setSubjects(CommonUtil.convertListToString(dto.getSubjects()));
        agency.setNearbyMRT(CommonUtil.convertListToString(dto.getLocations()));

        return agency;
    }

    @Override
    public TutorAgencyDto entityToDto(Agency agency) {
        if (agency == null) {
            return null;
        }

        TutorAgencyDto dto = new TutorAgencyDto();
        if (agency.getId() != null) {
            dto.setId(agency.getId().toString());
        }

        dto.setName(agency.getName());
        dto.setPromoText(agency.getPromoText());
        dto.setFeatured(CommonUtil.convertBitToBoolean(agency.getFeatured()));
        dto.setTargetEduLevels(CommonUtil.convertStringToList(agency.getTargetEducationLevel()));
        dto.setPhone(agency.getContact());
        dto.setEmail(agency.getEmail());
        dto.setAboutUs(agency.getAboutUs());
        dto.setSubjects(CommonUtil.convertStringToList(agency.getSubjects()));
        dto.setLocations(CommonUtil.convertStringToList(agency.getNearbyMRT()));

        return dto;
    }
}
