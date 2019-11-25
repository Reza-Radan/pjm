package com.progetto.projectmanagement.domain.member;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.querybuilder.*;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.domain.role.RoleRepository;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.security.UserPassSecurityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

public class MemberRepository {

    private final String id = "id";
    private final String name = "name";
    private final String family = "family";
    private final String imagePath = "imagePath";
    private final String subMembers = "subMembers";
    private final String workingtype = "workingtype";
    private final String phoneNumber = "phoneNumber";
    private final String email = "email";
    private final String userName = "userName";
    private final String password = "password";
    private final String roleId = "roleId";
    private final String modifyTime = "modifyTime";
    private final String config = "config";
    private final String tag = "MemberRepository";

    /* MemberByRole fields */
    private final String memberId = "memberId";
    private final String memberName = "memberName";
    private final String memberFamily = "memberFamily";
    private final String roleNameField = "roleName";
    private final String permissionID = "permissionId";

    ResultSet resultset, resultSetBatch, resultsetMemberRole;
    Logger logger = LoggerFactory.getLogger(MemberRepository.class);
    Batch batch = null;
    QueryBuilder queryBuilder;
    Select.Where select, selectMemberRole;
    Select selectallow, selectallowMemberRole;
    Update.Where update, updateMemberRole;
    Insert insertMemberRole, insertMember;


    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ConfigSession configSession;

    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    RoleRepository roleRepository;


    public ResultModel addMember(Member member, boolean isUpdate) {

        try {
            /*
            * in this query we check if user, "name" value exist other data will be updated according to user id
            */
            UUID fetchId = null, uuid = null, roleFetchId = null, memberByRoleID = null;
            List<String> roleNameId;
            String roleName = null, permissionId = null;

            roleNameId = roleRepository.getPermissionIdByRoleId(member.getRoleId());
            if (roleNameId != null && roleNameId.get(0) != null && roleNameId.get(1) != null) {
                logger.info("roleNameId......... : " + roleNameId + " , " + roleNameId.get(0));
                permissionId = roleNameId.get(0);
                roleName = roleNameId.get(1);

                logger.info("roleNameId......... : " + roleNameId + " , " + " roleName " + roleName + "  , permissionId : " + permissionId);
                batch = QueryBuilder.batch();

                selectallow = QueryBuilder.select(id).from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).allowFiltering();
                select = selectallow.where(eq(userName, member.getUserName()));
                resultset = configSession.getSession().execute(select);

                for (Row row : resultset)
                    fetchId = row.getUUID(id);

                System.out.println(tag + " fetchId " + fetchId);

                if (fetchId == null)
                    uuid = UUID.randomUUID();
                else
                    uuid = fetchId;

                System.out.println(tag + " uuid " + uuid + " member.getRoleId() " + member.getRoleId() + " , " + id + "=" + uuid);
                update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).
                        with()
                        .and(QueryBuilder.set(name, member.getName()))
                        .and(QueryBuilder.set(family, member.getFamily()))
                        .and(QueryBuilder.set(imagePath, member.getImagePath()))
                        .and(QueryBuilder.set(phoneNumber, member.getPhoneNumber()))
                        .and(QueryBuilder.set(email, member.getEmail()))
                        .and(QueryBuilder.set(userName, member.getUserName()))
                        .and(QueryBuilder.set(password, member.getPassword()))
                        .and(QueryBuilder.set(roleId, member.getRoleId()))
                        .and(QueryBuilder.set(modifyTime, resultModel.getInsertDate()))
                        .and(QueryBuilder.set(subMembers, member.getSubMembers()))
                        .and(QueryBuilder.set(config, member.getConfig()))
                        .and(QueryBuilder.set(workingtype, getUdtvalue(member)))
                        .where(QueryBuilder.eq(id, uuid));

                updateMemberRole = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole()).
                        with()
                        .and(QueryBuilder.set(memberName, member.getName()))
                        .and(QueryBuilder.set(memberFamily, member.getFamily()))
                        .and(QueryBuilder.set(roleNameField, roleName))
                        .and(QueryBuilder.set(permissionID,permissionId))
                        .and(QueryBuilder.set(modifyTime, resultModel.getInsertDate()))
                        .where(QueryBuilder.eq(memberId, uuid)).and(QueryBuilder.eq(roleId, member.getRoleId()));

//            insertMember = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.membertable())
//                    .value(name, member.getName())
//                    .value(family, member.getFamily())
//                    .value(imagePath,member.getImagePath())
//                    .value(phoneNumber, member.getPhoneNumber())
//                    .value(email,member.getEmail())
//                    .value(userName,member.getUserName())
//                    .value(roleId,member.getRoleId())
//                    .value(modifyTime, resultModel.getInsertDate())
//                    .value(id, uuid);

             /* to update the values of table member_by_role  we delete fields then insert b/c we can not update directly*/
                if (isUpdate) {
                    selectallowMemberRole = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole()).allowFiltering();
                    selectMemberRole = selectallowMemberRole.where(eq(memberId, uuid));
                    resultsetMemberRole = configSession.getSession().execute(selectMemberRole);
                    for (Row row : resultsetMemberRole) {
                        roleFetchId = row.getUUID(memberId);
                        memberByRoleID = row.getUUID(roleId);
                    }

