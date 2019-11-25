package com.progetto.projectmanagement.service.role;

import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.role.Role;

public interface IRoleService {

    ResponseModel addRole(Role role);
    ResponseModel getRoleList();

}