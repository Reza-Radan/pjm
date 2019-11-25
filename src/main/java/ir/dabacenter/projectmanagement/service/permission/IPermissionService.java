package ir.dabacenter.projectmanagement.service.permission;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.permission.Permission;

import java.util.UUID;

public interface IPermissionService {

	ResponseModel addPermission(Permission permission);
	ResponseModel getPermission();
	ResponseModel getUserProjectPermission(UUID projectId, UUID memberId);
	ResponseModel getPermissionById(String permissionId,String lang);
	boolean getResourceAccessByCompnent(String component);

}