package ir.dabacenter.projectmanagement.service.filemanager;

import ir.dabacenter.projectmanagement.domain.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileManagerService {

    public ResultModel saveUploadedFile(List<MultipartFile> files);
}
