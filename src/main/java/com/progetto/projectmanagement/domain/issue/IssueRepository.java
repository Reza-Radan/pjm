package com.progetto.projectmanagement.domain.issue;


import com.mysql.cj.result.Row;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.hibernate.sql.Insert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import static org.hibernate.criterion.Restrictions.eq;


public class IssueRepository {

    org.slf4j.Logger logger = LoggerFactory.getLogger(IssueRepository.class);
    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    ConfigSession configSession;

    @Autowired
    RequirementsProperties requirementsProperties;

    private final String id = "id";
//    private final String issueFrom = "issuefrom['id']";
//    private final String releaseid = "releaseid";
//    private final String issueTo = "issueTo";
    private final String taskIdString = "taskid";
    //field of issueByRelease
    private final String IRprojectIdString = "projectId";
    private final String IRreleaseIdString = "releaseId";
    private final String IRissueTitleString = "issueTitle";
    private final String IRissueDescString = "issueDesc";
    private final String IRstartDateString = "startDate";
    private final String IRdurationString = "duration";
    private final String IRmodifyTimeString = "modifyTime";
    private final String IRissueFromString = "issueFrom";
    private final String IRissueToString = "issueTo";
    private final String IRissueFromIdString = "issueFromId";
    private final String IRissueToIdString = "issueToId";
//    private final String IRtimeString = "time";
    private final String IRstatusString = "status";

    //field of issue
    private final String IprojectIdString = "projectId";
    private final String IissueTitleString = "title";
    private final String IissueDescString = "issueDesc";
    private final String IstartDateString = "startDate";
    private final String IdurationString = "duration";
    private final String ImodifyTimeString = "modifyTime";
    private final String IissueFromString = "issueFrom";
    private final String IissueToString = "issueTo";
    private final String IissueFromIdString = "issueFromId";
    private final String IissueToIdString = "issueToId";
    private final String IreleaseIdString = "releaseId";
//    private final String ItimeString = "time";
//    private final String IstatusString = "status";
    private final String ItaskIdString = "taskid";

    //field of issueByTask
    private final String ITprojectIdString = "projectId";
    private final String ITtaskIdString = "taskId";
    private final String ITissueTitleString = "title";
    private final String ITissueDescString = "issueDesc";
    private final String ITstartDateString = "startDate";
    private final String ITdurationString = "duration";
    private final String ITmodifyTimeString = "modifyTime";
    private final String ITissueFromString = "issueFrom";
    private final String ITissueToString = "issueTo";
    private final String ITissueFromIdString = "issueFromId";
    private final String ITissueToIdString = "issueToId";
//    private final String ITtimeString = "time";
    private final String ITstatusString = "status";

    Batch batch;


    public ResponseModel getIssueByMember(String memberId ,String lang) {
        responseModel.setResult("");
        try {
            logger.warn("memberId: " + memberId +" string: " + UUID.fromString(memberId) );
            ResultSet resultsetIssue = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getIssue()).allowFiltering()
                    .where(eq(IissueFromIdString, UUID.fromString(memberId))))/*.and(QueryBuilder.eq(IissueFromString,UUID.fromString(issueId))))*/;

            Issue issue = null;
            for (Row row : resultsetIssue) {
                logger.warn("resultsetIssue: " );
                issue = new Issue();
                issue = setIssue(row, issue);
                responseModel.setContent(issue);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (issue == null) {
                logger.warn("issue null ");
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "ISSUE_NOT_EXIST"));
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


    public ResponseModel getIssueByRelease( /*UUID issueId*/ UUID releaseId,String lang) {
        responseModel.setResult("");
        try {
            logger.warn("getIssueByRelease" );
            ResultSet resultsetIssue = configSession.getSession().execute(
                    QueryBuilder.select()
                            .all()
                            .from(requirementsProperties.getKeyspace(), requirementsProperties.getIssue()).allowFiltering()
                            .where(eq(IRreleaseIdString, releaseId))/*.and(eq(releaseid, releaseId))*/);

            ArrayList<Issue> issues = new ArrayList<>();
            for (Row row : resultsetIssue) {
                logger.warn("resultsetIssue: " );
                Issue issue = new Issue();
                issues.add( setIssue(row, issue));

            }

            if (issues.size()<=0) {
                logger.warn("issue null ");
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "ISSUE_NOT_EXIST"));
                responseModel.setRecordCount(0);
            }else{
                responseModel.setContent(issues);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(issues.size());
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }


