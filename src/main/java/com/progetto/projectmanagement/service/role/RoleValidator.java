package com.progetto.projectmanagement.service.role;

import com.progetto.projectmanagement.domain.role.RoleRepository;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleValidator {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel validate(Role role){

        resultModel.setError(0);
        resultModel.setResult(null);

        if(!validateField(role.getnameFa())){
            resultModel.setError(1);
            resultModel.setResult(" nameFa " + resultModel.getErrorTextByLanguage(role.getLang(),"REQUIREDFIELD"));
        } else if(!validateField(role.getnameEn())){
            resultModel.setError(1);
            resultModel.setResult(" nameEn " + resultModel.getErrorTextByLanguage(role.getLang(),"REQUIREDFIELD"));
        } else if(!validateField(role.getPermissionName())){
            resultModel.setError(1);
            resultModel.setResult(" permissionName " + resultModel.getErrorTextByLanguage(role.getLang(),"REQUIREDFIELD"));
        } else if(!validateField(role.getPermissionUuid())){
            resultModel.setError(1);
            resultModel.setResult(" permissionUuid " + resultModel.getErrorTextByLanguage(role.getLang(),"REQUIREDFIELD"));
        } else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public boolean permissionExistence(Role role){
        return roleRepository.isPermissionNameExist(role);
    }

    public <T> boolean validateField(T field){
        if(field != null)
            if (field == "")
                return false;
            else
                return true;
        else
            return false;

    }

}
