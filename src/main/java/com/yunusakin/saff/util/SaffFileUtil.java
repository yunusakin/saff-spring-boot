package com.yunusakin.saff.util;

import com.yunusakin.saff.util.data.FileUploadPojo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SaffFileUtil {

    public static FileUploadPojo saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("filestore");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        Path filePath;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return new FileUploadPojo(filePath.toString(),fileCode);
    }

    private static Path foundFile;

    public static Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("filestore");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }
        return null;
    }
}
