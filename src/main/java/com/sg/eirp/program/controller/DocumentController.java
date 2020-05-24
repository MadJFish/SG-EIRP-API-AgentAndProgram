package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.program.mapper.DocumentMapper;
import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.service.DocumentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@Validated
@RequestMapping("/api/document")
public class DocumentController extends BaseController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<DocumentDto> getDocumentByUUIDRequest(@RequestParam String documentId) {
        try {
            if (documentId == null || documentId.isEmpty()) {
                return null;
            }

            Document document = documentService.getByDocumentId(UUID.fromString(documentId));

            DocumentMapper mapper = new DocumentMapper();
            DocumentDto documentDto = null;
            if (document != null) {
                documentDto = (DocumentDto) mapper.entityToDto(document);
            }

            return responseDtoOK(documentDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
