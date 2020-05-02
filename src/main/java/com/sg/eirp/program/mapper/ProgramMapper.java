package com.sg.eirp.program.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.ProgramDto;
import com.sg.eirp.program.model.Program;

public class ProgramMapper extends BaseMapper {

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

}
