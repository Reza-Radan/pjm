package com.progetto.projectmanagement.service.permission;

import com.progetto.projectmanagement.domain.permission.Permission;
import com.progetto.projectmanagement.domain.ResponseModel;

import java.util.UUID;

public interface IPermissionService {

	ResponseModel addPermission(Permission permission);
	ResponseModel getPermission();
	ResponseModel getUserProjectPermission(UUID projectId, UUID memberId);
	ResponseModel getPermissionById(String permissionId,String lang);
	boolean getResourceAccessByCompnent(String component);

}