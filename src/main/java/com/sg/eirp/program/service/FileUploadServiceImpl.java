package com.sg.eirp.program.service;

import com.sg.eirp.common.aws.s3.S3UploadService;
import com.sg.eirp.common.dto.common.DocumentDto;
import com.sg.eirp.common.exceptions.BusinessValidationException;
import com.sg.eirp.program.util.CommonConstants;
import com.sg.eirp.program.util.CommonUtil;
import com.sg.eirp.program.util.DocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${aws.accessId}")
    private String awsAccessId;

    @Value("${aws.accessSecret}")
    private String awsAccessSecret;

    @Value("${aws.s3.region}")
    private String s3Region;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private S3UploadService s3UploadService;

    @Autowired
    private DocumentService documentService;

    @PostConstruct
    public void afterInit() {
        System.setProperty("aws.accessKeyId", awsAccessId);
        System.setProperty("aws.secretAccessKey", awsAccessSecret);
        s3UploadService = new S3UploadService(s3Region);
    }

    @Override
    public boolean uploadFile(String destPath, String destFileName, byte[] fileBytes) {
        return s3UploadService.uploadFile(bucketName, destPath, destFileName, fileBytes);
    }

    @Override
    public DocumentDto uploadFileById(MultipartFile file, String documentDtoStr) throws IOException {
        DocumentDto documentDto = null;
        if (documentDtoStr != null) {
            logger.info(documentDtoStr);
            ObjectMapper mapper = new ObjectMapper();
            documentDto = mapper.readValue(documentDtoStr, DocumentDto.class);
        }

        if (documentDto == null || documentDto.getReferenceId() == null || documentDto.getUploadType() == null) {
            throw new BusinessValidationException("Invalid input", "Invalid input");
        }

        String id = documentDto.getReferenceId();
        String uploadType = documentDto.getUploadType();

        // id must be a valid uuid
        UUID uuid = CommonUtil.convertIdtoUUID(id);
        if (uuid == null) {
            throw new BusinessValidationException("Invalid input", "Valid UUID is required when uploading documents");
        }

        String destinationPath = CommonConstants.COMMON_FOLDER_NAME + CommonConstants.DIRECTORY_SEPARATOR + id;

        String uploadedUrl = processUploadFile(destinationPath, file);
        if (uploadedUrl != null) {
            // uploaded to s3 server successfully, insert data into database
            String referenceTable = DocumentUtil.getFileUploadTypeMapping(uploadType);
            if (referenceTable == null) {
                throw new BusinessValidationException("Invalid input", "Invalid uploadType");
            }

            // insert document
            documentDto.setDocumentName(file.getOriginalFilename());
            documentDto.setMime(file.getContentType());
            documentDto.setDocumentUrl(uploadedUrl);
            documentDto = documentService.save(documentDto);
        } else {
            documentDto = null;
        }

        return documentDto;
    }

    @Override
    public String uploadCommonFile(MultipartFile file) throws IOException {
        return processUploadFile(CommonConstants.COMMON_FOLDER_NAME, file);
    }

    @Override
    public String uploadTempFile(MultipartFile file) throws IOException {
        String tempFileUrl = processUploadFile(CommonConstants.TEMP_FOLDER_NAME, file);
        if (tempFileUrl != null && !tempFileUrl.isEmpty()) {
            tempFileUrl = CommonConstants.S3_URL + tempFileUrl;
        }
        return tempFileUrl;
    }

    private String processUploadFile(String destinationPath, MultipartFile file) throws IOException {
        if (file == null) {
            throw new BusinessValidationException("Invalid input", "Multipart file must be valid.");
        }

        // if destinationPath is not set, redirect to temp folder
        if (destinationPath == null || destinationPath.isEmpty()) {
            destinationPath = CommonConstants.TEMP_FOLDER_NAME;
        }

        String filename = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        String url = null;
        if(uploadFile(destinationPath + CommonConstants.DIRECTORY_SEPARATOR, filename, fileBytes)){
            url = destinationPath + CommonConstants.DIRECTORY_SEPARATOR + filename;
        }

        return url;
    }

}