    public ResponseModel getIssueByTask(UUID taskId,String lang) {
        responseModel.setResult("");
        try {
            logger.warn("getIssueByTask" );
            ResultSet resultsetIssue = configSession.getSession().execute(
                    QueryBuilder.select()
                            .all()
                            .from(requirementsProperties.getKeyspace(), requirementsProperties.getIssue()).allowFiltering()
                            .where/*(eq(id, issueId)).and*/(eq(taskIdString, taskId)));

            ArrayList<Issue> issues = new ArrayList<>();
            for (Row row : resultsetIssue) {
                logger.warn("resultsetIssue: " );
                Issue issue = new Issue();
                issues.add( setIssue(row, issue));
            }

            if (issues.size()<=0) {
                logger.warn("issue null ");
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "ISSUE_NOT_EXIST"));
                responseModel.setRecordCount(0);
            }else{
                responseModel.setContent(issues);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(issues.size());
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }

    public ResultModel addIssueByRelease(IssueByRelease issuByRelease ,String lang) {

        logger.warn("addIssueByRelease" );
        batch = QueryBuilder.batch();
        //issue-to and issue-from fill from member table
        try {
            UUID uuid;
            Insert resultsetIssueByRelese = QueryBuilder.insertInto(requirementsProperties.getIssueByRelease())
                    .value(id, uuid = UUID.randomUUID())
                    .value(IRprojectIdString, issuByRelease.getProject_id())
                    .value(IRreleaseIdString, issuByRelease.getreleaseId())
                    .value(IRissueTitleString, issuByRelease.getIssue_title())
                    .value(IRissueDescString, issuByRelease.getIssue_desc())
                    .value(IRstartDateString, issuByRelease.getStart_date())
                    .value(IRdurationString, issuByRelease.getDuration())
                    .value(IRmodifyTimeString, resultModel.getInsertDate())
                    .value(IRissueFromIdString, issuByRelease.getIssueFromId())
                    .value(IRissueToIdString, issuByRelease.getIssueToId())
//                    .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .value(IRtimeString, resultModel.getInsertDate())
                    .value(IRstatusString, issuByRelease.getStatus());

            //add data to issue_table again
            Insert resultsetIssue =   QueryBuilder.insertInto(requirementsProperties.getIssue())
                            .value(id, uuid)
                            .value(IissueDescString, issuByRelease.getIssue_desc())
                            .value(IprojectIdString, issuByRelease.getProject_id())
                            .value(IreleaseIdString, issuByRelease.getreleaseId())
                            .value(IissueTitleString, issuByRelease.getIssue_title())
                            .value(IstartDateString, issuByRelease.getStart_date())
                            .value(IdurationString, issuByRelease.getDuration())
                            .value(IissueFromIdString, issuByRelease.getIssueFromId())
                            .value(IissueToIdString, issuByRelease.getIssueToId())
                            .value(ImodifyTimeString, resultModel.getInsertDate());
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                        .value(ItimeString, issuByRelease.getTime())
//                        .value(IstatusString, issuByRelease.getStatus()));


            batch.add(resultsetIssue);
            batch.add(resultsetIssueByRelese);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return resultModel;
    }



    private Issue setIssue(Row row, Issue issue) {

        issue.setId(row.getUUID("id"));
        issue.setTitle(row.getString("title"));
        issue.setDesc(row.getString("issueDesc"));
        issue.setstartDate(row.getLong("startDate"));
        issue.setDuration(row.getString("duration"));
        issue.setModifyTime(row.getLong("modifyTime"));
        if ((UDTValue)row.getObject("issueto") != null){
            issue.setIssue_to(getIssueMemeber(((UDTValue)row.getObject("issueto"))));
        }
        if ((UDTValue)row.getObject("issuefrom") != null){
            issue.setIssue_from(getIssueMemeber(((UDTValue)row.getObject("issuefrom"))));
        }
        issue.setRelease_id(row.getUUID("releaseId"));
        issue.setProject_id(row.getUUID("projectId"));
        issue.settaskId(row.getUUID("taskId"));



        return issue;
    }

    public MemberType getIssueMemeber(UDTValue issue) {
        MemberType member = new MemberType();

//        {email: 'mas@gmai;.com', family: 'abd', id: e7ae5cf3-d358-4d99-b900-85902fda9bb0, imagepath: null, name: 'mass', phonenumber: null, username: 'masoomeh001'}
        member.setId(issue.getUUID("id"));
        member.setEmail(issue.getString("email"));
        member.setFamily(issue.getString("family"));
        member.setUserName(issue.getString("imagepath"));
        member.setUserName(issue.getString("username"));
        member.setUserName(issue.getString("name"));
        member.setUserName(issue.getString("phonenumber"));
        return member;
    }

    public UDTValue getUdtvalue(Member member) {

        UDTValue udtValue = null;
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("issueto");


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

    public ResultModel addIssueByTask(IssueByTask issueByTask, String lang) {

        logger.warn("addIssueByRelease" );
        batch = QueryBuilder.batch();
        //issue-to and issue-from fill from member table
        try {
            UUID uuid;
            Insert resultsetIssueByRelese = QueryBuilder.insertInto(requirementsProperties.getIssue())
                    .value(id, uuid = UUID.randomUUID())
                    .value(IprojectIdString, issueByTask.getProject_id())
                    .value(ITtaskIdString, issueByTask.gettaskId())
                    .value(IissueTitleString, issueByTask.getTitle())
                    .value(IissueDescString, issueByTask.getDesc())
                    .value(IstartDateString, issueByTask.getStart_date())
                    .value(IdurationString, issueByTask.getDuration())
                    .value(IissueFromIdString, issueByTask.getIssueFromId())
                    .value(IissueToIdString, issueByTask.getIssueToId())
                    .value(ImodifyTimeString, resultModel.getInsertDate());
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .value(ItimeString, resultModel.getInsertDate())
//                    .value(IstatusString, issueByTask.getStatus());

            //add data to issue_table again
            Insert resultsetIssue =   QueryBuilder.insertInto(requirementsProperties.getIssueByTask())
                    .value(id, uuid)
                    .value(ITprojectIdString, issueByTask.getProject_id())
                    .value(ITtaskIdString, issueByTask.gettaskId())
                    .value(ITissueTitleString, issueByTask.getTitle())
                    .value(ITissueDescString, issueByTask.getDesc())
                    .value(ITstartDateString, issueByTask.getStart_date())
                    .value(ITdurationString, issueByTask.getDuration())
                    .value(ITmodifyTimeString, resultModel.getInsertDate())
                    .value(ITissueFromIdString, issueByTask.getIssueFromId())
                    .value(ITissueToIdString, issueByTask.getIssueToId())
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                        .value(ITtimeString, resultModel.getInsertDate())
                        .value(ITstatusString, issueByTask.getStatus());


            batch.add(resultsetIssue);
            batch.add(resultsetIssueByRelese);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(1);
        }
        return resultModel;
    }

    public ResultModel updateIssueByTask(IssueByTask issueByTask, String lang) {

        logger.warn("addIssueByRelease" );
        batch = QueryBuilder.batch();
        //issue-to and issue-from fill from member table
        try {
            Update.Where resultsetIssueByRelese = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getIssue())
                    .with()
                    .and(QueryBuilder.set(IprojectIdString, issueByTask.getProject_id()))
//                    .value(releaseIdString, issueByTask.getreleaseId())
                    .and(QueryBuilder.set(IissueTitleString, issueByTask.getTitle()))
                    .and(QueryBuilder.set(IissueDescString, issueByTask.getDesc()))
                    .and(QueryBuilder.set(IstartDateString, issueByTask.getStart_date()))
                    .and(QueryBuilder.set(IdurationString, issueByTask.getDuration()))
                    .and(QueryBuilder.set(ImodifyTimeString, resultModel.getInsertDate()))
                    .where(eq(id, issueByTask.getId()));
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .value(ItimeString, resultModel.getInsertDate())
//                    .value(IstatusString, issueByTask.getStatus());

            //add data to issue_table again
            Update.Where resultsetIssue =   QueryBuilder.update(requirementsProperties.getKeyspace() ,requirementsProperties.getIssueByTask())
                    .with()
                    .and(QueryBuilder.set(ITprojectIdString, issueByTask.getProject_id()))
//                    .and(QueryBuilder.set(ITtaskIdString, issueByTask.gettaskId()))
                    .and(QueryBuilder.set(ITissueTitleString, issueByTask.getTitle()))
                    .and(QueryBuilder.set(ITissueDescString, issueByTask.getDesc()))
                    .and(QueryBuilder.set(ITstartDateString, issueByTask.getStart_date()))
                    .and(QueryBuilder.set(ITdurationString, issueByTask.getDuration()))
                    .and(QueryBuilder.set(ITmodifyTimeString, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(ITissueFromIdString, issueByTask.getIssueFromId()))
                    .and(QueryBuilder.set(ITissueToIdString, issueByTask.getIssueToId()))
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .and(QueryBuilder.set(ITtimeString, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(ITstatusString, issueByTask.getStatus()))
                    .where(eq(id ,issueByTask.getId())).and(eq(ITtaskIdString,issueByTask.gettaskId()));

            batch.add(resultsetIssue);
            batch.add(resultsetIssueByRelese);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(1);
        }

        return resultModel;
    }

    public ResultModel updateIssueByRelease(IssueByRelease issuByRelease, String lang) {

        logger.warn("updateIssueByRelease" );
        batch = QueryBuilder.batch();
        //issue-to and issue-from fill from member table
//        try {
            Update.Where resultsetIssueByRelese = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getIssue())
                    .with()
                    .and(QueryBuilder.set(IprojectIdString, issuByRelease.getProject_id()))
//                    .value(releaseIdString, issueByTask.getreleaseId())
                    .and(QueryBuilder.set(IissueTitleString, issuByRelease.getIssue_title()))
                    .and(QueryBuilder.set(IissueDescString, issuByRelease.getIssue_desc()))
                    .and(QueryBuilder.set(IstartDateString, issuByRelease.getStart_date()))
                    .and(QueryBuilder.set(IdurationString, issuByRelease.getDuration()))
                    .and(QueryBuilder.set(ImodifyTimeString, resultModel.getInsertDate()))
                    .where(eq(id, issuByRelease.getId()));
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .value(ItimeString, resultModel.getInsertDate())
//                    .value(IstatusString, issueByTask.getStatus());

            //add data to issue_table again
            Update.Where resultsetIssue =   QueryBuilder.update(requirementsProperties.getKeyspace() ,requirementsProperties.getIssueByRelease())
                    .with()
                    .and(QueryBuilder.set(IRprojectIdString, issuByRelease.getProject_id()))
                    .and(QueryBuilder.set(IRissueDescString, issuByRelease.getIssue_desc()))
                    .and(QueryBuilder.set(IRstartDateString, issuByRelease.getStart_date()))
                    .and(QueryBuilder.set(IRdurationString, issuByRelease.getDuration()))
                    .and(QueryBuilder.set(IRmodifyTimeString, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(IRissueFromIdString, issuByRelease.getIssueFromId()))
                    .and(QueryBuilder.set(IRissueToIdString, issuByRelease.getIssueToId()))
                    .and(QueryBuilder.set(IRissueTitleString, issuByRelease.getIssue_title()))
//                    .and(QueryBuilder.set(IRtimeString, issuByRelease.getreleaseId()))
//                        .value(issueFromString, issuByRelease.getIssue_from())
//                        .value(issueToString, issuByRelease.getIssue_to())
//                    .and(QueryBuilder.set(IRtimeString, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(IRstatusString, issuByRelease.getStatus()))
                    .where(eq(id ,issuByRelease.getId())).
                            and(eq(IRreleaseIdString ,issuByRelease.getreleaseId()));

            batch.add(resultsetIssue);
            batch.add(resultsetIssueByRelese);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

//        }catch (Exception e){
//            resultModel.setError(1);
//            resultModel.setResult(e.toString());
//        }

        return resultModel;
    }
}


