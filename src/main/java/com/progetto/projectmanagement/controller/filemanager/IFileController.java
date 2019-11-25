package com.progetto.projectmanagement.controller.filemanager;

import com.progetto.projectmanagement.domain.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IFileController {
    ResponseModel saveUploadedFile(List<MultipartFile> files, HttpServletResponse isCompleted);
}
