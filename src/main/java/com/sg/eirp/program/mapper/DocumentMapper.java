package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.util.CommonConstants;
import com.sg.eirp.program.util.DocumentUtil;
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

        String url = documentDto.getDocumentUrl();
        if (url != null && url.contains(CommonConstants.S3_URL)) {
            url = url.replace(CommonConstants.S3_URL, CommonConstants.EMPTY_STRING);
        }
        document.setUrl(url);

        if (documentDto.getReferenceId() != null) {
            document.setReferenceId(UUID.fromString(documentDto.getReferenceId()));
        }

        document.setReferenceTable(DocumentUtil.getFileUploadTypeMapping(documentDto.getUploadType()));

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

        String url = document.getUrl();
        if (url != null) {
            url = CommonConstants.S3_URL + url;
        }
        dto.setDocumentUrl(url);

        if (document.getReferenceId() != null) {
            dto.setReferenceId(document.getReferenceId().toString());
        }

        dto.setUploadType(DocumentUtil.getReferenceTableMapping(document.getReferenceTable()));

        return dto;
    }
}
