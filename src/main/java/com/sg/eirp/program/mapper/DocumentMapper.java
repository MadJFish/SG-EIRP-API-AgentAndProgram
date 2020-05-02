package com.sg.eirp.program.mapper;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.DocumentDto;
import com.sg.eirp.program.model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentMapper extends BaseMapper {

    @Override
    public Object mapObject(Object model) {
        if (model instanceof Document) {
            Document document = (Document) model;
            DocumentDto dto = new DocumentDto();
            dto.setDocumentId(document.getId().toString());
            dto.setDocumentName(document.getFilename());
            dto.setDocumentType(document.getFiletype());
            dto.setMime(document.getMime());
            dto.setDocumentUrl(document.getUrl());
            return dto;
        }
        return null;
    }

}
