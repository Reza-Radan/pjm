package com.progetto.projectmanagement.controller.role;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.role.Role;

import javax.servlet.http.HttpServletResponse;

public interface IRoleController {

    ResponseModel addRole(Role role, HttpServletResponse httpServletResponse);
    ResponseModel getRoleList(HttpServletResponse httpServletResponse);
}
