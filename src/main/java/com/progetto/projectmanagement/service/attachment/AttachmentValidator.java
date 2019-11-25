package com.progetto.projectmanagement.service.attachment;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.attachment.Attachment;
import org.springframework.beans.factory.annotation.Autowired;

public class AttachmentValidator<T> {


    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel validate(Attachment attachment){

        if(!validateField(attachment.getProject_id())) {
            resultModel.setError(1);
            resultModel.setResult(" project_id " + resultModel.getErrorTextByLanguage(attachment.getLang(),"REQUIREDFIELD"));
        }else if(!validateField(attachment.getName())){
            resultModel.setError(1);
            resultModel.setResult(" name " + resultModel.getErrorTextByLanguage(attachment.getLang(),"REQUIREDFIELD"));
        }else if(!validateField(attachment.getPath())){
            resultModel.setError(1);
            resultModel.setResult(" path " + resultModel.getErrorTextByLanguage(attachment.getLang(),"REQUIREDFIELD"));
        }else if(!validateField(attachment.getExtention())){
            resultModel.setError(1);
            resultModel.setResult(" extension " + resultModel.getErrorTextByLanguage(attachment.getLang(),"REQUIREDFIELD"));
        }else if(!validateField(attachment.getSize())){
            resultModel.setError(1);
            resultModel.setResult(" size " + resultModel.getErrorTextByLanguage(attachment.getLang(),"REQUIREDFIELD"));
        }else {
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
