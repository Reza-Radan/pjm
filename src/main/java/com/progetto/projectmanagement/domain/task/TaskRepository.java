package com.progetto.projectmanagement.domain.task;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.querybuilder.*;
import com.progetto.projectmanagement.ConfigSession;
import com.progetto.projectmanagement.domain.project.AddProjectRepository;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.apache.cassandra.utils.UUIDGen;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.quote;

public class TaskRepository {
    org.slf4j.Logger logger = LoggerFactory.getLogger(TaskRepository.class);
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

    //task
    private final String id = "id";
    private final String title = "title";
    private final String desc = "descTask";
    private final String projectId = "projectId";
    private final String projectName = "projectName";
    private final String startDate = "startDate";
    private final String endDate = "endDate";
    private final String isPeriodic = "isPeriodic";
    private final String status = "status";
    private final String periodicType = "periodicType";
    private final String periodicTypeStart = "periodicTypeStart";
    private final String periodicTypeStop = "periodicTypeStop";
    private final String progressRate = "progressRate";
    private final String taskDuration = "taskDuration";
    private final String taskPriority = "taskPriority";
    private final String responsible = "responsible";
    private final String implementor = "implementor";
    private final String implementorId = "implementorId";
    private final String responsibleId = "responsibleId";
    private final String modifyTime = "modifyTime";
    private final String time = "time";
    private final String subfeatureId = "subfeatureId";
    private final String subfeatureTitle = "subfeatureTitle";

    //taskByProject
    private final String TPprojectId = "projectId";
    private final String TPtaskId = "taskId";
    private final String TPprojectName = "projectName";
    private final String TPtaskTitle = "taskTitle";
    private final String TPmember = "member";
    private final String TPtaskDesc = "descTask";
    private final String TPtaskStartDate = "taskStartDate";
    private final String TPtaskEndDate = "taskEndDate";
    private final String TPtaskStatus = "taskStatus";
    private final String TPtaskDuration = "taskDuration";
    private final String TPprogressRate = "progressRate";
    private final String TPmodifyTime = "modifyTime";


    //taskByUser
    private final String TItaskId = "taskId";
    private final String TIimplementorId = "implementorId";
    private final String TIresponsibleId = "responsibleId";
    private final String TIimplementor = "implementor";
    private final String TIresponsible = "responsible";
    private final String TIprojectName = "projectName";
    private final String TItaskTitle = "taskTitle";
    private final String TImember = "member";
    private final String TItaskDesc = "descTask";
    private final String TItaskStartDate = "taskStartDate";
    private final String TItaskEndDate = "taskEndDate";
//    private final String TItaskStatus = "taskStatus";
    private final String TItaskDuration = "taskDuration";
    private final String TIprogressRate = "progressRate";
    private final String TImodifyTime = "modifyTime";


    //subFeatureByTask.
    private final String STsubfeatureId = "subfeatureId";
    private final String STtaskId = "taskId";
    private final String STtaskTitle = "taskTitle";
    private final String STsubFeatureTitle = "subFeatureTitle";
    private final String STtaskDesc = "descTask";
    private final String STmodifyTime = "modifyTime";


    //NotificationByTask
//    private final String NTnotificationId = "notificationId";
    private final String NTtaskId = "taskId";
    private final String NTtaskName = "taskName";
    private final String NTnotificationTitle = "notificationTitle";
    private final String NTnotificationDesc = "notificationDesc";
    private final String NTcreatorMember = "creatorMember";
    private final String NTmodifyTime = "modifyTime";


