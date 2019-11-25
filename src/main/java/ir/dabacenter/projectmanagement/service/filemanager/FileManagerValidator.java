package ir.dabacenter.projectmanagement.service.filemanager;

public class FileManagerValidator {


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
