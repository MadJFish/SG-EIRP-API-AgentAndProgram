package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Document;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DocumentMapper extends DtoEntityMapper<DocumentDto, Document> {

    @Override
    public Document dtoToEntity(DocumentDto documentDto) {
        if (documentDto == null) {
            return null;
        }
        Document document = new Document();

        if (documentDto.getDocumentId() != null) {
            document.setId(UUID.fromString(documentDto.getDocumentId()));
        }

        document.setFilename(documentDto.getDocumentName());

        document.setFiletype(documentDto.getDocumentType());

        document.setMime(documentDto.getMime());

        document.setUrl(documentDto.getDocumentUrl());

        return document;
    }

    @Override
    public DocumentDto entityToDto(Document document) {
        if (document == null) {
            return null;
        }

        DocumentDto dto = new DocumentDto();

        if (document.getId() != null) {
            dto.setDocumentId(document.getId().toString());
        }

        dto.setDocumentName(document.getFilename());

        dto.setDocumentType(document.getFiletype());

        dto.setMime(document.getMime());

        dto.setDocumentUrl(document.getUrl());

        return dto;
    }
}
