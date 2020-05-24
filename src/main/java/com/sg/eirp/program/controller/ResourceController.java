package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;

// import com.sg.eirp.common.dto.common.ResourceDto;
import com.sg.eirp.program.dto.ResourceDto;
import com.sg.eirp.program.service.ResourceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/userRole")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<ResourceDto>> getResourceByUserRoleRequest(@RequestParam String userRole) throws URISyntaxException {
        try {
            return responseDtoOK(resourceService.getResourceByUserRole(userRole));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
