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

public class SprintReport {



    org.slf4j.Logger logger = LoggerFactory.getLogger(SprintReport.class);
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


    //TaskReport
    private final String id = "id";
    private final String RTtaskId = "taskId";
    private final String RTprojectId = "projectId";
    private final String RTreportId = "reportId";
    private final String RTtaskTitle = "taskTitle";
    private final String RTreportDesc = "reportDesc";
    private final String RTattachment = "attachment ";
    private final String modifyTime = "modifyTime";
    private final String time = "time";


    //ProjectReport
    private final String RMprojectId = "projectId";
    private final String RMreportId = "reportId";
    private final String RMmeetingId = "meetingId";
    private final String RMattachment = "attachment";
    private final String RMdateReport = "dateReport";
    private final String RMreportDesc = "reportDesc";
    private final String RMauthor = "author ";
    private final String RMdateMeeting = "dateMeeting";
    private final String RMmeetingTitle = "meetingTitle";


    //ProjectReport
    private final String RPtaskId = "taskId";
    private final String RPprojectId = "projectId";
    private final String RPreportId = "reportId";
    private final String RPprojectTitle = "meetingTitle";
    private final String RPreportDesc = "reportDesc";
    private final String RPattachment = "attachment";

    private final String RPdateMeeting = "dateMeeting";

    //UserReport
    private final String RUtaskId = "taskId";
    private final String RUuserId = "userId";
    private final String RUreportId = "reportId";
    private final String RUtaskTitle = "taskTitle";
    private final String RUreportDesc = "reportDesc";
    private final String RUattachment = "attachment";



    public ResponseModel getReportByTask(UUID taskId, String lang) {
        ArrayList<TaskReport> taskReports = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByTask())
                    .where(QueryBuilder.eq(RTtaskId, taskId)));

            for (Row row : resultset.all()) {
                TaskReport taskReport = new TaskReport();
                taskReports.add(setReportByTask(row, taskReport));
            }
            if (resultset.wasApplied() && taskReports.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(taskReports.size());
                responseModel.setContent(taskReports);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        }catch (Exception e){
            responseModel.setContent(taskReports);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }


        return responseModel;
    }

    private TaskReport setReportByTask(Row row, TaskReport taskReport) {

        taskReport.setTaskId(row.getUUID(RTtaskId));
        taskReport.setProjectId(row.getUUID(RTprojectId));
        taskReport.setReportId(row.getUUID(RTreportId));
        taskReport.setTaskTitle(row.getString(RTtaskTitle));
        taskReport.setReportDesc(row.getString(RTreportDesc));
        taskReport.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RTattachment),id));
        taskReport.setModifyTime(row.getUUID(modifyTime));
        taskReport.setTime(row.getUUID(time));
        return taskReport;
    }

    public ResponseModel getReportByUser(UUID userId, String lang) {

        ArrayList<UserReport> userReports = new ArrayList<>();
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByUser())
                    .where(QueryBuilder.eq(RUuserId, userId)));

            for (Row row : resultset.all()) {
                UserReport userReport = new UserReport();
                userReports.add(setReportByUser(row, userReport));
            }
            if (resultset.wasApplied() && userReports.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(userReports.size());
                responseModel.setContent(userReports);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        } catch (Exception e) {
            responseModel.setContent(userReports);
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


     public ResponseModel addReportByMeeting(ProjectReport projectReport, String lang){
//        batch = QueryBuilder.batch();
//        logger.warn("addReportByMeeting batch: " +batch );
//        UUID uuid = null;
//        try{
//            //task
//            UDTValue implimentorObj;
//            Insert taskInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getReportByMeeting())
//                    .value(RMmeetingTitle, projectReport.getMeetingTitle())
//                    .value(RMreportDesc, projectReport.getReportDesc())
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

    public ResponseModel addReportByUser(UserReport userReport, String lang) {
        return responseModel;
    }


    public ResponseModel addReportByTask(TaskReport reportTask, String lang) {
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

        ArrayList<ProjectReport> projectReports = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getReportByMeeting())
                    .where(QueryBuilder.eq(RTtaskId, meetingId)));

            for (Row row : resultset.all()) {
                ProjectReport projectReport = new ProjectReport();
                projectReports.add(setReportByMeeting(row, projectReport));
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

    private ProjectReport setReportByMeeting(Row row, ProjectReport projectReport) {

        projectReport.setProjectId(row.getUUID(RMprojectId));
        projectReport.setReportId(row.getUUID(RMreportId));
        projectReport.setMeetingId(row.getUUID(RMmeetingId));
        projectReport.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RMattachment),id));
        projectReport.setDateReport(row.getUUID(RMdateReport));
        projectReport.setReportDesc(row.getString(RMreportDesc));
        projectReport.setAuthor(AddProjectRepository.getMemberTypeFromUdtValue((UDTValue) row.getObject(RMauthor)));
        projectReport.setDateMeeting(row.getUUID(RMdateMeeting));
        projectReport.setMeetingTitle(row.getString(RMmeetingTitle));
        projectReport.setModifyTime(row.getUUID(modifyTime));
        projectReport.setTime(row.getUUID(time));
        return projectReport;

    }


    private UserReport setReportByUser(Row row, UserReport userReport) {
        userReport.setTaskId(row.getUUID(RUtaskId));
        userReport.setUserId(row.getUUID(RUuserId));
        userReport.setReportId(row.getUUID(RUreportId));
        userReport.setTaskTitle(row.getString(RUtaskTitle));
        userReport.setReportDesc(row.getString(RUreportDesc));
        userReport.setAttachment(AddProjectRepository.getudtValuesuuidClass(row.getUUID(RUattachment),id));
        userReport.setModifyTime(row.getUUID(modifyTime));
        userReport.setTime(row.getUUID(time));
        return userReport;
    }



}
