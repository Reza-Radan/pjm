package com.progetto.projectmanagement.service.meeting;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.meeting.MeetingByFeature;
import com.progetto.projectmanagement.domain.meeting.MeetingByProject;
import com.progetto.projectmanagement.domain.meeting.MeetingByRelease;
import com.progetto.projectmanagement.domain.meeting.MeetingRepository;
import ir.dabacenter.projectmanagement.domain.*;
import ir.dabacenter.projectmanagement.domain.meeting.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MeetingService implements IMeetingService {

	@Autowired
    MeetingRepository meetingRepository;

	@Autowired
    ResponseModel responseModel;

	@Autowired
    ResultModel resultModel;

	@Autowired
	MeetingValidator meetingValidator;

	@Autowired
    RequirementsProperties requirementsProperties;

	org.slf4j.Logger logger = LoggerFactory.getLogger(MeetingService.class);


	@Override
	public ResponseModel addMeetingByFeature(MeetingByFeature meetingByFeature, String lang) {
		resultModel = meetingValidator.addMeetingByFeatureValidate(meetingByFeature ,lang);
		if(resultModel.getError() == 0){
			resultModel = meetingRepository.addMeetingByFeature(meetingByFeature ,lang);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel addMeetingByRelease(MeetingByRelease meetingByRelease, String lang) {
		resultModel = meetingValidator.addMeetingByReleaseValidate(meetingByRelease ,lang);
		if(resultModel.getError() == 0){
			resultModel = meetingRepository.addMeetingByRelease(meetingByRelease ,lang);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel addMeetingByProject(MeetingByProject meetingByProject, String lang) {
		resultModel = meetingValidator.addMeetingByProjectValidate(meetingByProject ,lang);
		if(resultModel.getError() == 0){
			resultModel = meetingRepository.addMeetingByProject(meetingByProject ,lang);
			if (resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else{
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModel.getResult());
			}
		}else {
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getMeetingByFeature(UUID featureId, String lang) {

		resultModel = meetingValidator.getMeetingByFeatureValidate(featureId ,lang);
		if(resultModel.getError() ==0){
			responseModel = meetingRepository.getMeetingByFeature(featureId ,lang);

		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getMeetingByMember(UUID memberId, String lang) {
		return null;
	}

	@Override
	public ResponseModel getMeetingByRelease(UUID releaseId, String lang) {
		return null;
	}

	@Override
	public ResponseModel updateMeetingByFeature(MeetingByFeature meetingByFeature, String lang) {

		resultModel = meetingValidator.addMeetingByFeatureValidate(meetingByFeature ,lang);
		if(resultModel.getError() ==0){
			resultModel = meetingRepository.updateMeetingByFeature(meetingByFeature,lang);
			if(resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else {
				responseModel.setResult(resultModel.getResult());
				responseModel.setRecordCount(0);
				responseModel.setError(resultModel.getResult());
			}
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	@Override
	public ResponseModel updateMeetingByProject(MeetingByProject meetingByProject, String lang) {
		resultModel = meetingValidator.addMeetingByProjectValidate(meetingByProject ,lang);
		if(resultModel.getError() ==0){
			resultModel = meetingRepository.updateMeetingByProject(meetingByProject,lang);
			if(resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else {
				responseModel.setResult(resultModel.getResult());
				responseModel.setRecordCount(0);
				responseModel.setError(resultModel.getResult());
			}
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}

	@Override
	public ResponseModel updateMeetingByRelease(MeetingByRelease meetingByRelease, String lang) {
		resultModel = meetingValidator.addMeetingByReleaseValidate(meetingByRelease ,lang);
		if(resultModel.getError() ==0){
			resultModel = meetingRepository.updateMeetingByRelease(meetingByRelease,lang);
			if(resultModel.getError() ==0){
				responseModel.setResult(resultModel.getResult());
			}else {
				responseModel.setResult(resultModel.getResult());
				responseModel.setRecordCount(0);
				responseModel.setError(resultModel.getResult());
			}
		}else{
			responseModel.setResult(resultModel.getResult());
			responseModel.setRecordCount(0);
			responseModel.setError(resultModel.getResult());
		}
		return responseModel;
	}
}