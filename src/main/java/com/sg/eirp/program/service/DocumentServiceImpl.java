package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.mapper.DocumentMapper;
import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.repo.DocumentRepo;
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
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Document getByDocumentId(UUID documentId) {
        return documentRepo.findById(documentId).get();
    }

    @Override
    public Optional<List<DocumentDto>> getDocumentsByReference(String referenceTable, UUID referenceId) {
        if (referenceTable == null || referenceTable.isEmpty() || referenceId == null) {
            return null;
        }

        List<Document> documentList = new ArrayList<>();
        documentRepo.findByReferenceId(referenceTable, referenceId).ifPresent(documentList::addAll);

        return documentMapper.entitiesToDtos(Optional.ofNullable(documentList));
    }

    @Override
    public List<DocumentDto> saveAll(List<Document> documentList) {
        if (documentList == null || documentList.isEmpty()) {
            return null;
        }

        Iterable<Document> documentIterable = documentRepo.saveAll(documentList);

        // convert the iterable to list
        List<Document> documents = StreamSupport.stream(documentIterable.spliterator(), false)
                .collect(Collectors.toList());

        return documentMapper.entitiesToDtos(Optional.ofNullable(documents)).get();
    }

    @Override
    public List<DocumentDto> saveAll(List<DocumentDto> documentDtoList, String referenceTable, UUID referenceId) {
        if (documentDtoList == null || documentDtoList.isEmpty()) {
            return null;
        }

        List<Document> documentList = documentMapper.dtosToEntities(Optional.ofNullable(documentDtoList)).get();

        if (documentList != null && referenceTable != null && referenceId != null) {
            for (Document doc : documentList) {
                doc.setReferenceTable(referenceTable);
                doc.setReferenceId(referenceId);
            }
        }

        return saveAll(documentList);
    }
}
