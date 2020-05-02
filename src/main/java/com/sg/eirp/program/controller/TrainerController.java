package com.sg.eirp.program.controller;

import com.sg.eirp.program.base.BaseController;
import com.sg.eirp.program.base.BaseResponseDto;
import com.sg.eirp.program.dto.DocumentDto;
import com.sg.eirp.program.dto.TrainerDetailDto;
import com.sg.eirp.program.dto.TrainerDto;
import com.sg.eirp.program.mapper.AgencyMapper;
import com.sg.eirp.program.mapper.DocumentMapper;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.model.Document;
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
public class TrainerController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TrainerDto> getTrainerByUUIDRequest(@RequestParam String trainerId) {
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
                trainerDto = (TrainerDto) mapper.mapObject(agency);
            }

            return responseDtoOK(trainerDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getDetailsById")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TrainerDetailDto> getTrainerByUUIDRequest(@RequestParam String trainerId) {
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
                trainerDto = (TrainerDto) mapper.mapObject(agency);
            }

            return responseDtoOK(trainerDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
