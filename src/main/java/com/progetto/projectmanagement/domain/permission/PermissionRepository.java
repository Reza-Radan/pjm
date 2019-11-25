package com.progetto.projectmanagement.domain.permission;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.querybuilder.*;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.service.security.SecurityService;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionRepository {

    private String id = "id" ;
    private final String name = "name";
    private final String description = "description";
    private final String permissionKey = "permissionKey";
    private final String taskComponent = "taskComponent",projectComponent = "projectComponent",
                         issueComponent = "issueComponent",reportComponent = "reportComponent",
                         memberComponent = "memberComponent",meetingComponent = "meetingComponent" ;
    private final String modifyTime = "modifyTime";
    private final String tag = "PermissionRepository";

    ResultSet resultset ;
    Logger logger = LoggerFactory.getLogger(PermissionRepository.class);
    Batch batch;
    QueryBuilder queryBuilder;
    Select.Where select;
    Select selectallow;
    Update.Where update;
    Insert insertProject;
    permissionType permissionObj;
    permissionMeetingType permissionObjMeeting;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ConfigSession configSession;

    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    SecurityService securityService;

    @Autowired
    MemberService memberService;


    public ResultModel addPermission(Permission permission){


        UDTValue taskUDT = null, projectUDT = null , issueUDT = null, memberUDT= null , reportUDT = null, meetingUDT= null ;

        try{
                UUID fetchId = null,receivedID = null,uuid = null;
                selectallow = QueryBuilder.select(id).from(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).allowFiltering();
                select = selectallow.where(QueryBuilder.eq(name,permission.getName()));
                resultset = configSession.getSession().execute(select);

                logger.info(tag + " ID "+permission.getId());


            for (Row row : resultset)
                fetchId = row.getUUID(id);

               if (permission.getTaskComponent() != null){
                   taskUDT = getUdtvalue(permission,"task");
               }
               if (permission.getIssueComponent() != null) {
                   issueUDT = getUdtvalue(permission,"issue");
               }
               if(permission.getReportComponent() != null) {
                   reportUDT = getUdtvalue(permission,"report");
               }
               if (permission.getMemberComponent() != null) {
                   memberUDT = getUdtvalue(permission,"member");
               }
               if (permission.getMeetingComponent() != null) {
                   meetingUDT = getUdtvalue(permission,"meeting");
               }
               if(permission.getProjectComponent() != null) {
                   projectUDT = getUdtvalue(permission,"project");
               }



               // permissionType permissionType = permission.getIssueComponent();
              //
        // System.out.println(tag + " fetchId "+fetchId+" permission.getIssueComponent() "+permissionType.getAdd());

                if(fetchId == null)
                   uuid = UUID.randomUUID();
                else
                   uuid = fetchId ;

                System.out.println(tag+" uuid "+uuid);
                update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).
                         with()
                        .and(QueryBuilder.set(name, permission.getName()))
                        .and(QueryBuilder.set(description, permission.getDescription()))
                        .and(QueryBuilder.set(permissionKey, permission.getPermission_key()))
                        .and(QueryBuilder.set(taskComponent, taskUDT))
                        .and(QueryBuilder.set(projectComponent, projectUDT))
                        .and(QueryBuilder.set(issueComponent, issueUDT))
                        .and(QueryBuilder.set(reportComponent, reportUDT))
                        .and(QueryBuilder.set(memberComponent, memberUDT))
                        .and(QueryBuilder.set(meetingComponent, meetingUDT))
                        .and(QueryBuilder.set(modifyTime, resultModel.getInsertDate()))
                        .where(QueryBuilder.eq(id, uuid));

                int size = resultset.all().size();
                System.out.println(tag+" size : "+size);

              //  if(size == 0)
                    resultset = configSession.getSession().execute(update);

                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

                System.out.println(tag+" result set value "+resultset);
        }catch (Exception e){
            logger.error(tag+" error in add permission : "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }
        return resultModel;
    }

    public UDTValue getUdtvalue(Permission permission,String component){

        UDTValue udtValue = null;
        try {
            UserType coordsType;
            if(component.equalsIgnoreCase("meeting")){
                 coordsType = configSession.getCluster().getMetadata()
                        .getKeyspace(requirementsProperties.getKeyspace())
                        .getUserType("permissionMeetingType");
            }else {
                 coordsType = configSession.getCluster().getMetadata()
                        .getKeyspace(requirementsProperties.getKeyspace())
                        .getUserType("permissionType");
            }

            switch (component){

                case "issue" : {
                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getIssueComponent().getAdd())
                            .setInt("delete", permission.getIssueComponent().getDelete())
                            .setInt("edit", permission.getIssueComponent().getEdit())
                            .setInt("changeStatus", permission.getIssueComponent().getChangeStatus())
                            .setInt("list", permission.getIssueComponent().getList())
                            .setInt("viewPermission", permission.getIssueComponent().getViewPermission());
                    System.out.println("issue add ... "+permission.getIssueComponent().getAdd());
                }break;

                case "task" : {
                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getTaskComponent().getAdd())
                            .setInt("delete", permission.getTaskComponent().getDelete())
                            .setInt("edit", permission.getTaskComponent().getEdit())
                            .setInt("changeStatus", permission.getTaskComponent().getChangeStatus())
                            .setInt("list", permission.getTaskComponent().getList())
                            .setInt("viewPermission", permission.getTaskComponent().getViewPermission());

                    System.out.println("task add ... "+permission.getTaskComponent().getAdd());
                }break;

                case "project" : {
                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getProjectComponent().getAdd())
                            .setInt("delete", permission.getProjectComponent().getDelete())
                            .setInt("edit", permission.getProjectComponent().getEdit())
                            .setInt("changeStatus", permission.getProjectComponent().getChangeStatus())
                            .setInt("list", permission.getProjectComponent().getList())
                            .setInt("viewPermission", permission.getProjectComponent().getViewPermission());

                    System.out.println("project add ... "+permission.getReportComponent().getAdd()+" Edit() "+permission.getProjectComponent().getEdit());
                }break;

                case "report" : {
                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getReportComponent().getAdd())
                            .setInt("delete", permission.getReportComponent().getDelete())
                            .setInt("edit", permission.getReportComponent().getEdit())
                            .setInt("changeStatus", permission.getReportComponent().getChangeStatus())
                            .setInt("list", permission.getReportComponent().getList())
                            .setInt("viewPermission", permission.getReportComponent().getViewPermission());
                    System.out.println("report add ... "+permission.getReportComponent().getAdd()+" Edit() "+
                                        permission.getReportComponent().getEdit() + " delete "+permission.getReportComponent().getDelete()+
                                      "change status" +permission.getReportComponent().getChangeStatus()+" list : "+permission.getReportComponent().getList());
                }break;

                case "meeting" : {

                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getMeetingComponent().getAdd())
                            .setInt("delete", permission.getMeetingComponent().getDelete())
                            .setInt("edit", permission.getMeetingComponent().getEdit())
                            .setInt("changeStatus", permission.getMeetingComponent().getChangeStatus())
                            .setInt("list", permission.getMeetingComponent().getList())
                            .setInt("viewPermission", permission.getMeetingComponent().getViewPermission())
                            .setInt("invitation", permission.getMeetingComponent().getInvitation());

                    System.out.println("meeting add ... "+permission.getMeetingComponent().getAdd());
                }break;

                case "member" : {
                    udtValue = coordsType.newValue()
                            .setInt("add", permission.getMemberComponent().getAdd())
                            .setInt("delete", permission.getMemberComponent().getDelete())
                            .setInt("edit", permission.getMemberComponent().getEdit())
                            .setInt("changeStatus", permission.getMemberComponent().getChangeStatus())
                            .setInt("list", permission.getMemberComponent().getList())
                            .setInt("viewPermission", permission.getMemberComponent().getViewPermission());

                    System.out.println("member add ... "+permission.getMemberComponent().getAdd());
                }break;
            }
        }catch (Exception e ){
            logger.error("Error in permission repository getUdtvalue "+e);
            return udtValue;
        }
        return udtValue;
    }

    public ResponseModel getPermissionList(){

        Permission permission = new Permission();
        List<Permission> permissionList = new ArrayList<>();
        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).allowFiltering();
        resultset = configSession.getSession().execute(selectallow);

        for (Row row : resultset) {
                System.out.println("each row : " + " row "+row+"");
                permission.setId(row.getUUID(id));
                permission.setName(row.getString(name));
                permission.setPermission_key(row.getInt(permissionKey));
                permission.setDescription(row.getString(description));
                permission.setIssueComponent(getPermissionType(row.getUDTValue(issueComponent)));
                permission.setTaskComponent(getPermissionType(row.getUDTValue(taskComponent)));
                permission.setProjectComponent(getPermissionType(row.getUDTValue(projectComponent)));
                permission.setReportComponent(getPermissionType(row.getUDTValue(reportComponent)));
                permission.setMemberComponent(getPermissionType(row.getUDTValue(memberComponent)));
                permission.setMeetingComponent(getPermissionMeetingType(row.getUDTValue(meetingComponent)));

                permissionList.add(permission);
        }

        if(permissionList.size() > 0) {
            responseModel.setError(null);
            responseModel.setContents(permissionList);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getSuccessful());
            responseModel.setRecordCount(permissionList.size());
        }else{
            responseModel.setError(requirementsProperties.noAvailableData());
            responseModel.setContents(null);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }

    private permissionType getPermissionType(UDTValue component){

        logger.info("component "+component.getInt("changeStatus"));
        permissionObj= new permissionType();
        permissionObj.setAdd(component.getInt("add"));
        permissionObj.setDelete(component.getInt("delete"));
        permissionObj.setEdit(component.getInt("edit"));
        permissionObj.setChangeStatus(component.getInt("changeStatus"));
        permissionObj.setList(component.getInt("list"));
        permissionObj.setViewPermission(component.getInt("viewPermission"));

       return permissionObj;
    }

    private permissionMeetingType getPermissionMeetingType(UDTValue component){

        logger.info("component "+component.getInt("changeStatus"));
        permissionObjMeeting= new permissionMeetingType();
        permissionObjMeeting.setAdd(component.getInt("add"));
        permissionObjMeeting.setDelete(component.getInt("delete"));
        permissionObjMeeting.setEdit(component.getInt("edit"));
        permissionObjMeeting.setChangeStatus(component.getInt("changeStatus"));
        permissionObjMeeting.setList(component.getInt("list"));
        permissionObjMeeting.setViewPermission(component.getInt("viewPermission"));
        permissionObjMeeting.setInvitation(component.getInt("invitation"));


        return permissionObjMeeting;
    }

    public ResponseModel getPermissionById(String permissionId,String lang){

        //UUID uuid = UUID.randomUUID();
        UUID permissionUUID = UUID.fromString(permissionId);

        Permission permission = new Permission();
        List<Permission> permissionList = new ArrayList<>();
        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).allowFiltering();
        select = selectallow.where(QueryBuilder.eq(id,permissionUUID));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset) {
            System.out.println("each row : " + " row "+row+"");
            permission.setId(row.getUUID(id));
            permission.setName(row.getString(name));
            permission.setPermission_key(row.getInt(permissionKey));
            permission.setDescription(row.getString(description));
            permission.setIssueComponent(getPermissionType(row.getUDTValue(issueComponent)));
            permission.setTaskComponent(getPermissionType(row.getUDTValue(taskComponent)));
            permission.setProjectComponent(getPermissionType(row.getUDTValue(projectComponent)));
            permission.setReportComponent(getPermissionType(row.getUDTValue(reportComponent)));
            permission.setMemberComponent(getPermissionType(row.getUDTValue(memberComponent)));
            permission.setMeetingComponent(getPermissionMeetingType(row.getUDTValue(meetingComponent)));

            permissionList.add(permission);
        }

        if(permissionList.size() > 0) {
            responseModel.setError(null);
            responseModel.setContents(permissionList);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getSuccessful());
            responseModel.setRecordCount(permissionList.size());
        }else{
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "NOAVAILABLEDATA"));
            responseModel.setContents(null);
            responseModel.setSystemError(null);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }



    public Permission getPermissionId(UUID permissionUUID){

        //UUID permissionUUID = UUID.fromString(permissionId);

        Permission permission = new Permission();
        try {
            List<Permission> permissionList = new ArrayList<>();
            selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getPermission()).allowFiltering();
            select = selectallow.where(QueryBuilder.eq(id, permissionUUID));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset) {
                System.out.println("each row : " + " value " + row + "");
                permission.setId(row.getUUID(id));
                permission.setName(row.getString(name));
                permission.setPermission_key(row.getInt(permissionKey));
                permission.setDescription(row.getString(description));
                permission.setIssueComponent(getPermissionType(row.getUDTValue(issueComponent)));
                permission.setTaskComponent(getPermissionType(row.getUDTValue(taskComponent)));
                permission.setProjectComponent(getPermissionType(row.getUDTValue(projectComponent)));
                permission.setReportComponent(getPermissionType(row.getUDTValue(reportComponent)));
                permission.setMemberComponent(getPermissionType(row.getUDTValue(memberComponent)));
                permission.setMeetingComponent(getPermissionMeetingType(row.getUDTValue(meetingComponent)));

            }
        }catch (Exception e){
            logger.error("error in getPermission id : "+e);
        }
        return permission;
    }

    public <T extends permissionMeetingType> T getPermissionByComponent(Permission permission,String component) {

        permissionType permissionType = new permissionType();
           switch (component) {
                case "issue": {
                    permissionType = permission.getIssueComponent();
                }
                break;
                case "task": {
                    permissionType = permission.getTaskComponent();
                }
                break;
                case "project": {
                    permissionType = permission.getProjectComponent();
                }
                break;
                case "report": {
                    permissionType = permission.getReportComponent();
                }
                break;
                case "meeting": {
                    permissionType = permission.getMeetingComponent();
                }
                break;
                case "member": {
                    permissionType = permission.getMemberComponent();
                }
                break;
            }

        return (T) permissionType;
    }

    public boolean checkUserResourcePermissionByToken(String component) {
        String userName = null;
        try {
            userName = securityService.getUserNameByToken("");
            logger.info("user name retrieved from token : " + userName);
            if (userName == null)return false;
            UUID permissionId = memberService.getPermissionIdByMemberName(userName);
            logger.info(" permission id in checkUserResourcePermissionByToken of permission repo " + permissionId);
            permissionMeetingType permissionType = getPermissionByComponent(getPermissionId(permissionId), component);
            logger.info(" permission  " + permissionType.getViewPermission());
            if (permissionType.getViewPermission() == 1)
                return true;
            else
                return false;

        }catch (Exception e){
            return false;
        }
    }
}
