package ir.dabacenter.projectmanagement.service.issue;

import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.issue.*;

import java.util.UUID;

public interface IIssueService {

	/**
	 * 
	 * @param memberId
	 */
	ResponseModel getIssueByMember(String memberId,String lang);

	/**
	 *
	 * @param releaseId
	 */
	ResponseModel getIssueByRelease(UUID releaseId,String lang);

	/**
	 * 
	 * @param taskId
	 */
	ResponseModel getIssueByTask(UUID taskId ,String lang);

	/**
	 * 
	 * @param issuByRelease
	 */
	ResponseModel addIssueByRelease(IssueByRelease issuByRelease,String lang);

	/**
	 * 
	 * @param issueByTask
	 */
	ResponseModel addIssueByTask(IssueByTask issueByTask ,String lang);

	/**
	 *
	 * @param issueByTask
	 * @param lang
	 * @return
	 */
	public ResponseModel updateIssueByTask(IssueByTask issueByTask, String lang) ;

	/**
	 *
	 * @param issuByRelease
	 * @param lang
	 * @return
	 */
	public ResponseModel updateIssueByRelease(IssueByRelease issuByRelease, String lang);

}