                    logger.info(tag + " roleFetchId id Member_by_Role table : " + roleFetchId + " member.getRoleId() " + member.getRoleId() + " memberByRoleID : " + memberByRoleID);

                    if (roleFetchId != null) {
                        Delete.Where delete = QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole())
                                .where(eq(memberId, roleFetchId)).and(eq(roleId, memberByRoleID));
                        configSession.getSession().execute(delete);
                    }
                }


                insertMemberRole = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole())
                        .value(memberName, member.getName())
                        .value(memberFamily, member.getFamily())
                        .value(roleNameField, roleName)
                        .value(permissionID, permissionId)
                        .value(roleId, member.getRoleId())
                        .value(memberId, uuid)
                        .value(modifyTime, resultModel.getInsertDate());


                batch.add(update);
                batch.add(insertMemberRole);

                resultSetBatch = configSession.getSession().execute(batch);
                System.out.println(tag + " resultSetBatch wasApplied : " + resultSetBatch.wasApplied());

                if (resultSetBatch.wasApplied()) {
                    resultModel.setError(0);
                    resultModel.setResult(requirementsProperties.getSuccessful());
                } else {
                    resultModel.setError(1);
                    resultModel.setResult(resultModel.getErrorTextByLanguage(member.getLang(),"UNKNOWN_TRANSACTION_ERROR"));
                }
                System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
//
            } else { /* role id no exist so member could not created*/
                resultModel.setError(1);
                resultModel.setResult( resultModel.getErrorTextByLanguage(member.getLang(),"ROLE_ID_REQUIRED"));
            }

        } catch (Exception e) {
            logger.error(tag + " error in add member : " + e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }
        return resultModel;
    }

    public boolean checkMember(String username) {
        boolean result = false;

        try {
            /*
             *Check user existence by member for validation
             */
            UUID checkId = null;
            logger.info(tag + " username check for user existence : " + username + "  requirementsProperties.getMemberTable() " + requirementsProperties.membertable());
            selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).allowFiltering();
            select = selectallow.where(eq(userName, username));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset)
                checkId = row.getUUID(id);

            if (checkId != null)
                result = true;

            logger.info(tag + " result of check member fetched Id " + checkId + " result " + result);
            return result;
        } catch (Exception e) {
            logger.error(tag + " error in check user existence : " + e);
            return result;
        }
    }



    /*
     Change password
     */
    public ResultModel changePassword(UUID memberId,String oldPass,String newPass,String lang) {
        boolean result = false;

        try {
            /*
             *Check user existence by member for validation
             */
            UUID checkId = null;
            String pass = null;
            System.out.println(tag + " change pass : newPass : " + newPass + " oldPass " + oldPass+" member Id : "+memberId);

            selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).allowFiltering();
            select = selectallow.where(eq(id, memberId));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset)
                  pass = row.getString(password);

            if(oldPass.equalsIgnoreCase(pass)) {
                update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).
                        with()
                        .and(QueryBuilder.set(password, newPass))
                        .where(QueryBuilder.eq(id, memberId));
                resultset = configSession.getSession().execute(update);
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            }else{
              resultModel.setError(1);
              resultModel.setResult(resultModel.getErrorTextByLanguage(lang,"WRONG_OLD_PASS"));
            }

            logger.info(tag + " change pass result model " + resultModel.getResult());
            return resultModel;
        } catch (Exception e) {
            logger.error(tag + " error in change password : " + e);
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(lang,"UNKNOWN_TRANSACTION_ERROR"));
            return resultModel;
        }
    }



    public boolean loginMember(UserPassSecurityModel usernamePass) {
        boolean result = false;

            /*
             *Check user name and password
             */
        try {

            UUID checkId = null;
            logger.info(tag + " loginMember  username : " + usernamePass.getUsername() + "  password " + usernamePass.getPassword());
            selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).allowFiltering();
            select = selectallow.where(eq(userName, usernamePass.getUsername())).and(eq(password, usernamePass.getPassword()));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset)
                checkId = row.getUUID(id);
//
            if (checkId != null)
//                if (resultset.all().size() >= 0) {
                    result = true;
