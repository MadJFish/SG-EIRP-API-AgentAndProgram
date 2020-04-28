package com.sg.eirp.program.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController {

    protected ResponseEntity responseOK() {
        return constructResponse(HttpStatus.OK, null, null, null);
    }

    protected <T extends Object> BaseResponseDto responseDtoOK(T dto) {
        return constructDtoResponse(HttpStatus.OK, dto, null, null);
    }

    protected <T extends Object> ResponseEntity responseOK(T dto) {
        return constructResponse(HttpStatus.OK, dto, null, null);
    }

    protected <T extends Object> ResponseEntity responseOK(List<T> dtos) {
        return constructResponse(HttpStatus.OK, dtos, null, null);
    }


    protected <T extends Object> ResponseEntity responseError(HttpStatus status, T dto, String errorCode, String errorMsg) {
        return constructResponse(status, dto, errorCode, errorMsg);
    }

    private BaseResponseDto constructDtoResponse(HttpStatus status, Object dto, String errorCode, String errorMsg) {
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        baseResponseDto.setStatus(isSuccessStatus(status));
        baseResponseDto.setErrorCode(errorCode);
        baseResponseDto.setErrorMsg(errorMsg);
        baseResponseDto.setBody(dto);
        return baseResponseDto;
    }

    private ResponseEntity constructResponse(HttpStatus status, Object dto, String errorCode, String errorMsg) {
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        baseResponseDto.setStatus(isSuccessStatus(status));
        baseResponseDto.setErrorCode(errorCode);
        baseResponseDto.setErrorMsg(errorMsg);
        baseResponseDto.setBody(dto);
        return new ResponseEntity(baseResponseDto, status);
    }


    private boolean isSuccessStatus(HttpStatus status) {
        return status == HttpStatus.OK || status == HttpStatus.ACCEPTED || status == HttpStatus.CREATED;
    }
}

