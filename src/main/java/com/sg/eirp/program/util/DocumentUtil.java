package com.sg.eirp.program.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class DocumentUtil {
    public static final String UPLOAD_FOLDER = DocumentUtil.class.getResource("resources/").getPath() + "file/";
    public static final int MAX_FILE_SIZE = 999999999;

    /**
     * Utility method to save InputStream data to target location/file
     *
     * @param file
     *            - MultipartFile to be saved
     * @param target
     *            - full path to destination file
     */
    public static void saveToFile(MultipartFile file, String target)
            throws IOException {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                OutputStream out = new FileOutputStream(new File(target));
                BufferedOutputStream stream = new BufferedOutputStream(out);
                stream.write(bytes);
                out.flush();
                out.close();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a folder to desired location if it not already exists
     *
     * @param dirName
     *            - full path to the folder
     * @throws SecurityException
     *             - in case you don't have permission to create the folder
     */
    public static void createFolderIfNotExists(String dirName)
            throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }


}
