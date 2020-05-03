package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.program.ProgramSessionDto;
import com.sg.eirp.program.mapper.ProgramSessionMapper;
import com.sg.eirp.program.model.ProgramSession;
import com.sg.eirp.program.repo.ProgramSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProgramSessionServiceImpl implements ProgramSessionService {

    @Autowired
    private ProgramSessionRepo programSessionRepoRepo;

    @Autowired
    private ProgramSessionMapper programSessionMapper;

    @Override
    public ProgramSessionDto save(ProgramSessionDto programSessionDto) {
        if (programSessionDto == null) {
            return null;
        }

        // convert programSessionDto to programSession
        ProgramSession programSession = programSessionMapper.dtoToEntity(programSessionDto);

        // save program to database
        programSessionDto = programSessionMapper.entityToDto(programSessionRepoRepo.save(programSession));

        return programSessionDto;
    }

    @Override
    public List<ProgramSessionDto> saveAll(List<ProgramSessionDto> programSessionDtoList) {
        if (programSessionDtoList == null || programSessionDtoList.isEmpty()) {
            return null;
        }

        Optional<List<ProgramSession>> programSessionList = programSessionMapper.dtosToEntities(Optional.ofNullable(programSessionDtoList));

        Iterable<ProgramSession> programSessionIterable = programSessionRepoRepo.saveAll(programSessionList.get());

        // convert the iterable to list
        List<ProgramSession> programList = StreamSupport.stream(programSessionIterable.spliterator(), false)
                .collect(Collectors.toList());

        return programSessionMapper.entitiesToDtos(Optional.ofNullable(programList)).get();
    }

    @Override
    public List<ProgramSessionDto> saveAll(List<ProgramSessionDto> programSessionDtoList, UUID programId) {
        if (programSessionDtoList != null && programId != null) {
            for (ProgramSessionDto dto : programSessionDtoList) {
                dto.setProgramId(programId.toString());
            }
        }
        return saveAll(programSessionDtoList);
    }

    @Override
    public Optional<List<ProgramSessionDto>> getByProgramId(UUID programId) {
        if (programId == null) {
            return null;
        }

        Optional<List<ProgramSession>> programSessionList = programSessionRepoRepo.findByProgramId(programId);

        return programSessionMapper.entitiesToDtos(programSessionList);
    }
}
