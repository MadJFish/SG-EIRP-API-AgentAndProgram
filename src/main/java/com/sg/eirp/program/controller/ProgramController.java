package com.sg.eirp.program.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.eirp.program.base.BaseController;
import com.sg.eirp.program.base.BaseResponseDto;
import com.sg.eirp.program.dto.ProgramDto;
import com.sg.eirp.program.mapper.ProgramMapper;
import com.sg.eirp.program.service.ProgramService;
import com.sg.eirp.program.service.ProgramServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/program")
public class ProgramController extends BaseController {
	
	@GetMapping("/allPrograms")
	public BaseResponseDto<List<ProgramDto>> getAllPrograms() {
		ProgramService programService = new ProgramServiceImpl();
		ProgramMapper mapper = new ProgramMapper();
		List<Object> programList = programService.getPrograms()
									.stream()
									.map(program -> (Object) program)
									.collect(Collectors.toList());
		
		return responseDtoOK(mapper.mapObjects(programList));
	}
}
