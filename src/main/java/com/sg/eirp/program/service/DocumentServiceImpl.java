package com.sg.eirp.program.service;

import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(rollbackFor = Exception.class)
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    @Override
    public Optional<Document> getByDocumentId(UUID documentId) {
        return documentRepo.findById(documentId);
    }

    @Override
    public List<Document> getDocumentsByReference(String referenceTable, String referenceId) {
        List<Document> resultList = new ArrayList<>();
        if (referenceTable == null || referenceTable.isEmpty() || referenceId == null || referenceId.isEmpty()) {
            return resultList;
        }

        documentRepo.findByReferenceId(referenceTable, referenceId).ifPresent(resultList::addAll);
        return resultList;
    }
}
