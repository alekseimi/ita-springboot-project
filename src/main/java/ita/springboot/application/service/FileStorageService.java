package ita.springboot.application.service;

import ita.springboot.application.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private String path="c:\\uploads\\";

    public void uploadFile(MultipartFile file){
        if (file.isEmpty()){
            throw new StorageException("Failed to store empty file");
        } try {
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e){

            String msg = String.format("Failed to store file", file.getName());

            throw new StorageException(msg, e);
        }
    }

}
