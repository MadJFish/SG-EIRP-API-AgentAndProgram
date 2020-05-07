package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.model.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentService {
    Document getByDocumentId(UUID documentId);

    Optional<List<DocumentDto>> getDocumentsByReference(String referenceTable, UUID referenceId);

    DocumentDto save(DocumentDto documentDto);

    DocumentDto save(DocumentDto documentDto, String referenceTable, UUID referenceId);

    List<DocumentDto> saveAll(List<Document> documentList);

    List<DocumentDto> saveAll(List<DocumentDto> documentDtoList, String referenceTable, UUID referenceId);
}
