package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.common.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    boolean uploadFile(String destPath, String destFileName, byte[] fileBytes);

    DocumentDto uploadFileById(MultipartFile file, String documentDtoStr) throws IOException;

    String uploadCommonFile(MultipartFile file) throws IOException;

    String uploadTempFile(MultipartFile file) throws IOException;
}
