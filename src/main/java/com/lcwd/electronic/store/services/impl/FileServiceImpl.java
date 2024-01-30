package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.exceptions.BadApiRequestException;
import com.lcwd.electronic.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        String orginalFileName = file.getOriginalFilename();
        logger.info("File Name : {}", orginalFileName);

        String filename = UUID.randomUUID().toString();
        String extension = orginalFileName.substring(orginalFileName.lastIndexOf('.'));
        String fileNameWithExtention = filename + extension;
        String fullPathWithFileName = path +fileNameWithExtention;

        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")) {
            //file save

            File folder = new File(path);

            if (!folder.exists()) {

                //create the folder
                folder.mkdirs();
            }

            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtention;

        } else {
            throw new BadApiRequestException("File With this " + extension + "Not Allowed");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {

        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
