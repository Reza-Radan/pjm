package ir.dabacenter.projectmanagement.controller.permission;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.permission.Permission;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public interface IPermissionController {

    ResponseModel addPermission(Permission permission, HttpServletResponse httpServletResponse);
    ResponseModel getPermission(HttpServletResponse httpServletResponse);
    ResponseModel getPermissionById(@RequestParam String id,String lang, HttpServletResponse httpServletResponse);
}
