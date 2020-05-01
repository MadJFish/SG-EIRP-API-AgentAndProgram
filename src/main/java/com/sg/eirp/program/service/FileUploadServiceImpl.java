package com.sg.eirp.program.service;


import com.sg.eirp.common.aws.s3.S3UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Value("${aws.accessId}")
    private String awsAccessId;

    @Value("${aws.accessSecret}")
    private String awsAccessSecret;

    @Value("${aws.s3.region}")
    private String s3Region;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    S3UploadService s3UploadService;

    @PostConstruct
    public void afterInit() {
        System.setProperty("aws.accessKeyId",awsAccessId);
        System.setProperty("aws.secretAccessKey",awsAccessSecret);
        s3UploadService = new S3UploadService(s3Region);
    }


    @Override
    public boolean uploadFile(String destPath, String destFileName, byte[] fileBytes) {
        return s3UploadService.uploadFile(bucketName,destPath,destFileName,fileBytes);
    }
}
