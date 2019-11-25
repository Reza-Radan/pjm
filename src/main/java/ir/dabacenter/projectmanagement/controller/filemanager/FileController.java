package ir.dabacenter.projectmanagement.controller.filemanager;


import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.service.filemanager.IFileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FileController implements IFileController {

    @Autowired
    public IFileManagerService iFileManagerService;

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel responseModel;




    @Override
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseModel saveUploadedFile(@RequestParam("file") List<MultipartFile> files, HttpServletResponse httpServletResponse) {

        resultModel = iFileManagerService.saveUploadedFile(files);
        makeResponseClear();

        if(resultModel.getError()==0)
          return new ResponseModel("success",httpServletResponse.getStatus(),requirementsProperties.getSuccessful(),"","");
        else
          return new ResponseModel("fail",httpServletResponse.getStatus(),requirementsProperties.getFail(),"",resultModel.getResult());
    }


    public void makeResponseClear(){
        responseModel.setContents(null);
        responseModel.setContent(null);
        responseModel.setSystemError("");
        responseModel.setError("");
        responseModel.setRecordCount(0);
    }
}
