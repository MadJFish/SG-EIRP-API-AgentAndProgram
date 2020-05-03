package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper extends DtoEntityMapper<TutorAgencyDto, Agency> {

    /*
    public Object mapObject(Object model) {
        if (model instanceof Agency) {
            Agency agency = (Agency) model;
            TrainerDto dto = new TrainerDto();
            dto.setId(agency.getId().toString());
            dto.setName(agency.getName());
            dto.setImageUrl(agency.getImageUrl());
            dto.setVideoUrl(agency.getVideoUrl());
            dto.setPromoText(agency.getTitle());
            dto.setFeatured(agency.getIsFeatured());
            dto.setTargetEduLevels(CommonUtil.convertStringToList(agency.getTargetEducationLevel()));
            dto.setPhone(agency.getContact());
            dto.setEmail(agency.getEmail());
            dto.setAboutUs(agency.getAboutUs());
            dto.setSubjects(CommonUtil.convertStringToList(agency.getSubjects()));
            dto.setLocations(CommonUtil.convertStringToList(agency.getNearbyMRT()));

            return dto;
        }
        return null;
    }
    */

    @Override
    public Agency dtoToEntity(TutorAgencyDto tutorAgencyDto) {
        return null;
    }

    @Override
    public TutorAgencyDto entityToDto(Agency agency) {
        return null;
    }
}
