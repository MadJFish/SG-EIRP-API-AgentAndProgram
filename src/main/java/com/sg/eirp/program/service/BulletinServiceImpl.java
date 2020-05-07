package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.common.BulletinDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.exceptions.BusinessValidationException;
import com.sg.eirp.program.mapper.BulletinMapper;
import com.sg.eirp.program.model.Bulletin;
import com.sg.eirp.program.model.ProgramSession;
import com.sg.eirp.program.repo.BulletinRepo;
import com.sg.eirp.program.util.CommonConstants;
import com.sg.eirp.program.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional(rollbackFor = Exception.class)
@Service
public class BulletinServiceImpl implements BulletinService {

    private Logger logger = LoggerFactory.getLogger(BulletinServiceImpl.class);

    @Autowired
    private BulletinRepo bulletinRepo;

    @Autowired
    private BulletinMapper bulletinMapper;

    @Autowired
    private DocumentService documentService;

    @Override
    public BulletinDto save(BulletinDto dto) {
        if (dto == null) {
            return null;
        }

        // save bulletin
        Bulletin bulletin = bulletinRepo.save(bulletinMapper.dtoToEntity(dto));
        if (bulletin == null) {
            throw new BusinessValidationException();
        }

        // get bulletin id
        UUID bulletinId = bulletin.getId();

        // get bulletin image
        DocumentDto imageDto = dto.getImage();

        // convert bulletin to bulletin dto
        dto = bulletinMapper.entityToDto(bulletin);

        // save bulletin image
        logger.info("imageDto: " + imageDto.toString());

        if (imageDto != null) {
            imageDto = documentService.save(imageDto, CommonConstants.BULLETIN_TABLE, bulletinId);
            dto.setImage(imageDto);
        }

        return dto;
    }

    @Override
    public Optional<List<BulletinDto>> saveAll(List<BulletinDto> bulletinDtoList) {
        return Optional.empty();
    }

    @Override
    public Optional<List<BulletinDto>> getByReference(String referenceTable, UUID referenceId) {
        if (referenceTable == null || referenceTable.isEmpty() || referenceId == null) {
            return null;
        }

        List<Bulletin> bulletinList = new ArrayList<>();
        bulletinRepo.findByReferenceId(referenceTable, referenceId).ifPresent(bulletinList::addAll);

        Optional<List<BulletinDto>> bulletinDtoListOptional = bulletinMapper.entitiesToDtos(Optional.ofNullable(bulletinList));

        // get bulletin image
        List<BulletinDto> bulletinDtoList = null;
        if (bulletinDtoListOptional != null) {
            bulletinDtoList = bulletinDtoListOptional.get()
                                .stream()
                                .map(dto -> {
                                    String id = dto.getBulletinId();
                                    UUID uuid = CommonUtil.convertIdtoUUID(id);
                                    DocumentDto imageDto = getImageByBulletinId(uuid);
                                    dto.setImage(imageDto);
                                    return dto;
                                })
                                .collect(Collectors.toList());
        }

        return Optional.ofNullable(bulletinDtoList);
    }

    private DocumentDto getImageByBulletinId(UUID bulletinId) {
        if (bulletinId == null) {
            return null;
        }

        Optional<List<DocumentDto>> documentDtoListOptional = documentService.getDocumentsByReference(CommonConstants.BULLETIN_TABLE, bulletinId);
        List<DocumentDto> documentDtoList = null;
        if (documentDtoListOptional.isPresent()) {
            documentDtoList = documentDtoListOptional.get();
        }

        DocumentDto documentDto = null;
        if (documentDtoList != null && documentDtoList.size() > 0) {
            documentDto = documentDtoList.get(0);
        }

        return documentDto;
    }
}
