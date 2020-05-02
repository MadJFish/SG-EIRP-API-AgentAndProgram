package com.sg.eirp.program.mapper;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.TrainerDetailDto;
import com.sg.eirp.program.model.Agency;

public class AgencyDetailsMapper extends BaseMapper {

    @Override
    public Object mapObject(Object model) {
        if (model instanceof Agency) {
            Agency agency = (Agency) model;
            TrainerDetailDto dto = new TrainerDetailDto();
            dto.setId(agency.getId().toString());
            dto.setName(agency.getName());
            dto.setImageUrl(agency.getImageUrl());
            dto.setPromoText(agency.getTitle());
            dto.setFeatured(agency.getIsFeatured());
            dto.setTargetEduLevels(agency.getTargetEducationLevel());
            dto.setPhone(agency.getContact());
            dto.setEmail(agency.getEmail());
            dto.setAboutUs(agency.getAboutUs());

            // hq Address
            // TODO:

            return dto;
        }
        return null;
    }
}