    public ResultModel updateTask(Task taskModel) {
        logger.warn("updateTask" );
        batch = QueryBuilder.batch();
        UUID uuid;
        try{
            //task
        UDTValue implimentordata;
        Update.Where taskUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTask())
                    .with()
//                    .and(QueryBuilder.set(id, uuid = UUID.randomUUID()))
                    .and(QueryBuilder.set(title, taskModel.getTitle()))
                    .and(QueryBuilder.set(desc, taskModel.getDescTask()))
                    .and(QueryBuilder.set(startDate, taskModel.getStartDate()))
                    .and(QueryBuilder.set(endDate, taskModel.getEndDate()))
                    .and(QueryBuilder.set(isPeriodic, taskModel.isPeriodic()))
                    .and(QueryBuilder.set(status, taskModel.getStatus()))
                    .and(QueryBuilder.set(projectName, taskModel.getProjectName()))
                    .and(QueryBuilder.set(projectId, taskModel.getProjectId()))
                    .and(QueryBuilder.set(periodicType, taskModel.getPeriodicType()))
                    .and(QueryBuilder.set(periodicTypeStart, taskModel.getPeriodicTypeStart()))
                    .and(QueryBuilder.set(periodicTypeStop, taskModel.getPeriodicTypeStop()))
                    .and(QueryBuilder.set(progressRate, taskModel.getProgressRate()))
                    .and(QueryBuilder.set(taskDuration, taskModel.getTaskDuration()))
                    .and(QueryBuilder.set(taskPriority, taskModel.getTaskPriority()))
                    .and(QueryBuilder.set(responsible, AddProjectRepository.getMemberTypeById(taskModel.getResponsibleId())))
                    .and(QueryBuilder.set(implementor, implimentordata = AddProjectRepository.getMemberTypeById(taskModel.getImplementorId())))
                    .and(QueryBuilder.set(responsibleId, taskModel.getResponsibleId()))
                    .and(QueryBuilder.set(implementorId, taskModel.getImplementorId()))
                    .and(QueryBuilder.set(modifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(subfeatureId, taskModel.getSubfeatureId()))
                    .and(QueryBuilder.set(subfeatureTitle, taskModel.getSubfeatureTitle()))
                    .where(QueryBuilder.eq(id, taskModel.getId())).and((QueryBuilder.eq(time, taskModel.getTime())));


            //subFeatureByTask
            Update.Where taskBySubFeatureUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByTask())
                    .with()
                    .and(QueryBuilder.set(STtaskTitle, taskModel.getTitle()))
                    .and(QueryBuilder.set(STsubFeatureTitle, taskModel.getSubfeatureTitle()))
                    .and(QueryBuilder.set(STtaskDesc, taskModel.getDescTask()))
                    .and(QueryBuilder.set(STmodifyTime, UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq(STtaskId, taskModel.getId())).and(QueryBuilder.eq(STsubfeatureId, taskModel.getSubfeatureId()));

            //taskByProject
//            ResultSet idTaskProject = configSession.getSession().execute(QueryBuilder.select(id)
//                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject()).allowFiltering()
//                    .where(eq(NTtaskId, taskModel.getId())));
//
//            Row rowTaskPro = idTaskProject.one();
//            UUID idTaskProjectUuid = rowTaskPro.getUUID(id);

            Update.Where taskByProjectUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject())
                    .with()
//                    .and(QueryBuilder.set(id, uuid))
                    .and(QueryBuilder.set(TPtaskTitle, taskModel.getTitle()))
                    .and(QueryBuilder.set(TPtaskDesc, taskModel.getDescTask()))
                    .and(QueryBuilder.set(TPprojectName, taskModel.getProjectName()))
                    .and(QueryBuilder.set(TPmember, implimentordata))//member details should get from implementorId and set
                    .and(QueryBuilder.set(TPtaskStartDate, taskModel.getStartDate()))
                    .and(QueryBuilder.set(TPtaskEndDate, taskModel.getEndDate()))
                    .and(QueryBuilder.set(TPtaskStatus, taskModel.getStatus()))
                    .and(QueryBuilder.set(TPtaskDuration, taskModel.getTaskDuration()))

                    .and(QueryBuilder.set(TPmodifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(TPprogressRate, taskModel.getProgressRate()))
                            .where(eq(TPtaskId ,taskModel.getId())).and(eq(TPprojectId , taskModel.getProjectId()));
//                    .and();
            //                .and(QueryBuilder.set(member , taskModel.getMeeting_);

            //notificationByTask
//        ResultSet notificationId = configSession.getSession().execute(QueryBuilder.select(NTnotificationId)
//                .from(requirementsProperties.getKeyspace(), requirementsProperties.getNotificationByTask()).allowFiltering()
//                .where(eq(NTtaskId, taskModel.getId())));

//        Row row = notificationId.one();
//        UUID NTId = row.getUUID(NTnotificationId);

//        UPDATE table SET column1 = XXX WHERE primary_key IN (p1, p2, p3, ...);
            Update.Where notificationByTaskUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getNotificationByTask()).
//                    with(set(NTtaskId ,taskModel.getId()))
                    with()
//                    .and(QueryBuilder.set(id, uuid))
//                    .and(QueryBuilder.set(NTtaskId, uuid))
//                    .and(QueryBuilder.set(NTnotificationId,  UUID.randomUUID()))
                    .and(QueryBuilder.set(NTtaskName, taskModel.getTitle()))
                    .and(QueryBuilder.set(NTnotificationTitle, taskModel.getTitle()))
                    .and(QueryBuilder.set(NTmodifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(NTcreatorMember, implimentordata))
                    .and(QueryBuilder.set(NTnotificationDesc, taskModel.getDescTask()))
                    .where( eq(NTtaskId ,taskModel.getId()))/*.and(eq(NTnotificationId ,NTId)*/;


            batch.add(taskUpdate);
            batch.add(notificationByTaskUpdate);
            batch.add(taskByProjectUpdate);
            batch.add(taskBySubFeatureUpdate);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(taskModel.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }


        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel addTask(Task taskModel) {
        batch = QueryBuilder.batch();
        logger.warn("addTAsk batch: " +batch );
        UUID uuid = null;
        try{
            //task
        UDTValue implimentorObj;
        Insert taskInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getTask())
                    .value(title, taskModel.getTitle())
                    .value(desc, taskModel.getDescTask())
                    .value(projectId, taskModel.getProjectId())
                    .value(projectName, taskModel.getProjectName())
                    .value(startDate, taskModel.getStartDate())
                    .value(endDate, taskModel.getEndDate())
                    .value(isPeriodic, taskModel.isPeriodic())
                    .value(status, taskModel.getStatus())
                    .value(periodicType, taskModel.getPeriodicType())
                    .value(periodicTypeStart, taskModel.getPeriodicTypeStart())
                    .value(periodicTypeStop, taskModel.getPeriodicTypeStop())
                    .value(progressRate, taskModel.getProgressRate())
                    .value(taskDuration, taskModel.getTaskDuration())
                    .value(taskPriority, taskModel.getTaskPriority())
                    .value(responsible, AddProjectRepository.getMemberTypeById(taskModel.getResponsibleId()))
                    .value(implementor,implimentorObj = AddProjectRepository.getMemberTypeById(taskModel.getImplementorId()))
                    .value(responsibleId, taskModel.getResponsibleId())
                    .value(implementorId, taskModel.getImplementorId())
                    .value(modifyTime, UUIDGen.getTimeUUID())
                    .value(time, UUIDGen.getTimeUUID())
                    .value(subfeatureId, taskModel.getSubfeatureId())
                    .value(id, uuid = UUIDGen.getTimeUUID())
                    .value(subfeatureTitle, taskModel.getSubfeatureTitle());



            Insert taskByImolimentorInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserImplementer())
                    .value(TItaskId, uuid )
                    .value(TIimplementorId, taskModel.getImplementorId())
                    .value(TIresponsibleId, taskModel.getResponsibleId())
                    .value(TIimplementor,implimentorObj)
                    .value(TIresponsible, AddProjectRepository.getMemberTypeById(taskModel.getResponsibleId()))
                    .value(TIprojectName, taskModel.getProjectName())
                    .value(TItaskTitle, taskModel.getTitle())
//                    .value(TImember, implimentorObj)
                    .value(TItaskDesc, taskModel.getDescTask())
                    .value(TItaskStartDate, taskModel.getStartDate())
                    .value(TItaskEndDate, taskModel.getEndDate())
                    .value(status, taskModel.getStatus())
                    .value(TItaskDuration, taskModel.getTaskDuration())
                    .value(TIprogressRate, taskModel.getProgressRate())
                    .value(TImodifyTime, UUIDGen.getTimeUUID())
                    .value(time, UUIDGen.getTimeUUID());



        Insert taskByResponsibleInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserResp())
                .value(TItaskId, uuid )
                .value(TIimplementorId, taskModel.getImplementorId())
                .value(TIresponsibleId, taskModel.getResponsibleId())
                .value(TIimplementor,implimentorObj)
                .value(TIresponsible, AddProjectRepository.getMemberTypeById(taskModel.getResponsibleId()))
                .value(TIprojectName, taskModel.getProjectName())
                .value(TItaskTitle, taskModel.getTitle())
//                    .value(TImember, implimentorObj)
                .value(TItaskDesc, taskModel.getDescTask())
                .value(TItaskStartDate, taskModel.getStartDate())
                .value(TItaskEndDate, taskModel.getEndDate())
                .value(status, taskModel.getStatus())
                .value(TItaskDuration, taskModel.getTaskDuration())
                .value(TIprogressRate, taskModel.getProgressRate())
                .value(TImodifyTime, UUIDGen.getTimeUUID())
                .value(time, UUIDGen.getTimeUUID());



            //subFeatureByTask
            Insert taskBySubFeatureInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByTask())
                    .value(STtaskTitle, taskModel.getTitle())
                    .value(STtaskDesc, taskModel.getDescTask())
                    .value(STtaskId, uuid)
                    .value(STsubFeatureTitle, taskModel.getSubfeatureTitle())
                    .value(STsubfeatureId, taskModel.getSubfeatureId())
                    .value(status, taskModel.getStatus())
                    .value(STmodifyTime, UUIDGen.getTimeUUID());

