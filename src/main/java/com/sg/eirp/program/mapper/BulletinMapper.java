package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.common.BulletinDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Bulletin;
import com.sg.eirp.program.util.CommonUtil;
import org.springframework.stereotype.Component;

@Component
public class BulletinMapper extends DtoEntityMapper<BulletinDto, Bulletin> {
    @Override
    public Bulletin dtoToEntity(BulletinDto bulletinDto) {
        if (bulletinDto == null) {
            return null;
        }

        Bulletin bulletin = new Bulletin();

        if (bulletinDto.getBulletinId() != null) {
            bulletin.setId(CommonUtil.convertIdtoUUID(bulletinDto.getBulletinId()));
        }

        bulletin.setPublishedDate(bulletinDto.getPublishedDate());
        bulletin.setTitle(bulletinDto.getTitle());
        bulletin.setContent(bulletinDto.getParagraphInHtml());
        bulletin.setReferenceTable((bulletinDto.getReferenceTable()));

        if (bulletinDto.getReferenceId() != null) {
            bulletin.setReferenceId(CommonUtil.convertIdtoUUID(bulletinDto.getReferenceId()));
        }

        return bulletin;
    }

    @Override
    public BulletinDto entityToDto(Bulletin bulletin) {
        if (bulletin == null) {
            return null;
        }

        BulletinDto dto = new BulletinDto();

        if (bulletin.getId() != null) {
            dto.setBulletinId(bulletin.getId().toString());
        }

        dto.setPublishedDate(bulletin.getPublishedDate());
        dto.setTitle(bulletin.getTitle());
        dto.setParagraphInHtml(bulletin.getContent());
        dto.setReferenceTable(bulletin.getReferenceTable());

        if (bulletin.getReferenceId() != null) {
            dto.setReferenceId(bulletin.getReferenceId().toString());
        }

        return dto;
    }
}
