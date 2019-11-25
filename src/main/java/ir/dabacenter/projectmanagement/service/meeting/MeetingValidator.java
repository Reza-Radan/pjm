package ir.dabacenter.projectmanagement.service.meeting;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.meeting.Meeting;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByFeature;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByProject;
import ir.dabacenter.projectmanagement.domain.meeting.MeetingByRelease;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MeetingValidator {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel meetingValidate(Meeting meeting ,String lang){

        if(!validateField(meeting.getTitle())) {
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStart_date())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getDuration_time())){
            resultModel.setError(1);
            resultModel.setResult(" duration " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getCreate_member())){
            resultModel.setError(1);
            resultModel.setResult(" createMember " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStart_meeting())) {
            resultModel.setError(1);
            resultModel.setResult(" startMeeting " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMember())) {
            resultModel.setError(1);
            resultModel.setResult(" member " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel addMeetingByFeatureValidate(MeetingByFeature meeting,String lang){

        if(!validateField(meeting.getfeatureId())) {
            resultModel.setError(1);
            resultModel.setResult(" featureId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getFeature_name())){
            resultModel.setError(1);
            resultModel.setResult(" featureName " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeeting_title())){
            resultModel.setError(1);
            resultModel.setResult(" meetingTitle " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeeting_desc())){
            resultModel.setError(1);
            resultModel.setResult(" meetingDesc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStart_date())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getDuration_time())) {
            resultModel.setError(1);
            resultModel.setResult(" durationTime " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getCreate_member())) {
            resultModel.setError(1);
            resultModel.setResult(" createMember " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getProject_id())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  if(!validateField(meeting.getStart_meeting())) {
            resultModel.setError(1);
            resultModel.setResult(" startMeeting " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel addMeetingByReleaseValidate(MeetingByRelease meeting,String lang){

        if(!validateField(meeting.getReleaseId())) {
            resultModel.setError(1);
            resultModel.setResult(" releaseId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getReleaseName())){
            resultModel.setError(1);
            resultModel.setResult(" releaseName " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeetingTitle())){
            resultModel.setError(1);
            resultModel.setResult(" meetingTitle " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeetingDesc())){
            resultModel.setError(1);
            resultModel.setResult(" meetingDesc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStartDate())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getDurationTime())) {
            resultModel.setError(1);
            resultModel.setResult(" durationTime " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getCreateMember())) {
            resultModel.setError(1);
            resultModel.setResult(" createMember " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStartMeeting())) {
            resultModel.setError(1);
            resultModel.setResult(" startMeeting " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel addMeetingByProjectValidate(MeetingByProject meeting,String lang){

        if(!validateField(meeting.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getProjectName())){
            resultModel.setError(1);
            resultModel.setResult(" projectName " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeetingTitle())){
            resultModel.setError(1);
            resultModel.setResult(" meetingTitle " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getMeetingDesc())){
            resultModel.setError(1);
            resultModel.setResult(" meetingDesc " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStartDate())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getDurationTime())) {
            resultModel.setError(1);
            resultModel.setResult(" durationTime " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getCreateMember())) {
            resultModel.setError(1);
            resultModel.setResult(" createMember " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getProjectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else if(!validateField(meeting.getStartMeeting())) {
            resultModel.setError(1);
            resultModel.setResult(" startMeeting " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel getMeetingByMemberValidate(UUID memberId,String lang){

        if(!validateField(memberId)) {
            resultModel.setError(1);
            resultModel.setResult(" memberId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getMeetingByReleaseValidate(UUID releaseId,String lang){

        if(!validateField(releaseId)) {
            resultModel.setError(1);
            resultModel.setResult(" releaseId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getMeetingByFeatureValidate(UUID featureId,String lang){

        if(!validateField(featureId)) {
            resultModel.setError(1);
            resultModel.setResult(" featuredId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

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
