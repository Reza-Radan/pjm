package com.progetto.projectmanagement.service.issue;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.issue.IssueRepository;
import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.issue.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class IssueService implements IIssueService {

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	ResponseModel responseModel;

	@Autowired
	ResultModel resultModel;

	@Autowired
	IssueValidator issueValidator;

	@Autowired
	RequirementsProperties requirementsProperties;
	/**
	 * 
	 * @param memberId

	 */
	public ResponseModel getIssueByMember(String memberId ,String lang) {
		responseModel.setResult("");

		resultModel = issueValidator.issueByMemberValidate(UUID.fromString(memberId),lang);
		if(resultModel.getError() ==0){
			responseModel = issueRepository.getIssueByMember(memberId ,lang);
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}


		return responseModel;
	}

	/**
	 *
	 * @param releaseId
	 */
	public ResponseModel getIssueByRelease( UUID releaseId ,String lang) {
		responseModel.setResult("");
		resultModel = issueValidator.issueByReleaseValidate(releaseId,lang);
		if(resultModel.getError() ==0){
			responseModel = issueRepository.getIssueByRelease( releaseId,lang);
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}

		return responseModel;
	}

	/**
	 * 
	 * @param taskId
	 */
	public ResponseModel getIssueByTask(UUID taskId,String lang) {
		responseModel.setResult("");
		resultModel = issueValidator.issueByTaskValidate(taskId,lang);
		if(resultModel.getError() ==0){
		responseModel = issueRepository.getIssueByTask( taskId,lang);
//			responseModel = issueRepository.getIssueByRelease( releaseId,lang);
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	/**
	 * 
	 * @param issuByRelease
	 */
	public ResponseModel addIssueByRelease(IssueByRelease issuByRelease ,String lang) {

		//validation
		responseModel.setResult(requirementsProperties.getFail());
		responseModel.setError(null);
		responseModel.setSystemError(null);
		responseModel.setContent(null);
		responseModel.setContents(null);

		resultModel = issueValidator.issueAddByReleaseValidate(issuByRelease ,lang);
		if(resultModel.getError() == 0 ){
			resultModel = issueRepository.addIssueByRelease(issuByRelease,lang);
			if(resultModel.getError() == 0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else{
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	/**
	 * @author masoomeh
	 * @param issueByTask
	 */
	public ResponseModel addIssueByTask(IssueByTask issueByTask,String lang) {
		//validation
		responseModel.setResult(requirementsProperties.getFail());
		responseModel.setError(null);
		responseModel.setSystemError(null);
		responseModel.setContent(null);
		responseModel.setContents(null);

		resultModel = issueValidator.issueAddByTaskValidate(issueByTask,lang);
		if(resultModel.getError() == 0 ){
			resultModel = issueRepository.addIssueByTask(issueByTask,lang);
			if(resultModel.getError() == 0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else{
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	public ResponseModel updateIssueByTask(IssueByTask issueByTask, String lang) {
		//validation
		responseModel.setResult(requirementsProperties.getFail());
		responseModel.setError(null);
		responseModel.setSystemError(null);
		responseModel.setContent(null);
		responseModel.setContents(null);

		resultModel = issueValidator.issueAddByTaskValidate(issueByTask,lang);
		if(resultModel.getError() == 0 ){
			resultModel = issueRepository.updateIssueByTask(issueByTask,lang);
			if(resultModel.getError() == 0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else{
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	public ResponseModel updateIssueByRelease(IssueByRelease issuByRelease, String lang) {

		//validation
		responseModel.setResult(requirementsProperties.getFail());
		responseModel.setError(null);
		responseModel.setSystemError(null);
		responseModel.setContent(null);
		responseModel.setContents(null);

		resultModel = issueValidator.issueAddByReleaseValidate(issuByRelease,lang);
		if(resultModel.getError() == 0 ){
			resultModel = issueRepository.updateIssueByRelease(issuByRelease,lang);
			if(resultModel.getError() == 0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else{
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}
}