package com.progetto.projectmanagement.controller.role;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.role.Role;
import com.progetto.projectmanagement.service.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

//import ir.dabacenter.projectmanagement.domain.role.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController implements IRoleController , ErrorController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tag = "RoleController";

    @Autowired
    RoleService roleService;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    @Override
    @RequestMapping(value = "/addRole" , method = RequestMethod.POST)
    public ResponseModel addRole(@RequestBody Role role, HttpServletResponse httpServletResponse) {

        logger.warn(tag , "addRole");
            makeResponseClear();
            responseModel = roleService.addRole(role);
            responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @Override
    @RequestMapping(value = "/getRoles" , method = RequestMethod.GET)
    public ResponseModel getRoleList(HttpServletResponse httpServletResponse) {
        return roleService.getRoleList();
    }

    @Override
    public String getErrorPath() {
        return null;
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseModel handleException(MethodArgumentNotValidException exception, HttpServletResponse response) {
        makeResponseClear();
        responseModel.setStatus(response.getStatus());
        responseModel.setSystemError(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return responseModel;// new ResponseModel(responseStatus, "fail", exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(), content);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseModel setExceptio(HttpMessageNotReadableException exceptio ,HttpServletResponse response){
        makeResponseClear();
        try {
            responseModel.setSystemError(exceptio.getCause().getMessage().substring(0,exceptio.getCause().getMessage().indexOf("at")));
           // return new ResponseModel(responseStatus, "fail", exceptio.getCause().getMessage().substring(0,exceptio.getCause().getMessage().indexOf("at")), content);
        }catch (Exception e){
//            return new ResponseModel(responseStatus, "0", exceptio.getLocalizedMessage(),content);
            responseModel.setSystemError(exceptio.getLocalizedMessage());
        }
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
