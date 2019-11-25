package ir.dabacenter.projectmanagement.service.permission;


import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.permission.Permission;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionValidator {


    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel validate(Permission permission){

        resultModel.setError(0);
        resultModel.setResult(null);

        if(!validateField(permission.getName())){
            resultModel.setError(1);
            resultModel.setResult(" name is a " + resultModel.getErrorTextByLanguage(permission.getLang(),"REQUIREDFIELD"));
        }else{
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public <T> boolean validateField(T field){
        if(field!=null)
            if (field=="")
                return false;
            else
                return true;
        else
            return false;

    }
}