//                    }

            logger.info(tag + " loginMember result " + result);
            return result;
        } catch (Exception e) {
            logger.error(tag + " error in check user existence : " + e);
            return result;
        }
    }


    public UUID getPermissionIdByMemberName(String memberNameRec) {
        UUID permID = null;
        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole()).allowFiltering();
        select = selectallow.where(eq(memberName, memberNameRec));
        resultset = configSession.getSession().execute(select);
        for (Row row : resultset)
            permID = row.getUUID(permissionID);
        return permID;
    }


    public UDTValue getUdtvalue(Member member) {

        UDTValue udtValue = null;
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("workingType");
            udtValue = coordsType.newValue()
                    .setLong("startTime", member.getWorkingtype().getStartTime())
                    .setLong("endTime", member.getWorkingtype().getStartTime())
                    .setString("type", member.getWorkingtype().getType())
                    .setList("days", member.getWorkingtype().getDays());

            System.out.println("member type :  " + member.getWorkingtype().getType());

        } catch (Exception e) {
            logger.error("error getUdtvalue : "+e);
        }
        return udtValue;
    }


    public ResultModel deleteAccount(UUID memberID,String lang){

        UUID roleID = null ;
        ResultSet deleteResultSet;
        resultModel.setResult("");

        selectallowMemberRole = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()).allowFiltering();
        selectMemberRole = selectallowMemberRole.where(eq(id, memberID));
        resultsetMemberRole = configSession.getSession().execute(selectMemberRole);
        for (Row row : resultsetMemberRole)
              roleID = row.getUUID(roleId);

        if (roleID != null) {

            Delete.Where deleteMember = QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.membertable())
                    .where(eq(id, memberID));

            Delete.Where deleteMemberByRole = QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getMemberByRole())
                    .where(eq(memberId, memberID)).and(eq(roleId, roleID));

            batch = QueryBuilder.batch();
            batch.add(deleteMember);
            batch.add(deleteMemberByRole);

            deleteResultSet = configSession.getSession().execute(batch);
            if (deleteResultSet.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            }else{
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang,"UNKNOWN_TRANSACTION_ERROR"));
            }
        }else{
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(lang,"MEMBERID_NOTFOUND"));
        }

        return resultModel;
    }


    /**
     * @author Masoomeh
     * @param lang
     * @return
     */
//    public ResponseModel getMembers(String lang){
//        responseModel.setResult("");
//        try {
//            ResultSet resultsetMembers = configSession.getSession().execute(QueryBuilder.select()
//                    .all()
//                    .from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()));
//
//            ArrayList<Member> members = new ArrayList<>();
//
//            for (Row row : resultsetMembers) {
//                Member member = new Member();
//                members.add(setMember(row, member));
//            }
//            /*
//             Validation error
//            */
//            if (members.size() >= 0) {
//                responseModel.setContents(members);
//                responseModel.setRecordCount(members.size());
//                responseModel.setResult(requirementsProperties.getSuccessful());
//            } else {
//                responseModel.setResult(requirementsProperties.getFail());
//                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "NO_MEMBER_EXIST"));
//                responseModel.setRecordCount(0);
//            }
//
//<<<<<<< HEAD
//=======

    /**
     * @author Masoomeh
     * @param lang
     * @return
     */
    public ResponseModel getMembers(String lang){
        responseModel.setResult("");
        try {
            ResultSet resultsetMembers = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.membertable()));

            ArrayList<Member> members = new ArrayList<>();

            for (Row row : resultsetMembers) {
                Member member = new Member();
                members.add(setMember(row, member));
            }
            /*
             Validation error
            */
            if (members.size() >= 0) {
                responseModel.setContents(members);
                responseModel.setRecordCount(members.size());
                responseModel.setResult(requirementsProperties.getSuccessful());
            } else {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "NO_MEMBER_EXIST"));
                responseModel.setRecordCount(0);
            }

        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    /**
     * @author Masoomeh
     * @param uuid
     * @param lang
     * @return
     */

    public ResponseModel getMembersById(UUID uuid,String lang){
        responseModel.setResult("");
        logger.info("uuid: " +uuid);
        try {
            ResultSet resultsetMembers = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.membertable())
                    .where(eq(id, uuid)));

            Member member = null;
            for (Row row : resultsetMembers) {
                logger.warn("uuid: " + row.getString("name"));
                member = new Member();
                member = setMember(row, member);
                responseModel.setContent(member);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (member == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "MEMBER_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
         return responseModel;
    }

    /**
     * @author Masoomeh
     * @param row
     * @param member
     * @return
     */
    private Member setMember(Row row, Member member) {
        member.setId(row.getUUID("id"));
        member.setName(row.getString("name"));
        member.setFamily(row.getString("family"));
        member.setImagePath(row.getString("imagePath"));
        member.setPhoneNumber(row.getString("phoneNumber"));
        member.setEmail(row.getString("email"));
        member.setUserName(row.getString("userName"));
        member.setConfig(row.getString("config"));

        workingType workingType = new workingType();
        workingType.setType(((UDTValue)row.getObject("workingtype")).getString("type"));
        workingType.setEndTime(((UDTValue)row.getObject("workingtype")).getLong("endtime"));
        workingType.setId(((UDTValue)row.getObject("workingtype")).getUUID("id"));
        workingType.setStartTime(((UDTValue)row.getObject("workingtype")).getLong("starttime"));
        workingType.setDays(((UDTValue)row.getObject("workingtype")).getList("days",Integer.class));

        member.setWorkingtype(workingType);
        member.setModifyTime(row.getLong("modifyTime"));
        member.setRoleId(row.getUUID("roleId"));

        return member;
    }
}
