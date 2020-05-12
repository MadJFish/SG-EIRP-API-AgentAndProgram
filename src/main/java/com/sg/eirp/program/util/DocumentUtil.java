package com.sg.eirp.program.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class DocumentUtil {

    public static String getFileUploadTypeMapping(String fileUploadType) {
        if (fileUploadType == null) {
            return null;
        }

        if (fileUploadType.equals("user")) {
            return "tb_user";
        }

        if (fileUploadType.equalsIgnoreCase("agency")) {
            return "tb_agency";
        }

        if (fileUploadType.equalsIgnoreCase("agencyBranch")) {
            return "tb_agency_branch";
        }

        if (fileUploadType.equalsIgnoreCase("agencyLeadership")) {
            return "tb_agency_leadership";
        }

        if (fileUploadType.equalsIgnoreCase("program")) {
            return "tb_program";
        }

        if (fileUploadType.equalsIgnoreCase("leadership")) {
            return "tb_agency_leadership";
        }

        return null;
    }

    public static String getReferenceTableMapping(String referenceTable) {
        if (referenceTable == null) {
            return null;
        }

        if (referenceTable.equalsIgnoreCase("tb_user")) {
            return "user";
        }

        if (referenceTable.equalsIgnoreCase("tb_agency")) {
            return "agency";
        }

        if (referenceTable.equalsIgnoreCase("tb_agency_branch")) {
            return "agencyBranch";
        }

        if (referenceTable.equalsIgnoreCase("tb_agency_leadership")) {
            return "agencyLeadership";
        }

        if (referenceTable.equalsIgnoreCase("tb_program")) {
            return "program";
        }

        if (referenceTable.equalsIgnoreCase("tb_agency_leadership")) {
            return "leadership";
        }

        return null;
    }

}
