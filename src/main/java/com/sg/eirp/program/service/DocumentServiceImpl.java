package com.sg.eirp.program.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.mapper.DocumentMapper;
import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.repo.DocumentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional(rollbackFor = Exception.class)
@Service
public class DocumentServiceImpl implements DocumentService {

    private Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

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

        logger.info("Reference Table: " + referenceTable);
        logger.info("Reference id: " + referenceId.toString());

        Optional<List<Document>> documentListOptional = documentRepo.findByReferenceId(referenceTable, referenceId);

        return documentMapper.entitiesToDtos(documentListOptional);
    }

    @Override
    public DocumentDto save(DocumentDto documentDto) throws JsonProcessingException {
        if (documentDto == null) {
            return null;
        }

        Document doc = documentMapper.dtoToEntity(documentDto);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(doc);

        logger.info("Document: " + json);

        return documentMapper.entityToDto(documentRepo.save(doc));
    }

    @Override
    public DocumentDto save(DocumentDto documentDto, String referenceTable, UUID referenceId) {
        if (documentDto == null || referenceTable == null || referenceId == null) {
            return null;
        }

        Document document = documentMapper.dtoToEntity(documentDto);
        if (document == null) {
            return null;
        }

        document.setReferenceTable(referenceTable);
        document.setReferenceId(referenceId);

        return documentMapper.entityToDto(documentRepo.save(document));
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
