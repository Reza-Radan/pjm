package ir.dabacenter.projectmanagement.controller.permission;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.permission.Permission;
import ir.dabacenter.projectmanagement.service.permission.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/permission")
public class PermissionController implements IPermissionController {


    @Autowired
    PermissionService permissionService;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;

    private String tag  = "PermissionController";
    Logger logger = LoggerFactory.getLogger(PermissionService.class);



    @Override
    @RequestMapping(value = "/addPermission" ,method = RequestMethod.POST)
    public ResponseModel addPermission(@RequestBody Permission permission, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = permissionService.addPermission(permission);
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @Override
    @RequestMapping(value = "/getPermission" , method = RequestMethod.GET)
    public ResponseModel getPermission(HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = permissionService.getPermission();
        responseModel.setStatus(httpServletResponse.getStatus());
//        if(permissionService.getResourceAccessByCompnent("project")) {
//            logger.info("value : true");
//            responseModel = permissionService.getPermission();
//            responseModel.setStatus(httpServletResponse.getStatus());
//        }else{
//            logger.info("value : false ");
//            responseModel.setStatus(httpServletResponse.getStatus());
//            responseModel.setResult(requirementsProperties.getFail());
//            responseModel.setSystemError(requirementsProperties.accessDenied());
//        }
         return responseModel;
    }

    @Override
    @RequestMapping(value = "/permissionById" , method = RequestMethod.GET)
    public ResponseModel getPermissionById(@RequestParam String id,String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info(" id received in getPermissionById  : "+id);
        responseModel =  permissionService.getPermissionById(id,lang);
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    public void makeResponseClear(){
        responseModel.setContents(null);
        responseModel.setContent(null);
        responseModel.setSystemError("");
        responseModel.setError("");
        responseModel.setRecordCount(0);
    }
}
