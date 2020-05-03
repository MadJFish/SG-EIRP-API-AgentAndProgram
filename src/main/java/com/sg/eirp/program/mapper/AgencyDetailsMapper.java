package com.sg.eirp.program.mapper;

import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class AgencyDetailsMapper extends DtoEntityMapper {

    /*
    public Object mapObject(Object model) {
        if (model instanceof Agency) {
            Agency agency = (Agency) model;
            TrainerDetailDto dto = new TrainerDetailDto();
            dto.setId(agency.getId().toString());
            dto.setName(agency.getName());
            dto.setImageUrl(agency.getImageUrl());
            dto.setPromoText(agency.getTitle());
            dto.setFeatured(agency.getFeatured());
            dto.setTargetEduLevels(CommonUtil.convertStringToList(agency.getTargetEducationLevel()));
            dto.setPhone(agency.getContact());
            dto.setEmail(agency.getEmail());
            dto.setAboutUs(agency.getAboutUs());

            // hq Address
            // TODO:

            return dto;
        }
        return null;
    }
    */

    @Override
    public Object dtoToEntity(Object o) {
        return null;
    }

    @Override
    public Object entityToDto(Object o) {
        return null;
    }
}
