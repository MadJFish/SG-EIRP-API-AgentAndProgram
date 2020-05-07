package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.common.dto.common.BulletinDto;
import com.sg.eirp.program.service.BulletinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/bulletin")
public class BulletinController extends BaseController {

    @Autowired
    private BulletinService bulletinService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<BulletinDto> getBulletinByIdRequest(@RequestParam String bulletinId) {
        return null;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<BulletinDto> addOrUpdateBulletin(@RequestBody BulletinDto bulletinDto) {
        return responseDtoOK(bulletinService.save(bulletinDto));
    }
}
