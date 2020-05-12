package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDetailDto;
import com.sg.eirp.common.dto.agency.TutorAgencyDto;
import com.sg.eirp.common.dto.agency.TutorAgencyLeadershipDto;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.program.service.AgencyBranchService;
import com.sg.eirp.program.service.AgencyLeadershipService;
import com.sg.eirp.program.service.AgencyService;
import com.sg.eirp.program.util.CommonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/tutorAgency")
public class TutorAgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AgencyBranchService agencyBranchService;

    @Autowired
    private AgencyLeadershipService agencyLeadershipService;

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<TutorAgencyDto>> getAllTutorAgenciesRequest() {
        return responseDtoOK(agencyService.getAllAgencies());
    }

    @GetMapping("/get/byFeatured")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<TutorAgencyDto>> getFeaturedTutorAgencyRequest(@RequestParam Boolean featured) {
        return responseDtoOK(agencyService.getAgenciesByFeatured(featured));
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDto> getTutorAgencyRequest(@RequestParam String tutorAgencyId) {
        return responseDtoOK(agencyService.getByAgencyId(CommonUtil.convertIdtoUUID(tutorAgencyId)));
    }

    /*
    @GetMapping("/get/byUserId")
    public BaseResponseDto<List<TutorAgencyDto>> getUserTutorAgencyRequest(@RequestParam String userId) {
        return responseDtoOk()
    }
    */

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<TutorAgencyDto> saveTutorAgencyRequest(@RequestBody TutorAgencyDto tutorAgencyDto) {
        return responseDtoOK(agencyService.save(tutorAgencyDto));
    }

    @GetMapping("/details/get")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDetailDto> getTutorAgencyDetailsByAgencyIdRequest(@RequestParam String tutorAgencyId) {
        return responseDtoOK(agencyService.getDetailsByAgencyId(CommonUtil.convertIdtoUUID(tutorAgencyId)));
    }

    @PutMapping("/details/put")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<TutorAgencyDetailDto> updateTutorAgencyDetailsRequest(@RequestBody TutorAgencyDetailDto tutorAgencyDetailDto) {
        return responseDtoOK(agencyService.updateDetails(tutorAgencyDetailDto));
    }

    @PostMapping("/details/branches/post")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<List<TutorAgencyDetailDto>> saveTutorAgencyBranches(@RequestBody List<TutorAgencyBranchDto> tutorAgencyBranchDtoList) {
        return responseDtoOK(agencyService.saveTutorAgencyBranchAll(tutorAgencyBranchDtoList));
    }

    @GetMapping("/details/branch/get/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<TutorAgencyBranchDto>> getAllTutorAgencyBranchesRequest() {
        return responseDtoOK(agencyBranchService.getAll());
    }

    @PostMapping("/details/leadership/post")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<List<TutorAgencyLeadershipDto>> saveTutorAgencyLeaderships(@RequestBody List<TutorAgencyLeadershipDto> tutorAgencyLeadershipDtoList) {
        return responseDtoOK(agencyLeadershipService.saveAll(tutorAgencyLeadershipDtoList));
    }

}
