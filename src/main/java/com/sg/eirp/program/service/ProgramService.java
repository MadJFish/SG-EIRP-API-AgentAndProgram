package com.sg.eirp.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sg.eirp.common.dto.program.ProgramDto;

public interface ProgramService {
	ProgramDto save(ProgramDto program);
	
	Optional<List<ProgramDto>> getAllPrograms();
	
	ProgramDto getByProgramId(UUID programId);
}
