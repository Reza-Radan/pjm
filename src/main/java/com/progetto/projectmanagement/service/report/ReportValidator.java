package com.progetto.projectmanagement.service.report;

import com.progetto.projectmanagement.domain.report.ProjectReport;
import com.progetto.projectmanagement.domain.report.ReportByMeeting;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.report.ReportByTask;
import com.progetto.projectmanagement.domain.report.ReportByUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ReportValidator {

//    public ResponseModel addReportByTask(ReportByTask reposrtTask) {
//    public ResponseModel addReportByUser(ReportByUser reportByUser) {
//    public ResponseModel getReportByProject(UUID projectId) {
//    public ResponseModel getReportByTask(UUID taskId, UUID projectId) {
//    public ResponseModel getReportByUser(UUID userId) {
//    public ResponseModel getReportByMeeting(UUID meetingId) {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel reportProjectValidate(ProjectReport projectReport){

        if(!validateField(projectReport.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(projectReport.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectReport.getProjectTitle())){
            resultModel.setError(1);
            resultModel.setResult(" projectTitle " + resultModel.getErrorTextByLanguage(projectReport.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectReport.getReportDesc())){
            resultModel.setError(1);
            resultModel.setResult(" reportDesc " + resultModel.getErrorTextByLanguage(projectReport.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectReport.getAttachment())){
            resultModel.setError(1);
            resultModel.setResult(" attachment " + resultModel.getErrorTextByLanguage(projectReport.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel reportMeetingValidate(ReportByMeeting projectMeeting){

        if(!validateField(projectMeeting.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getMeetingTitle())){
            resultModel.setError(1);
            resultModel.setResult(" meetingTitle " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getReportDesc())){
            resultModel.setError(1);
            resultModel.setResult(" reportDesc " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getAttachment())){
            resultModel.setError(1);
            resultModel.setResult(" attachment " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getDateMeeting())){
            resultModel.setError(1);
            resultModel.setResult(" dateMeeting " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getAuthor())){
            resultModel.setError(1);
            resultModel.setResult(" author " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getDateReport())){
            resultModel.setError(1);
            resultModel.setResult(" dateReport " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getMeetingId())){
            resultModel.setError(1);
            resultModel.setResult(" meetingId " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        } else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }



    public ResultModel reportTaskValidate(ReportByTask projectMeeting){

        if(!validateField(projectMeeting.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getTaskTitle())){
            resultModel.setError(1);
            resultModel.setResult(" taskTitle " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getReportDesc())){
            resultModel.setError(1);
            resultModel.setResult(" reportDesc " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getAttachment())){
            resultModel.setError(1);
            resultModel.setResult(" attachment " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getTime())){
            resultModel.setError(1);
            resultModel.setResult(" date " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectMeeting.getTaskId())){
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(projectMeeting.getLang(),"REQUIREDFIELD"));

        } else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel reportUserValidate(ReportByUser projectByUser){

        if(!validateField(projectByUser.getUserId())) {
            resultModel.setError(1);
            resultModel.setResult(" userId " + resultModel.getErrorTextByLanguage(projectByUser.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectByUser.getTaskTitle())){
            resultModel.setError(1);
            resultModel.setResult(" taskTitle " + resultModel.getErrorTextByLanguage(projectByUser.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectByUser.getReportDesc())){
            resultModel.setError(1);
            resultModel.setResult(" reportDesc " + resultModel.getErrorTextByLanguage(projectByUser.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectByUser.getAttachment())){
            resultModel.setError(1);
            resultModel.setResult(" attachment " + resultModel.getErrorTextByLanguage(projectByUser.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(projectByUser.getTaskId())){
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(projectByUser.getLang(),"REQUIREDFIELD"));

        } else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel idTaskValidate(UUID taskId, String lang) {
        if(!validateField(taskId)) {
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }/*else if(!validateField(projectId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }*/else{
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel idUserValidate(UUID taskId, String lang) {
        if(!validateField(taskId)) {
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }/*else if(!validateField(projectId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }*/else{
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel idProjectValidate(UUID pId, String lang) {
        if(!validateField(pId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }/*else if(!validateField(projectId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }*/else{
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
