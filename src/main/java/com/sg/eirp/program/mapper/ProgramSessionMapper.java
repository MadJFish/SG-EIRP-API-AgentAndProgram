package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.program.ProgramSessionDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.ProgramSession;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProgramSessionMapper extends DtoEntityMapper<ProgramSessionDto, ProgramSession> {
    @Override
    public ProgramSession dtoToEntity(ProgramSessionDto programSessionDto) {
        if (programSessionDto == null) {
            return null;
        }
        ProgramSession programSession = new ProgramSession();

        if (programSessionDto.getProgramSessionId() != null) {
            programSession.setId(UUID.fromString(programSessionDto.getProgramSessionId()));
        }

        if (programSessionDto.getProgramId() != null) {
            programSession.setProgramId(UUID.fromString(programSessionDto.getProgramId()));
        }

        programSession.setStartDatetime(programSessionDto.getStartDatetime());

        programSession.setEndDatetime(programSessionDto.getEndDatetime());

        programSession.setVenue(programSessionDto.getVenue());

        return programSession;
    }

    @Override
    public ProgramSessionDto entityToDto(ProgramSession programSession) {
        if (programSession == null) {
            return null;
        }

        ProgramSessionDto dto = new ProgramSessionDto();

        if (programSession.getId() != null) {
            dto.setProgramSessionId(programSession.getId().toString());
        }

        if (programSession.getProgramId() != null) {
            dto.setProgramId(programSession.getProgramId().toString());
        }

        dto.setStartDatetime(programSession.getStartDatetime());

        dto.setEndDatetime(programSession.getEndDatetime());

        dto.setVenue(programSession.getVenue());

        return dto;
    }
}
