package com.sg.eirp.program.service;

import com.sg.eirp.program.model.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentService {
    Optional<Document> getByDocumentId(UUID documentId);

    List<Document> getDocumentsByReference(String referenceTable, String referenceId);
}
