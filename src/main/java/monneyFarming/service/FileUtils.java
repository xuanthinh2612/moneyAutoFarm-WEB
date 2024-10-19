package monneyFarming.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                // Delete all files and subdirectories recursively
                File[] contents = file.listFiles();
                if (contents != null) {
                    for (File f : contents) {
                        deleteFile(f);
                    }
                }
            }
            // Delete the file or empty directory
            if (!file.delete()) {
                System.out.println("Unable to delete image!");
            }
        }
    }

}