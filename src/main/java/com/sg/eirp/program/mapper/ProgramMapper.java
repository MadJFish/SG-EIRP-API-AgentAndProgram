package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.program.ProgramDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Program;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProgramMapper extends DtoEntityMapper<ProgramDto, Program> {
	@Override
	public Program dtoToEntity(ProgramDto programDto) {
		if (programDto == null) {
			return null;
		}
		Program program = new Program();

		if (programDto.getProgramId() != null) {
			program.setId(UUID.fromString(programDto.getProgramId()));
		}

		if (programDto.getAgencyId() != null) {
			program.setAgencyId(UUID.fromString(programDto.getAgencyId()));
		}

		/*
		program.setAgencyId(UUID.randomUUID());
		*/

		program.setName(programDto.getProgramName());
		program.setDescription(programDto.getProgramDescription());
		program.setDetails(programDto.getProgramDetailParagraph());
		program.setSubject(programDto.getSubject());
		program.setEducationLevel(programDto.getForEducationLevel());
		program.setMinAge(programDto.getForMinAge());
		program.setMaxAge(programDto.getForMaxAge());
		program.setFeeType(programDto.getFeeType());
		program.setFee(programDto.getFee());
		program.setFeeCurrency(programDto.getFeeCurrency());
		program.setLocations(programDto.getLocations());

		return program;
	}

	@Override
	public ProgramDto entityToDto(Program program) {
		if (program == null) {
			return null;
		}

		ProgramDto dto = new ProgramDto();

		if (program.getId() != null) {
			dto.setProgramId(program.getId().toString());
		}

		if (program.getAgencyId() != null) {
			dto.setAgencyId(program.getAgencyId().toString());
		}

		dto.setProgramName(program.getName());
		dto.setProgramDescription(program.getDescription());
		dto.setProgramDetailParagraph(program.getDetails());
		dto.setSubject(program.getSubject());
		dto.setForEducationLevel(program.getEducationLevel());
		dto.setForMinAge(program.getMinAge());
		dto.setForMaxAge(program.getMaxAge());
		dto.setFeeType(program.getFeeType());
		dto.setFee(program.getFee());
		dto.setFeeCurrency(program.getFeeCurrency());
		dto.setLocations(program.getLocations());

		return dto;
	}
}
