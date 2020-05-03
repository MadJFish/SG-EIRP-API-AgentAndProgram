package com.sg.eirp.program.controller;

import java.util.List;
import java.util.UUID;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.program.mapper.ProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.common.dto.program.ProgramDto;

import com.sg.eirp.program.service.ProgramService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/program")
public class ProgramController extends BaseController {

	@Autowired
	private ProgramService programService;

	@Autowired
	@Qualifier(value = "programMapper")
	private ProgramMapper mapper;

	@GetMapping("/get/all")
	@ResponseStatus(HttpStatus.OK)
	public BaseResponseDto<List<ProgramDto>> getAllProgramsRequest() {
		return responseDtoOK(programService.getAllPrograms());
	}
	
	@GetMapping("/get")
	@ResponseStatus(HttpStatus.OK)
	public BaseResponseDto<ProgramDto> getProgramRequest(@RequestParam String programId) {
		return responseDtoOK(programService.getByProgramId(UUID.fromString(programId)));
	}

	@PostMapping("/post")
	@ResponseStatus(HttpStatus.CREATED)
	public BaseResponseDto<ProgramDto> saveProgramRequest(@RequestBody ProgramDto programDto) {
		return responseDtoOK(programService.save(programDto));
	}
}
