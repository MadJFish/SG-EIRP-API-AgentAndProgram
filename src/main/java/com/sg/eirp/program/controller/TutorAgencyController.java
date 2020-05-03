package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.agency.TutorAgencyDetailDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.program.service.AgencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/tutorAgency")
public class TutorAgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<TutorAgencyDto>> getAllTutorAgenciesRequest() {
        return responseDtoOK(agencyService.getAllAgencies());
    }

    @GetMapping("/get/byFeatured")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<TutorAgencyDto>> getTrainerRequest(@RequestParam Boolean featured) {
        return responseDtoOK(agencyService.getAgenciesByFeatured(featured));
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDto> getTrainerRequest(@RequestParam String tutorAgencyId) {
        return responseDtoOK(agencyService.getByAgencyId(UUID.fromString(tutorAgencyId)));
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<TutorAgencyDto> saveTutorAgencyRequest(@RequestBody TutorAgencyDto tutorAgencyDto) {
        return responseDtoOK(agencyService.save(tutorAgencyDto));
    }

    @PutMapping("/details/put")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDetailDto> updateTutorAgencyDetailsRequest(@RequestBody TutorAgencyDetailDto tutorAgencyDetailDto) {
        return responseDtoOK(agencyService.updateDetails(tutorAgencyDetailDto));
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
