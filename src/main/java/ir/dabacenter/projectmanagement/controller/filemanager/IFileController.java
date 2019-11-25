package ir.dabacenter.projectmanagement.controller.filemanager;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IFileController {
    ResponseModel saveUploadedFile(List<MultipartFile> files, HttpServletResponse isCompleted);
}
