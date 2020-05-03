package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.program.ProgramSessionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProgramSessionService {

    ProgramSessionDto save(ProgramSessionDto programSessionDto);

    List<ProgramSessionDto> saveAll(List<ProgramSessionDto> programSessionDtoList);

    List<ProgramSessionDto> saveAll(List<ProgramSessionDto> programSessionDtoList, UUID programId);

    Optional<List<ProgramSessionDto>> getByProgramId(UUID programId);

}