            //taskByProject
            Insert taskByProjectInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject())
                    .value(TPtaskTitle, taskModel.getTitle())
                    .value(TPtaskDesc, taskModel.getDescTask())
                    .value(TPtaskStartDate, taskModel.getStartDate())
                    .value(TPprojectId, taskModel.getProjectId())
                    .value(TPtaskId, uuid)
                    .value(TPprojectName, taskModel.getProjectName())
                    .value(TPmember, implimentorObj)
//                    .value(TPtaskStartDate, taskModel.getstartDate())
                    .value(TPtaskEndDate, taskModel.getEndDate())
                    .value(status, taskModel.getStatus())
                    .value(TPtaskDuration, taskModel.getTaskDuration())

                    .value(TPmodifyTime, UUIDGen.getTimeUUID())
                    .value(TPprogressRate, taskModel.getProgressRate());

            //notificationByTask
            Insert notificationByTaskInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getNotificationByTask())
//                    .value(id, uuid)
                    .value(NTtaskId, uuid)
//                    .value(NTnotificationId,  UUID.randomUUID())
                    .value(NTtaskName, taskModel.getTitle())
                    .value(NTnotificationTitle, taskModel.getTitle())
                    .value(NTmodifyTime, UUIDGen.getTimeUUID())
                    .value(NTcreatorMember, AddProjectRepository.getMemberTypeById(taskModel.getImplementorId()))
                    .value(NTnotificationDesc, taskModel.getDescTask());

            batch.add(taskInsert);
            batch.add(taskByImolimentorInsert);
            batch.add(notificationByTaskInsert);
            batch.add(taskByProjectInsert);
            batch.add(taskByResponsibleInsert);
            batch.add(taskBySubFeatureInsert);

            logger.info("configSession.getSession(): " +configSession.getSession() + " batch: "+batch + " taskByImolimentorInsert: " +taskByImolimentorInsert);
            ResultSet batchExecute = configSession.getSession().execute(batch);

            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(taskModel.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(0);
        }

        return resultModel;
    }

    /**
     * @author masoomeh
     * @param taskId
     * @param lang
     * @return
     */
    public ResponseModel getTaskDetail(UUID taskId, String lang) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getTask())
                    .where(QueryBuilder.eq(id, taskId)));

            for (Row row : resultset.all()) {
                Task task = new Task();
                tasks.add(setTask(row, task));
            }
            if (resultset.wasApplied() && tasks.size() > 0) {
//            responseModel.setError(requirementsProperties.getSuccessful());
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(tasks.size());
                responseModel.setContent(tasks);
            } else {
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setResult(requirementsProperties.getFail());
            }
        }catch (Exception e){
            responseModel.setContent(tasks);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }


    public ResultModel changeStatus(UUID taskIdData, int statusdata, String lang) {
        logger.warn("changeStatus" );
        batch = QueryBuilder.batch();
        UUID uuid;
        try {

            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getTask()).allowFiltering()
                    .where(QueryBuilder.eq(id, taskIdData)));

            List<Row> rows = resultset.all();
            if (rows.size()>0) {
                Row row = rows.get(0);

                logger.info("row: " +row);
                //task
                Update.Where taskUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTask())
                        .with()
                        .and(QueryBuilder.set(status, statusdata))
                        .where(QueryBuilder.eq(id, taskIdData)).and(eq(time, row.getUUID(time)));

                //TaskByUserImplementer
                Update.Where taskByUserImplementer = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserImplementer())
                        .with()
                        .and(QueryBuilder.set(status, statusdata))
                        .where(QueryBuilder.eq(TItaskId, taskIdData)).and(eq(implementorId,row.getUUID(implementorId)));

                //TaskByUserRespo
                Update.Where taskByUserResp = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserResp())
                        .with()
                        .and(QueryBuilder.set(TPtaskStatus, statusdata))
                        .where(QueryBuilder.eq(TItaskId, taskIdData)).and(eq(responsibleId,row.getUUID(responsibleId)));


                //SubfeatureByTask
                Update.Where subFeaturebyTask = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByTask())
                        .with()
                        .and(QueryBuilder.set(status, statusdata))
                        .where(QueryBuilder.eq(TItaskId, taskIdData)).and(eq(subfeatureId,row.getUUID(subfeatureId)));

                //TaskByProject
                Update.Where taskByProject = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject())
                        .with()
                        .and(QueryBuilder.set(TPtaskStatus, statusdata))
                        .where(QueryBuilder.eq(TItaskId, taskIdData)).and(eq(projectId,row.getUUID(projectId)));

                batch.add(taskUpdate);
                batch.add(taskByUserImplementer);
                batch.add(taskByUserResp);
                batch.add(subFeaturebyTask);
                batch.add(taskByProject);

                ResultSet batchExecute = configSession.getSession().execute(batch);
                if (batchExecute.wasApplied()) {
                    resultModel.setError(0);
                    resultModel.setResult(requirementsProperties.getSuccessful());
                } else {
                    resultModel.setError(1);
                    resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
                }

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "DONOTFOUND_THE_TASK"));
            }

        }catch (Exception e){
            logger.error("error statue update: " +e.toString());
            resultModel.setError(1);
            resultModel.setResult(e.toString());

        }
        return resultModel;
    }

    public ResponseModel getTaskByProject(UUID pId, int page, String lang) {
        responseModel.setResult("");
        ArrayList<TaskByProject> taskByProjects = new ArrayList<>();
        logger.info("getTaskByProject");
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject())
                    .where(eq(projectId, pId)).orderBy(QueryBuilder.desc(TItaskId)));

            List<Row> dataAll = rs.all();
            logger.info(" dataAll.size(): " +dataAll.size()
                    +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    TaskByProject taskByProject = new TaskByProject();
                    taskByProjects.add(setTaskByProject(row, taskByProject));
//                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (taskByProjects.size() > 0 ) {
                responseModel.setContent(taskByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(taskByProjects.size());

            }else {
                responseModel.setContent(taskByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(taskByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    /**
     * @author Masoomeh
     * @param taskId
     * @param lang
     * @return
     */
    public ResultModel deleteTask(UUID taskId, String lang) {
        responseModel.setResult("");
        logger.warn("deleteTask" );
        batch = QueryBuilder.batch();

        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select().all().
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getTask()).allowFiltering()
                    .where(QueryBuilder.eq(id, taskId)));

            List<Row> rows = resultset.all();
            if (rows.size()>0) {
                Row row = rows.get(0);

                logger.info("row: " + row);
                Delete.Where deleteTask = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getTask())
                        .where(eq(id, taskId)).and(eq(time,row.getUUID(time)));

                Delete.Where deleteTaskByPro = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByProject())
                        .where(eq(TItaskId, taskId)).and(eq(projectId,row.getUUID(projectId)));

                Delete.Where deleteTaskByUserImpl = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserImplementer())
                        .where(eq(TItaskId, taskId)).and(eq(implementorId,row.getUUID(implementorId)));

                Delete.Where deleteTaskByUserResp = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserResp())
                        .where(eq(TItaskId, taskId)).and(eq(responsibleId,row.getUUID(responsibleId)));

                Delete.Where deleteNotificationByTask = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getNotificationByTask())
                        .where(eq(TItaskId, taskId));


                Delete.Where deleteSubFeatureByTask = QueryBuilder.delete()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByTask())
                        .where(eq(TItaskId, taskId)).and(eq(subfeatureId,row.getUUID(subfeatureId)));

                batch.add(deleteTask);
                batch.add(deleteTaskByPro);
                batch.add(deleteTaskByUserImpl);
                batch.add(deleteTaskByUserResp);
                batch.add(deleteNotificationByTask);
                batch.add(deleteSubFeatureByTask);

                configSession.getSession().execute(batch);
                logger.info("batch: " + batch.isIdempotent() + " batch: " +batch);


            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "DONOTFOUND_THE_TASK"));
            }

        }catch (Exception e){

            resultModel.setResult(requirementsProperties.getFail());
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            logger.info(e.toString() + " responseModel: " +responseModel.getError() + " result: " +responseModel.getResult());
        }

        return resultModel;
    }



    public ResponseModel getTaskByUserImpl(UUID implementerIdData,int page, String lang) {
        responseModel.setResult("");
        ArrayList<TaskByUserImplementer> taskByUserImplementers = new ArrayList<>();
        logger.info("getTaskByUserImpl");
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserImplementer())
                    .where(eq(implementorId, implementerIdData)).orderBy(QueryBuilder.desc(TItaskId)));

            List<Row> dataAll = rs.all();
            logger.info(" dataAll.size(): " +dataAll.size() + " implementorId: " +implementerIdData
                    +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    TaskByUserImplementer taskByUserImplementer = new TaskByUserImplementer();
                    taskByUserImplementers.add(setTaskByUserImplementer(row, taskByUserImplementer));
                    System.out.println("uuiddddd: " + row.getUUID(TItaskId));
                }
            }

            if (taskByUserImplementers.size() > 0 ) {
                responseModel.setContent(taskByUserImplementers);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(taskByUserImplementers.size());
                logger.info("in if");

            }else {
                responseModel.setContent(taskByUserImplementers);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
                logger.info("in else");
            }
        }catch (Exception e){
            responseModel.setContent(taskByUserImplementers);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
            logger.info("in exception");
        }
        return responseModel;
    }



    public ResponseModel getTaskByUserResp(UUID ResponsibleId,int page, String lang) {
        responseModel.setResult("");
        ArrayList<TaskByUserResponsible> taskByUserResponsibles = new ArrayList<>();
        logger.info("getTaskByUserResp");
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getTaskByUserResp())
                    .where(eq(responsibleId, ResponsibleId)).orderBy(QueryBuilder.desc(TItaskId)));

            List<Row> dataAll = rs.all();
            logger.info(" dataAll.size(): " +dataAll.size()
                    +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    TaskByUserResponsible taskByUserResponsible= new TaskByUserResponsible();
                    taskByUserResponsibles.add(setTaskUserRes(row, taskByUserResponsible));
//                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (taskByUserResponsibles.size() > 0 ) {
                responseModel.setContent(taskByUserResponsibles);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(taskByUserResponsibles.size());

            }else {
                responseModel.setContent(taskByUserResponsibles);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(taskByUserResponsibles);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }



    public ResponseModel getTaskBySubFeature(UUID subFeatureId,int page, String lang) {
        responseModel.setResult("");
        ArrayList<SubfeatureByTask> subfeatureByTasks = new ArrayList<>();
        logger.info("getTaskBySubFeature");
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByTask())
                    .where(eq(subfeatureId, subFeatureId)).orderBy(QueryBuilder.desc(TItaskId)));

            List<Row> dataAll = rs.all();
            logger.info(" dataAll.size(): " +dataAll.size()
                    +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    SubfeatureByTask subfeatureByTask= new SubfeatureByTask();
                    subfeatureByTasks.add(setSubFeatureByTask(row, subfeatureByTask));
//                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (subfeatureByTasks.size() > 0 ) {
                responseModel.setContent(subfeatureByTasks);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(subfeatureByTasks.size());

            }else {
                responseModel.setContent(subfeatureByTasks);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(subfeatureByTasks);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }
    private TaskByProject setTaskByProject(Row row, TaskByProject taskByProject) {

        taskByProject.setProjectId(row.getUUID(projectId));
        taskByProject.setTaskId(row.getUUID(TItaskId));
        taskByProject.setprojectName(row.getString(projectName));
        taskByProject.settaskTitle(row.getString(TItaskTitle));

        if(((UDTValue)row.getObject(TPmember)) != null) {
            taskByProject.setMember(getMemeberTypeObject(((UDTValue) row.getObject(TPmember))));
        }
        taskByProject.setDescTask(row.getString(desc));
        taskByProject.setTaskStartDate(row.getUUID(TPtaskStartDate));
        taskByProject.setTaskEndDate(row.getUUID(TPtaskEndDate));
        taskByProject.setTaskStatus(row.getInt(TPtaskStatus));
        taskByProject.setTaskDuration(row.getString(taskDuration));
        taskByProject.setProgressRate(row.getInt(progressRate));
        taskByProject.setStatus(row.getInt(status));
        return taskByProject;
    }

    private MemberType getMemeberTypeObject(UDTValue object) {
        MemberType memberType = new MemberType();
        logger.info("object: " +object);
        memberType.setId((object).getUUID("id"));
        memberType.setImagePath((object).getString("imagePath"));
        memberType.setPhoneNumber((object).getString("phoneNumber"));
        memberType.setName((object).getString("name"));
        memberType.setEmail((object).getString("email"));
        memberType.setFamily((object).getString("family"));
        memberType.setUserName((object).getString("userName"));
        return memberType;
    }

    private SubfeatureByTask setSubFeatureByTask(Row row, SubfeatureByTask subfeatureByTask) {
        subfeatureByTask.setsubfeatureId(row.getUUID(STsubfeatureId));
        subfeatureByTask.settaskId(row.getUUID(STtaskId));
        subfeatureByTask.settaskTitle(row.getString(STtaskTitle));
        subfeatureByTask.setsubFeatureTitle(row.getString(STsubFeatureTitle));
        subfeatureByTask.settaskDesc(row.getString(STtaskDesc));
        subfeatureByTask.setStatus(row.getInt(status));
        subfeatureByTask.setModifyTime(row.getUUID(STmodifyTime));

        return subfeatureByTask;
    }

    private TaskByUserImplementer setTaskByUserImplementer(Row row, TaskByUserImplementer taskByUserImplementer) {

        taskByUserImplementer.setTaskId(row.getUUID(TItaskId));
        taskByUserImplementer.setImplementorId(row.getUUID(TIimplementorId));
        taskByUserImplementer.setResponsibleId(row.getUUID(TIresponsibleId));
        if(row.getObject(TIimplementor)!= null){
            taskByUserImplementer.setImplementor(getMemeberTypeObject((UDTValue) row.getObject(TIimplementor)));
        }
        if(row.getObject(TIresponsible)!= null) {
            taskByUserImplementer.setResponsible(getMemeberTypeObject((UDTValue) row.getObject(TIresponsible)));
        }
        taskByUserImplementer.setProjectName(row.getString(TIprojectName));
        taskByUserImplementer.setTaskTitle(row.getString(TItaskTitle));
        taskByUserImplementer.setStatus(row.getInt(status));
        taskByUserImplementer.setTaskDuration(row.getString(TItaskDuration));
        taskByUserImplementer.setProgressRate(row.getInt(TIprogressRate));
        taskByUserImplementer.setTime(row.getUUID(time));
        taskByUserImplementer.setModifyTime(row.getUUID(TImodifyTime));

        logger.info("setTaskByUserImplementer: " +taskByUserImplementer.getTaskId());
        return taskByUserImplementer;
    }

    private TaskByUserResponsible setTaskUserRes(Row row, TaskByUserResponsible taskByUserResponsible) {
        taskByUserResponsible.setTaskId(row.getUUID(TItaskId));
        taskByUserResponsible.setImplementorId(row.getUUID(TIimplementorId));
        taskByUserResponsible.setResponsibleId(row.getUUID(TIresponsibleId));
        if(row.getObject(TIimplementor)!= null) {
            taskByUserResponsible.setImplementor(getMemeberTypeObject((UDTValue) row.getObject(TIimplementor)));
        }
        if(row.getObject(TIresponsible)!= null) {
            taskByUserResponsible.setResponsible(getMemeberTypeObject((UDTValue) row.getObject(TIresponsible)));
        }
        taskByUserResponsible.setProjectName(row.getString(TIprojectName));
        taskByUserResponsible.setTaskTitle(row.getString(TItaskTitle));
        taskByUserResponsible.setStatus(row.getInt(status));
        taskByUserResponsible.setTaskDuration(row.getString(TItaskDuration));
        taskByUserResponsible.setProgressRate(row.getInt(TIprogressRate));
        taskByUserResponsible.setTime(row.getUUID(time));
        taskByUserResponsible.setModifyTime(row.getUUID(TImodifyTime));

        return taskByUserResponsible;
    }

    public ResponseModel filterTask(String implementorId, String responsibleId, String startDate,
                                    String endDate, String priority, String status, int page, String lang) {
//      SELECT * FROM task WHERE implementorid= fe599768-4ff6-463b-b29f-851dc6927244
// 		AND responsibleid = fe599768-4ff6-463b-b29f-851dc6927244
// 		AND startdate=6f6ecf22-801d-11e8-ae9c-27682331bf09
// 		AND enddate = 6f6ecf22-801d-11e8-ae9c-27682331bf09
// 		AND taskpriority='string' AND status =null ALLOW FILTERING

        ArrayList<Task> tasks = new ArrayList<>();
        try {

            int index =  10*(page-1) ;

            boolean andStatement = false;
            String query = "";
            String firstPart = "SELECT * FROM task ";
            query = query + firstPart;
            if (implementorId != null && !implementorId.equalsIgnoreCase("")) {
                query = query + " WHERE implementorid = " + implementorId;
                andStatement = true;
            }
            if (responsibleId != null && !responsibleId.equalsIgnoreCase("")) {
                if (andStatement) {
                    query = query + " And responsibleid = " + responsibleId;
                } else {
                    query = query + " WHERE responsibleid = " + responsibleId;
                    andStatement = true;
                }
            }
            if (startDate != null && !startDate.equalsIgnoreCase("")) {
                if (andStatement) {
                    query = query + " And startdate = " + startDate;
                } else {
                    query = query + " WHERE startdate = " + startDate;
                    andStatement = true;
                }
            }
            if (endDate != null && !endDate.equalsIgnoreCase("")) {
                if (andStatement) {
                    query = query + " AND enddate = " + endDate;
                } else {
                    query = query + " WHERE enddate = " + endDate;
                    andStatement = true;
                }
            }
            if (priority != null && !priority.equalsIgnoreCase("")) {
                if (andStatement) {
                    query = query + " AND taskpriority = '" + priority + "'";
                } else {
                    query = query + " WHERE taskpriority = '" + priority + "'";
                    andStatement = true;
                }
            }
            if (status != null && !status.equalsIgnoreCase("")) {
                if (andStatement) {
                    query = query + " And status = " + status;
                } else {
                    query = query + " Where status = " + status;
                    andStatement = true;
                }

            }
            ResultSet resultSet = configSession.getSession().execute(query + " ALLOW FILTERING ");

            List<Row> rows = resultSet.all();
            if(resultSet.wasApplied() && rows.size()>0) {

                int maxpage = page * 10;
                for (int i = index; ((i < maxpage) && (i < rows.size())); i++) {
                    Row row = rows.get(i);
                    Task task = new Task();
                    tasks.add(setTask(row, task));
                }
            }
            logger.info("tag query: " +query );
            if (resultSet.wasApplied()) {
                responseModel.setContent(tasks);
                responseModel.setRecordCount(tasks.size());
                responseModel.setResult(requirementsProperties.getSuccessful());
                logger.info("was applied");

            } else {
                responseModel.setError(requirementsProperties.getFail());
                responseModel.setContent(tasks);
                responseModel.setRecordCount(tasks.size());
                responseModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){

            responseModel.setContent(tasks);
            responseModel.setRecordCount(tasks.size());
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    private Task setTask(Row row, Task task) {
        task.setId(row.getUUID(id));
        task.setImplementorId(row.getUUID(implementorId));
        task.setResponsibleId(row.getUUID(responsibleId));
        if ((UDTValue) row.getObject(implementor) != null) {
            task.setImplementor(getMemeberTypeObject((UDTValue) row.getObject(implementor)));
        }
        if ((UDTValue) row.getObject(responsible) != null) {
            task.setResponsible(getMemeberTypeObject((UDTValue) row.getObject(responsible)));
        }
        task.setProjectId(row.getUUID(projectId));
        task.setProjectName(row.getString(projectName));
        task.setModifyTime(row.getUUID(modifyTime));
        task.setTime(row.getUUID(time));
        task.setSubfeatureId(row.getUUID(subfeatureId));
        task.setSubfeatureTitle(row.getString(subfeatureTitle));
        task.setTitle(row.getString(title));
        task.setDescTask(row.getString(desc));
        task.setStartDate(row.getUUID(startDate));
        task.setEndDate(row.getUUID(endDate));
        task.setPeriodic(row.getBool(isPeriodic));
        task.setStatus(row.getInt(status));
        task.setPeriodicType(row.getString(periodicType));
        task.setPeriodicTypeStart(row.getLong(periodicTypeStart));
        task.setPeriodicTypeStop(row.getLong(periodicTypeStop));
        task.setProgressRate(row.getInt(progressRate));
        task.setTaskDuration(row.getString(taskDuration));
        task.setTaskPriority(row.getString(taskPriority));

        return task;
    }
}
