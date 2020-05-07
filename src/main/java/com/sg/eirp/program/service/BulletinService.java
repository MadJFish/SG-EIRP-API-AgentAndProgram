package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.common.BulletinDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BulletinService {

    BulletinDto save(BulletinDto dto);

    Optional<List<BulletinDto>> saveAll(List<BulletinDto> bulletinDtoList);

    Optional<List<BulletinDto>> getByReference(String referenceTable, UUID referenceId);

}
