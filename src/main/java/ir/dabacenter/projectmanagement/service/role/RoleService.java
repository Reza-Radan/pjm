package ir.dabacenter.projectmanagement.service.role;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.role.Role;
import ir.dabacenter.projectmanagement.domain.role.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class RoleService implements IRoleService {

    private String tag  = "RoleService";
    Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ResultModel resultModel,databaseResultmode;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    RoleValidator roleValidator;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Override
    @Async
    public ResponseModel  addRole(Role role) {

        //addRoleAsync(role).getNow(responseModel);
        return addRoleAsync(role).getNow(responseModel);
    }

    @Override
    public ResponseModel getRoleList() {
        return roleRepository.getRoleList();
    }

    @Async
    public  CompletableFuture<ResponseModel>  addRoleAsync(Role role){

         /*
         set all Value to null to avoid data of last transaction
         */
        responseModel.setResult(requirementsProperties.getFail());
        responseModel.setError(null);
        responseModel.setSystemError(null);
        responseModel.setContent(null);
        responseModel.setContents(null);

        resultModel = roleValidator.validate(role);
        if(resultModel.getError() == 0){
           if (roleValidator.permissionExistence(role)) {

               databaseResultmode = roleRepository.addrole(role);
               if (databaseResultmode.getError() == 0) {
                   responseModel.setResult(databaseResultmode.getResult());
               } else {
                   responseModel.setSystemError(databaseResultmode.getResult());
                   responseModel.setResult(requirementsProperties.getFail());
               }
           }else{
               /*
                permission name which is entered by user is not exist
                */
               responseModel.setError(" permission name not exist ");
               responseModel.setResult(requirementsProperties.getFail());
           }
        }else {
            responseModel.setError(resultModel.getResult());
            responseModel.setResult(requirementsProperties.getFail());
        }

        logger.info(tag+" result of add role "+resultModel.getResult()+" error "+resultModel.getError());

        return CompletableFuture.completedFuture(responseModel);
    }

}