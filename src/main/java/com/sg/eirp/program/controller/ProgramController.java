package com.sg.eirp.program.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sg.eirp.program.base.BaseController;
import com.sg.eirp.program.base.BaseResponseDto;
import com.sg.eirp.program.dto.ProgramDto;
import com.sg.eirp.program.mapper.ProgramMapper;
import com.sg.eirp.program.model.Program;
import com.sg.eirp.program.service.ProgramService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/program")
public class ProgramController extends BaseController {

	@Autowired
	private ProgramService programService;

	@GetMapping("/getAll")
	public BaseResponseDto<List<ProgramDto>> getAllProgramsRequest() {
		ProgramMapper mapper = new ProgramMapper();
		List<Object> programList = programService.getPrograms()
									.stream()
									.map(program -> (Object) program)
									.collect(Collectors.toList());
		
		return responseDtoOK(mapper.mapObjects(programList));
	}
	
	@GetMapping("/get/{requestId}")
	public ResponseEntity<ProgramDto> getProgramRequest(@PathVariable String requestId) throws URISyntaxException {
		try {
			ProgramMapper mapper = new ProgramMapper();
			return responseOK(mapper.mapObject((Object)programService.getByProgramId(UUID.nameUUIDFromBytes(requestId.getBytes()))));
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.BAD_REQUEST, Program.class, "400", e.getMessage());
		}
	}

	@PostMapping("/add")
	public ResponseEntity<UUID> addProgramRequest(@RequestBody ProgramDto programDto) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.BAD_REQUEST, Program.class, "400", e.getMessage());
		}
	}

	@PostMapping("/update")
	public ResponseEntity<UUID> updateProgramRequest(@RequestBody ProgramDto programDto) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.BAD_REQUEST, Program.class, "400", e.getMessage());
		}
	}
}
