package ir.dabacenter.projectmanagement.service.role;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.role.Role;

public interface IRoleService {

    ResponseModel addRole(Role role);
    ResponseModel getRoleList();

}