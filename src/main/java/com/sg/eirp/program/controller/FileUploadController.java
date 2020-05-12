package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.service.FileUploadService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/upload")
public class FileUploadController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/common")
    public BaseResponseDto<String> uploadCommonFile(@RequestParam("file") MultipartFile file) throws IOException {
        return responseDtoOK(fileUploadService.uploadCommonFile(file));
    }

    @PostMapping("/temp")
    public BaseResponseDto<String> uploadTempFile(@RequestParam("file") MultipartFile file) throws IOException {
        return responseDtoOK(fileUploadService.uploadTempFile(file));
    }

    @PostMapping("/document")
    public BaseResponseDto<DocumentDto> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String documentDtoStr) throws IOException {
        return responseDtoOK(fileUploadService.uploadFileById(file, documentDtoStr));
    }
}
