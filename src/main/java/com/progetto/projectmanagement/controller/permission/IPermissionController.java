package com.progetto.projectmanagement.controller.permission;

import com.progetto.projectmanagement.domain.permission.Permission;
import com.progetto.projectmanagement.domain.ResponseModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public interface IPermissionController {

    ResponseModel addPermission(Permission permission, HttpServletResponse httpServletResponse);
    ResponseModel getPermission(HttpServletResponse httpServletResponse);
    ResponseModel getPermissionById(@RequestParam String id,String lang, HttpServletResponse httpServletResponse);
}
