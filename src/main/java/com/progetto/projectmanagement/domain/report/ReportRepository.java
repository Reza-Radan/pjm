package com.progetto.projectmanagement.domain.report;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.querybuilder.Batch;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.domain.project.AddProjectRepository;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import org.apache.cassandra.utils.UUIDGen;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

public class ReportRepository {



    org.slf4j.Logger logger = LoggerFactory.getLogger(ReportRepository.class);
    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    ConfigSession configSession;

    @Autowired
    RequirementsProperties requirementsProperties;

    Batch batch;

    ResultSet resultset ;


    //ReportByTask
    private final String id = "id";
    private final String RTtaskId = "taskId";
    private final String RTprojectId = "projectId";
    private final String RTreportId = "reportId";
    private final String RTtaskTitle = "taskTitle";
    private final String RTreportDesc = "reportDesc";
    private final String RTattachment = "attachment ";
    private final String modifyTime = "modifyTime";
    private final String time = "time";


    //ReportByMeeting
    private final String RMprojectId = "projectId";
    private final String RMreportId = "reportId";
    private final String RMmeetingId = "meetingId";
    private final String RMattachment = "attachment";
    private final String RMdateReport = "dateReport";
    private final String RMreportDesc = "reportDesc";
    private final String RMauthor = "author ";
    private final String RMdateMeeting = "dateMeeting";
    private final String RMmeetingTitle = "meetingTitle";


    //ReportByProject
    private final String RPtaskId = "taskId";
    private final String RPprojectId = "projectId";
    private final String RPreportId = "reportId";
    private final String RPprojectTitle = "meetingTitle";
    private final String RPreportDesc = "reportDesc";
    private final String RPattachment = "attachment";

    private final String RPdateMeeting = "dateMeeting";

    //ReportByUser
    private final String RUtaskId = "taskId";
    private final String RUuserId = "userId";
    private final String RUreportId = "reportId";
    private final String RUtaskTitle = "taskTitle";
    private final String RUreportDesc = "reportDesc";
    private final String RUattachment = "attachment";



