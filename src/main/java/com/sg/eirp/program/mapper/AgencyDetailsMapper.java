package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.agency.TutorAgencyDetailDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.program.model.Agency;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgencyDetailsMapper extends AgencyMapper {

    @Override
    public Agency dtoToEntity(TutorAgencyDto tutorAgencyDto) {
        if (tutorAgencyDto == null) {
            return null;
        }

        Agency agency = super.dtoToEntity(tutorAgencyDto);
        TutorAgencyDetailDto dto = (TutorAgencyDetailDto) tutorAgencyDto;

        return agency;
    }

    @Override
    public TutorAgencyDetailDto entityToDto(Agency agency) {
        if (agency == null) {
            return null;
        }

        TutorAgencyDetailDto dto = new TutorAgencyDetailDto();
        BeanUtils.copyProperties(super.entityToDto(agency), dto);

        return dto;
    }
}
