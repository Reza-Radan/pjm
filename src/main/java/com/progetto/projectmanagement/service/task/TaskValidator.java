package com.progetto.projectmanagement.service.task;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.task.SubfeatureByTask;
import com.progetto.projectmanagement.domain.task.Task;
import com.progetto.projectmanagement.domain.task.TaskByProject;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.mapping.CassandraType;

import java.util.UUID;

public class TaskValidator {
    
    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    org.slf4j.Logger logger = LoggerFactory.getLogger(TaskValidator.class);


    public ResultModel taskValidate(Task task){

        logger.info("issss pr ..."+task.isPeriodic());
        if(!validateField(task.getImplementorId())) {
            resultModel.setError(1);
            resultModel.setResult(" implementorId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getResponsibleId())){
            resultModel.setError(1);
            resultModel.setResult(" responsibleId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getProjectName())){
            resultModel.setError(1);
            resultModel.setResult(" projectName " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getSubfeatureId())) {
            resultModel.setError(1);
            resultModel.setResult(" subfeatureId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getSubfeatureTitle())){
            resultModel.setError(1);
            resultModel.setResult(" subfeatureTitle " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTitle())) {
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getDescTask())){
            resultModel.setError(1);
            resultModel.setResult(" descTask " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getStartDate())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskDuration())){
            resultModel.setError(1);
            resultModel.setResult(" taskDuration " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getEndDate())){
            resultModel.setError(1);
            resultModel.setResult(" endDate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getStatus())) {
            resultModel.setError(1);
            resultModel.setResult(" status " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getProgressRate())) {
            resultModel.setError(1);
            resultModel.setResult(" progressRate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskDuration())) {
            resultModel.setError(1);
            resultModel.setResult(" taskDuration " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskPriority())) {
            resultModel.setError(1);
            resultModel.setResult(" taskPriority " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.isPeriodic())) {
            resultModel.setError(1);
            resultModel.setResult(" isPeriodic " + resultModel.getErrorTextByLanguage(task.getLang(), "REQUIREDFIELD"));

        }else if(validateField(task.isPeriodic())) {

                if (task.isPeriodic() == true) {
                    if (!validateField(task.getPeriodicType())) {
                        resultModel.setError(1);
                        resultModel.setResult(" periodicType " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

                    } else if (!validateField(task.getPeriodicTypeStart())) {
                        resultModel.setError(1);
                        resultModel.setResult(" periodicTypeStart " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

                    } else if (!validateField(task.getPeriodicTypeStop())) {
                        resultModel.setError(1);
                        resultModel.setResult(" periodicTypeStop " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));
                    }else {
                        resultModel.setResult(requirementsProperties.getSuccessful());
                        resultModel.setError(0);
                    }

                }else {
                    resultModel.setResult(requirementsProperties.getSuccessful());
                    resultModel.setError(0);
                }

        } else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel taskBySubfeatureValidate(SubfeatureByTask task){

        if(!validateField(task.gettaskTitle())) {
            resultModel.setError(1);
            resultModel.setResult(" taskTitle " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.gettaskDesc())){
            resultModel.setError(1);
            resultModel.setResult(" taskDesc " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getsubfeatureId())){
            resultModel.setError(1);
            resultModel.setResult(" subfeatureId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        } else if(!validateField(task.getsubFeatureTitle())){
            resultModel.setError(1);
            resultModel.setResult(" subFeatureTitle " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }



    public ResultModel taskByProjectValidate(TaskByProject task){

        if(!validateField(task.getTaskTitle())) {
            resultModel.setError(1);
            resultModel.setResult(" taskTitle " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskDesc())){
            resultModel.setError(1);
            resultModel.setResult(" taskDesc " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskStartDate())){
            resultModel.setError(1);
            resultModel.setResult(" taskStartDate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskDuration())){
            resultModel.setError(1);
            resultModel.setResult(" taskDuration " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskEndDate())){
            resultModel.setError(1);
            resultModel.setResult(" taskEndDate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getTaskStatus())) {
            resultModel.setError(1);
            resultModel.setResult(" taskStatus " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getProgressRate())) {
            resultModel.setError(1);
            resultModel.setResult(" progressRate " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(task.getProjectName())) {
            resultModel.setError(1);
            resultModel.setResult(" projectName " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        }else  if(!validateField(task.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        } if(!validateField(task.getMember())) {
            resultModel.setError(1);
            resultModel.setResult(" member " + resultModel.getErrorTextByLanguage(task.getLang(),"REQUIREDFIELD"));

        } else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getTaskIdValidate(UUID taskId,String lang){

        if(!validateField(taskId)) {
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel getStatusValidate(UUID id, int status ,String lang){

        if(!validateField(id)) {
            resultModel.setError(1);
            resultModel.setResult(" id " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(status)) {
            resultModel.setError(1);
            resultModel.setResult(" status " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel projectIdValidate(UUID pId, String lang) {

        if(!validateField(pId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

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
