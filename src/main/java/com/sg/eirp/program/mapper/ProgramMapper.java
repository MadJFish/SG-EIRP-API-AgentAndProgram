package com.sg.eirp.program.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.ProgramDto;
import com.sg.eirp.program.model.Program;

public class ProgramMapper implements BaseMapper {

	@Override
	public Object mapObject(Object model) {
		if (model instanceof Program) {
			Program program = (Program) model;
			ProgramDto dto = new ProgramDto();
			dto.setProgramName(program.getName());
			return dto;
		}
		return null;
	}

	@Override
	public List<Object> mapObjects(List<Object> models) {
		if (models == null) {
			return new ArrayList<Object>();
		}
		
		return models.stream()
				.map(model -> mapObject(model))
				.collect(Collectors.toList());
	}

}
