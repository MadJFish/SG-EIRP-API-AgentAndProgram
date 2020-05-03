package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Program;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProgramMapper extends DtoEntityMapper<ProgramDto, Program> {

	/*
	@Override
	public Object mapObject(Object model) {
		if (model instanceof Program) {
			Program program = (Program) model;
			ProgramDto dto = new ProgramDto();
			dto.setProgramId(program.getId().toString());
			dto.setProgramName(program.getName());
			dto.setProgramDescription(program.getDescription());
			dto.setProgramDetailParagraph(program.getDetails());
			// dto.setImageUrl(program.get)
			dto.setSubject(program.getSubject());
			dto.setForEducationLevel(program.getEducationLevel());
			dto.setForMinAge(program.getMinAge());
			dto.setForMaxAge(program.getMaxAge());
			//dto.setDuration(program.)
			dto.setCost(program.getFee().toString() + " " + program.getFeeCurrency());
			return dto;
		}
		return null;
	}
	*/

	@Override
	public Program dtoToEntity(ProgramDto programDto) {
		if (programDto == null) {
			return null;
		}
		Program program = new Program();
		BeanUtils.copyProperties(programDto, program);
		return program;
	}

	@Override
	public ProgramDto entityToDto(Program program) {
		if (program == null) {
			return null;
		}
		ProgramDto programDto = new ProgramDto();
		BeanUtils.copyProperties(program, programDto);
		return programDto;
	}
}
