package com.progetto.projectmanagement.service.filemanager;

import com.progetto.projectmanagement.domain.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileManagerService {

    public ResultModel saveUploadedFile(List<MultipartFile> files);
}
