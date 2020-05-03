package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.program.mapper.AgencyMapper;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.service.AgencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/trainer")
public class TutorAgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/get/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDto> getTrainerByUUIDRequest(@PathVariable String uuid) {
        return responseDtoOK(agencyService.getByAgencyId(UUID.fromString(uuid)));
    }

    /*
    @GetMapping("/get/details/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TrainerDetailDto> getTrainerDetailsByUUIDRequest(@RequestParam String uuid) {
        try {
            if (trainerId == null || trainerId.isEmpty()) {
                return null;
            }

            Agency agency = null;
            agencyService.getByAgencyId(UUID.fromString(trainerId))
                    .ifPresent(agency::equals);

            AgencyMapper mapper = new AgencyMapper();
            TrainerDto trainerDto = null;
            if (agency != null) {
                trainerDto = (TrainerDto) mapper.entityToDto(agency);
            }

            return responseDtoOK(trainerDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */

}
