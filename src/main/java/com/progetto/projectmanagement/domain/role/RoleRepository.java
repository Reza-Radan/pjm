package com.progetto.projectmanagement.domain.role;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.*;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleRepository {

    private final String id = "id";
    private final String nameEn  = "nameEn";
    private final String permissionUuid  = "permissionUuid";
    private final String nameFa  = "nameFa";
    private final String permissionName  = "permissionName";
    private final String modifyTime = "modifytime";
    private final String tag = "RoleRepository";

    ResultSet resultset ;
    Logger logger = LoggerFactory.getLogger(RoleRepository.class);
    Batch batch;
    QueryBuilder queryBuilder;
    Select.Where select,selectPermission;
    Select selectallow,SelectallowPermission;
    Update.Where update;
    Insert insertProject;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ConfigSession configSession;

    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;


    public ResultModel addrole(Role role){
        logger.error(tag+" addrole");
        try {
            UUID uuid = null;
            selectallow = QueryBuilder.select(id).
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getRole()).allowFiltering();

            select = selectallow.where(QueryBuilder.eq(nameEn,role.getnameEn()));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset)
                 uuid = row.getUUID(id);


            if(uuid == null)
               uuid = UUID.randomUUID();

            System.out.println(tag+" uuid "+uuid +" permission name : "+role.getPermissionName());
            update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getRole()).
                    with()
                    .and(QueryBuilder.set(nameEn, role.getnameEn()))
                    .and(QueryBuilder.set(nameFa, role.getnameFa()))
                    .and(QueryBuilder.set(permissionName, role.getPermissionName()))
                    .and(QueryBuilder.set(modifyTime, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(permissionUuid, role.getPermissionUuid()))
                    .where(QueryBuilder.eq(id, uuid));
//
//           resultset = configSession.getSession().execute(select);
//
//            if(resultset.all().size() == 0) {
                resultset = configSession.getSession().execute(update);
//            }

            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());

            System.out.println(tag+" result set value "+resultset);
        }catch (Exception e){
            logger.error(tag+" error in add role : "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }
        return resultModel;
    }

    public ResponseModel getRoleList(){

        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getRole()).allowFiltering();
        resultset = configSession.getSession().execute(selectallow);
        for (Row row : resultset) {
            role.setId(row.getUUID(id));
            role.setnameEn(row.getString(nameEn));
            role.setnameFa(row.getString(nameFa));
            role.setModifyTime(row.getLong(modifyTime));
            role.setPermissionName(row.getString(permissionName));
            role.setPermissionUuid(row.getString(permissionUuid));
            roleList.add(role);
        }

        if(roleList.size() > 0) {
            responseModel.setError(null);
            responseModel.setContents(roleList);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getSuccessful());
            responseModel.setRecordCount(roleList.size());
        }else{
            responseModel.setError(requirementsProperties.noAvailableData());
            responseModel.setContents(null);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    public boolean isPermissionNameExist(Role role){

        /*
         validate the permission name , is there any field in permission table with this name
         */
        UUID checkUUID = null;
        SelectallowPermission = QueryBuilder.select(id).from(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).allowFiltering();
        selectPermission = SelectallowPermission.where(QueryBuilder.eq("name",role.getPermissionName()));
        resultset = configSession.getSession().execute(selectPermission);
        for (Row row : resultset)
            checkUUID = row.getUUID(id);

        if (checkUUID == null)
            return false;
        else
            return true;
    }


    public List<String> getPermissionIdByRoleId(UUID roleId) {

        try {
            String permissionId = null;
            String roleName = null;
            logger.info(tag + " roleId in getPermissionIdByRoleId : " + roleId);
            List<String> nameUUIDValue = new ArrayList<>();
            selectallow = QueryBuilder.select().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getRole()).allowFiltering();

            select = selectallow.where(QueryBuilder.eq(id, roleId));
            resultset = configSession.getSession().execute(select);
            for (Row row : resultset) {
                permissionId = row.getString(permissionUuid);
                roleName = row.getString(nameEn);

            }
            logger.info(" permission id in getPermissionIdByRoleId " + permissionId + " role name : " + roleName+" permission string "+permissionId);
//
            nameUUIDValue.add(0, String.valueOf(permissionId));
            nameUUIDValue.add(1, roleName);

            return nameUUIDValue;
        } catch (Exception e){
            logger.error(tag + " erro in getPermissionIdByRoleId "+e);
            e.printStackTrace();
            return null;
        }


    }


//    public ResultModel addrolee(Role role) {
//        UUID uuid = UUID.randomUUID();
//
//        try {
//            insertProject = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getRole())
//                    .value(nameEn, role.getnameEn())
//                    .value(nameFa, role.getnameFa())
//                    .value(modifyTime, new Date().getTime())
//                    .value(id, uuid);
//
//            resultset = configSession.getSession().execute(insertProject);
//            resultModel.setError(0);
//            resultModel.setResult(requirementsProperties.getSuccessful());
//
//            System.out.println(tag + " result set value " + resultset);
//        } catch (Exception e) {
//            logger.error(tag + " error in add role : " + e);
//            resultModel.setError(1);
//            resultModel.setResult(e.toString());
//        }
//        return resultModel;
//    }


}