    public ResponseModel getReportByTask(UUID taskId, String lang) {
        ArrayList<ReportByTask> reportByTasks = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByTask())
                    .where(QueryBuilder.eq(RTtaskId, taskId)));

            for (Row row : resultset.all()) {
                ReportByTask reportByTask = new ReportByTask();
                reportByTasks.add(setReportByTask(row, reportByTask));
            }
            if (resultset.wasApplied() && reportByTasks.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(reportByTasks.size());
                responseModel.setContent(reportByTasks);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        }catch (Exception e){
            responseModel.setContent(reportByTasks);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }


        return responseModel;
    }

    private ReportByTask setReportByTask(Row row, ReportByTask reportByTask) {

        reportByTask.setTaskId(row.getUUID(RTtaskId));
        reportByTask.setProjectId(row.getUUID(RTprojectId));
        reportByTask.setReportId(row.getUUID(RTreportId));
        reportByTask.setTaskTitle(row.getString(RTtaskTitle));
        reportByTask.setReportDesc(row.getString(RTreportDesc));
        reportByTask.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RTattachment),id));
        reportByTask.setModifyTime(row.getUUID(modifyTime));
        reportByTask.setTime(row.getUUID(time));
        return reportByTask;
    }

    public ResponseModel getReportByUser(UUID userId, String lang) {

        ArrayList<ReportByUser> reportByUsers = new ArrayList<>();
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByUser())
                    .where(QueryBuilder.eq(RUuserId, userId)));

            for (Row row : resultset.all()) {
                ReportByUser reportByUser = new ReportByUser();
                reportByUsers.add(setReportByUser(row, reportByUser));
            }
            if (resultset.wasApplied() && reportByUsers.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(reportByUsers.size());
                responseModel.setContent(reportByUsers);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        } catch (Exception e) {
            responseModel.setContent(reportByUsers);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;

    }



    public ResponseModel getReportByProject(UUID projectId, String lang) {
        ArrayList<ProjectReport> projectReports = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getProjectReport())
                    .where(QueryBuilder.eq(RPprojectId, projectId)));

            for (Row row : resultset.all()) {
                ProjectReport projectReport = new ProjectReport();
                projectReports.add(setReportByProject(row, projectReport));
            }
            if (resultset.wasApplied() && projectReports.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(projectReports.size());
                responseModel.setContent(projectReports);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        } catch (Exception e) {
            responseModel.setContent(projectReports);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }


        return responseModel;
    }


     public ResponseModel addReportByMeeting(ReportByMeeting reportByMeeting, String lang){
//        batch = QueryBuilder.batch();
//        logger.warn("addReportByMeeting batch: " +batch );
//        UUID uuid = null;
//        try{
//            //task
//            UDTValue implimentorObj;
//            Insert taskInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getReportByMeeting())
//                    .value(RMmeetingTitle, reportByMeeting.getMeetingTitle())
//                    .value(RMreportDesc, reportByMeeting.getReportDesc())
//                    .value(RPMett, taskModel.getProjectId())
//                    .value(projectName, taskModel.getProjectName())
//                    .value(startDate, taskModel.getStartDate())
//                    .value(endDate, taskModel.getEndDate())
//                    .value(isPeriodic, taskModel.isPeriodic())
//                    .value(status, taskModel.getStatus())
//                    .value(periodicType, taskModel.getPeriodicType())
//                    .value(periodicTypeStart, taskModel.getPeriodicTypeStart())
//                    .value(periodicTypeStop, taskModel.getPeriodicTypeStop())
//                    .value(progressRate, taskModel.getProgressRate())
//                    .value(taskDuration, taskModel.getTaskDuration())
//                    .value(taskPriority, taskModel.getTaskPriority())
//                    .value(responsible, AddProjectRepository.getMemberTypeById(taskModel.getResponsibleId()))
//                    .value(implementor,implimentorObj = AddProjectRepository.getMemberTypeById(taskModel.getImplementorId()))
//                    .value(responsibleId, taskModel.getResponsibleId())
//                    .value(implementorId, taskModel.getImplementorId())
//                    .value(modifyTime, UUIDGen.getTimeUUID())
//                    .value(time, UUIDGen.getTimeUUID())
//                    .value(subfeatureId, taskModel.getSubfeatureId())
//                    .value(id, uuid = UUIDGen.getTimeUUID())
//                    .value(subfeatureTitle, taskModel.getSubfeatureTitle());
//
//
//
//
//            batch.add(taskInsert);
////            batch.add(taskByImolimentorInsert);
////            batch.add(notificationByTaskInsert);
////            batch.add(taskByProjectInsert);
////            batch.add(taskByResponsibleInsert);
////            batch.add(taskBySubFeatureInsert);
//
//            logger.info("configSession.getSession(): " +configSession.getSession() + " batch: "+batch + " taskByImolimentorInsert: " +taskByImolimentorInsert);
//            ResultSet batchExecute = configSession.getSession().execute(batch);
//
//            if (batchExecute.wasApplied()) {
//                resultModel.setError(0);
//                resultModel.setResult(requirementsProperties.getSuccessful());
//
//            } else {
//                resultModel.setError(1);
//                resultModel.setResult(resultModel.getErrorTextByLanguage(taskModel.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
//            }
//        }catch (Exception e){
//            resultModel.setResult(e.toString());
//            resultModel.setError(0);
//        }


        return responseModel;
    }

    public ResponseModel addReportByUser(ReportByUser reportByUser, String lang) {
        return responseModel;
    }


    public ResponseModel addReportByTask(ReportByTask reportTask, String lang) {
        return responseModel;
    }


    private ProjectReport setReportByProject(Row row, ProjectReport projectReport) {
        projectReport.setProjectId(row.getUUID(RPprojectId));
        projectReport.setReportId(row.getUUID(RPreportId));
        projectReport.setProjectTitle(row.getString(RPprojectTitle));
        projectReport.setReportDesc(row.getString(RPreportDesc));
        projectReport.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RPattachment),id));
        projectReport.setModifyTime(row.getUUID(modifyTime));
        projectReport.setTime(row.getUUID(time));
        return projectReport;
    }

    public ResponseModel getReportByMeeting(UUID meetingId, String lang) {

        ArrayList<ReportByMeeting> reportByMeetings = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByMeeting())
                    .where(QueryBuilder.eq(RTtaskId, meetingId)));

            for (Row row : resultset.all()) {
                ReportByMeeting reportByMeeting = new ReportByMeeting();
                reportByMeetings.add(setReportByMeeting(row, reportByMeeting));
            }
            if (resultset.wasApplied() && reportByMeetings.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(reportByMeetings.size());
                responseModel.setContent(reportByMeetings);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        } catch (Exception e) {
            responseModel.setContent(reportByMeetings);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }

    private ReportByMeeting setReportByMeeting(Row row, ReportByMeeting reportByMeeting) {

        reportByMeeting.setProjectId(row.getUUID(RMprojectId));
        reportByMeeting.setReportId(row.getUUID(RMreportId));
        reportByMeeting.setMeetingId(row.getUUID(RMmeetingId));
        reportByMeeting.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RMattachment),id));
        reportByMeeting.setDateReport(row.getUUID(RMdateReport));
        reportByMeeting.setReportDesc(row.getString(RMreportDesc));
        reportByMeeting.setAuthor(AddProjectRepository.getMemberTypeFromUdtValue((UDTValue) row.getObject(RMauthor)));
        reportByMeeting.setDateMeeting(row.getUUID(RMdateMeeting));
        reportByMeeting.setMeetingTitle(row.getString(RMmeetingTitle));
        reportByMeeting.setModifyTime(row.getUUID(modifyTime));
        reportByMeeting.setTime(row.getUUID(time));
        return reportByMeeting;

    }


    private ReportByUser setReportByUser(Row row, ReportByUser reportByUser) {
        reportByUser.setTaskId(row.getUUID(RUtaskId));
        reportByUser.setUserId(row.getUUID(RUuserId));
        reportByUser.setReportId(row.getUUID(RUreportId));
        reportByUser.setTaskTitle(row.getString(RUtaskTitle));
        reportByUser.setReportDesc(row.getString(RUreportDesc));
        reportByUser.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RUattachment),id));
        reportByUser.setModifyTime(row.getUUID(modifyTime));
        reportByUser.setTime(row.getUUID(time));
        return reportByUser;
    }



}
