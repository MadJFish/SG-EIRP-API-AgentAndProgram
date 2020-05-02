package com.sg.eirp.program.mapper;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.DocumentDto;
import com.sg.eirp.program.dto.TrainerDto;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.model.Document;

import java.util.List;

public class AgencyMapper extends BaseMapper {

    @Override
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
            dto.setTargetEduLevels(agency.getTargetEducationLevel());
            dto.setPhone(agency.getContact());
            dto.setEmail(agency.getEmail());
            dto.setAboutUs(agency.getAboutUs());
            dto.setSubjects(agency.getSubjects());
            dto.setLocations(agency.getNearbyMRT());

            return dto;
        }
        return null;
    }

}
