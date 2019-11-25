package com.progetto.projectmanagement.service.issue;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.issue.IssueByRelease;
import ir.dabacenter.projectmanagement.domain.issue.IssueByTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class IssueValidator {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;

    public ResultModel issueAddByReleaseValidate(IssueByRelease issue ,String lang){

        if(!validateField(issue.getIssue_title())) {
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getIssue_desc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getStart_date())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getDuration())){
            resultModel.setError(1);
            resultModel.setResult(" duration " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getIssue_from())){
            resultModel.setError(1);
            resultModel.setResult(" issueFrom " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getIssue_to())) {
            resultModel.setError(1);
            resultModel.setResult(" issueTo " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getreleaseId())) {
            resultModel.setError(1);
            resultModel.setResult(" releaseId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getProject_id())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel issueAddByTaskValidate(IssueByTask issue,String lang){

        if(!validateField(issue.getTitle())) {
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getStart_date())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getDuration())){
            resultModel.setError(1);
            resultModel.setResult(" duration " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getIssue_from())){
            resultModel.setError(1);
            resultModel.setResult(" issueFrom " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getIssue_to())) {
            resultModel.setError(1);
            resultModel.setResult(" issueTo " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.getProject_id())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(issue.gettaskId())) {
            resultModel.setError(1);
            resultModel.setResult(" taskId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel issueByMemberValidate(UUID memberId,String lang){

        if(!validateField(memberId)) {
            resultModel.setError(1);
            resultModel.setResult(" memberId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

//        }else if(!validateField(issueId)){
//            resultModel.setError(1);
//            resultModel.setResult(" issueId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

       }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel issueByReleaseValidate(UUID releaseId, String lang){

        if(!validateField(releaseId)) {
            resultModel.setError(1);
            resultModel.setResult(" releaseId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

//        }else if(!validateField(issueId)){
//            resultModel.setError(1);
//            resultModel.setResult(" issueId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel issueByTaskValidate(UUID taskId,String lang){

        if(!validateField(taskId)) {
            resultModel.setError(1);
            resultModel.setResult(" releaseId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

//        }else if(!validateField(issueId)){
//            resultModel.setError(1);
//            resultModel.setResult(" issueId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
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
