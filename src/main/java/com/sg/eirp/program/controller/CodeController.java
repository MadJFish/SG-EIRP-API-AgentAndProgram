package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.program.model.Code;
import com.sg.eirp.program.service.CodeService;
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
@RequestMapping("/api/code")
public class CodeController extends BaseController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/getByType")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<List<Code>> getCodesByTypeRequest(@RequestParam String type) throws URISyntaxException {
        try {
            return responseDtoOK(codeService.getCodesByType(type));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
