package com.sg.eirp.program.controller;

import com.sg.eirp.common.controller.base.BaseController;
import com.sg.eirp.common.dto.base.BaseResponseDto;
import com.sg.eirp.program.dto.DocumentDto;
import com.sg.eirp.program.mapper.DocumentMapper;
import com.sg.eirp.program.model.Document;
import com.sg.eirp.program.service.DocumentService;
import com.sg.eirp.program.util.DocumentUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

            Document document = null;
            documentService.getByDocumentId(UUID.fromString(documentId))
                    .ifPresent(document::equals);

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

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<DocumentDto> uploadDocument(@RequestParam("file") MultipartFile file, @RequestBody DocumentDto documentDto) {
        // check if all form parameters are provided
        if (file == null || documentDto == null) {
            // return Response.status(400).entity("Invalid form data").build();
            return null;
        }

        // create our destination folder, if it not exists
        try {
            DocumentUtil.createFolderIfNotExists(DocumentUtil.UPLOAD_FOLDER);
        } catch (SecurityException se) {
            se.printStackTrace();
            // return Response.status(500).entity("Can not create destination folder on server").build();
            return null;
        }

        // save file to server
        String uploadedFileLocation = DocumentUtil.UPLOAD_FOLDER + documentDto.getDocumentName();
        try {
            DocumentUtil.saveToFile(file, uploadedFileLocation);
        } catch (IOException e) {
            e.printStackTrace();
            // return Response.status(500).entity("Can not save file").build();
            return null;
        }

       documentDto.setDocumentUrl(uploadedFileLocation);

        // return Response.status(200).entity("File saved to " + uploadedFileLocation).build();
        return responseDtoOK(documentDto);
    }

}
