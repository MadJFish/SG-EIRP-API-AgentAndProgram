package com.sg.eirp.program.service;

public interface FileUploadService {

    public boolean uploadFile(String destPath,String destFileName,byte[] fileBytes);
}
