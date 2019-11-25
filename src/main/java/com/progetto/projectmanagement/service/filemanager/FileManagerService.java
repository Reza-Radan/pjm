package com.progetto.projectmanagement.service.filemanager;

import com.progetto.projectmanagement.ProjectmanagementApplication;
import com.progetto.projectmanagement.domain.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManagerService implements IFileManagerService {

    @Autowired
    ProjectmanagementApplication environment;
    boolean isCompleted = false;

    @Autowired
    ResultModel resultModel;

    @Override
    public ResultModel saveUploadedFile(List<MultipartFile> files) {

        final Logger logger = LoggerFactory.getLogger(FileManagerService.class);
        String UPLOADED_FOLDER = environment.getCatalinaBasePath();
        System.out.println(" path in file manager "+UPLOADED_FOLDER);
        for (MultipartFile file : files) {
            System.out.print("getContentType() "+file.getContentType());
            try {
                byte[] bytes = new byte[0];
                bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                System.out.print("getContentType() "+path.toString());
                Files.write(path, bytes);
                resultModel.setResult("successful");
                resultModel.setError(0);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Error in saveUploadedFile "+e);
                resultModel.setResult(e.toString());
                resultModel.setError(1);
            }

        }
        return resultModel;
    }
}