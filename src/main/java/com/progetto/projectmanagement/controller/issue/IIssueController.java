package com.progetto.projectmanagement.controller.issue;

import com.progetto.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.issue.IssueByRelease;
import ir.dabacenter.projectmanagement.domain.issue.IssueByTask;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public interface IIssueController {

    ResponseModel getIssueByMember(Map<String,String> requestParams , HttpServletResponse httpServletResponse);
    ResponseModel getIssueByRelease(Map<String,String> requestParams ,HttpServletResponse httpServletResponse);
    ResponseModel getIssueByTask(UUID taskId,String lang ,HttpServletResponse httpServletResponse);
    ResponseModel addIssueByRelease(IssueByRelease issuByRelease ,String lang,HttpServletResponse httpServletResponse);
    ResponseModel addIssueByTask(IssueByTask issueByTask,String lang,HttpServletResponse httpServletResponse);
    ResponseModel updateIssueByTask(IssueByTask issueByTask,String lang,HttpServletResponse httpServletResponse);
    ResponseModel updateIssueByRelease(IssueByRelease issuByRelease ,String lang,HttpServletResponse httpServletResponse);


}
