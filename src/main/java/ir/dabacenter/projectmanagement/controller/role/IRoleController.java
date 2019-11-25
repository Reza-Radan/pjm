package ir.dabacenter.projectmanagement.controller.role;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.role.Role;

import javax.servlet.http.HttpServletResponse;

public interface IRoleController {

    ResponseModel addRole(Role role, HttpServletResponse httpServletResponse);
    ResponseModel getRoleList(HttpServletResponse httpServletResponse);
}
