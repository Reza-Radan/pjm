package com.progetto.projectmanagement.service.permission;

import com.progetto.projectmanagement.domain.permission.Permission;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.permission.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PermissionService implements IPermissionService {


	private String tag  = "PermissionService";
	Logger logger = LoggerFactory.getLogger(PermissionService.class);

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	ResultModel resultModel,databaseResultmodel;

	@Autowired
	ResponseModel responseModel;

	@Autowired
	PermissionValidator permissionValidator;

	@Autowired
	RequirementsProperties requirementsProperties;

	@Override
	public ResponseModel addPermission(Permission permission) {

		/*
         set all Value to null to avoid data of last transaction
         */
		responseModel.setResult(requirementsProperties.getFail());
		responseModel.setError(null);
		responseModel.setSystemError(null);
		responseModel.setContent(null);
		responseModel.setContents(null);

	    resultModel = permissionValidator.validate(permission);
		  if(resultModel.getError() == 0 ){
			  databaseResultmodel = permissionRepository.addPermission(permission);
			  if(databaseResultmodel.getError() == 0){
				  responseModel.setResult(databaseResultmodel.getResult());
			  }else{
				  responseModel.setResult(requirementsProperties.getFail());
				  responseModel.setSystemError(databaseResultmodel.getResult());
			  }

		  }else{
			  responseModel.setError(resultModel.getResult());
		  }
		return responseModel;
	}

	@Override
	public ResponseModel getPermission(){
	    return permissionRepository.getPermissionList();
	}

	@Override
	public ResponseModel getUserProjectPermission(UUID projectId, UUID memberId) {return null;}

	@Override
	public ResponseModel getPermissionById(String permissionId,String lang) {
		return permissionRepository.getPermissionById(permissionId,lang);
	}

	@Override
	public boolean getResourceAccessByCompnent(String component){
		return permissionRepository.checkUserResourcePermissionByToken(component);
	}

}