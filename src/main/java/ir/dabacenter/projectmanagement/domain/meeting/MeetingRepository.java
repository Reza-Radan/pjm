package ir.dabacenter.projectmanagement.domain.meeting;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.*;
import ir.dabacenter.projectmanagement.ConfigSession;
import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.issue.IssueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

public class MeetingRepository {

    org.slf4j.Logger logger = LoggerFactory.getLogger(MeetingRepository.class);
    @Autowired
    ResultModel resultModel;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    ConfigSession configSession;

    @Autowired
    RequirementsProperties requirementsProperties;
    Batch batch;

    //meetingByFeature
    private final String MFmeetingId = "meetingId";
    private final String MFfeatureId ="featureId";
    private final String MFfeatureName = "featureName";
    private final String MFmeetingTitle ="meetingTitle";
    private final String MFmeetingDesc ="meetingDesc";
    private final String MFstartDate = "startDate";
    private final String MFmodifyTime ="modifyTime";
    private final String MFdurationTime = "durationTime";
    private final String MFcreateMember ="createMember";
    private final String MFstartMeeting  = "startMeeting";
    private final String MFprojectId ="projectId";

    //meetingByRelease
    private final String MRmeetingId = "meetingId";
    private final String MRreleaseId ="releaseId";
    private final String MRreleaseName = "releaseName";
    private final String MRmeetingTitle ="meetingTitle";
    private final String MRmeetingDesc ="meetingDesc";
    private final String MRdurationTime = "durationTime";
    private final String MRstartMeeting  = "startMeeting";
    private final String MRstartDate = "startDate";
    private final String MRmodifyTime ="modifyTime";
    private final String MRcreateMember ="createMember";
    private final String MRprojectId ="projectId";

    //meetingByProject
    private final String MPprojectId ="projectId";
    private final String MPprojectName = "projectName";
    private final String MPmeetingTitle ="meetingTitle";
    private final String MPmeetingDesc ="meetingDesc";
    private final String MPstartDate = "startDate";
    private final String MPdurationTime = "durationTime";
    private final String MPmodifyTime ="modifyTime";
    private final String MPcreateMember ="createMember";
    private final String MPstartMeeting  = "startMeeting";


    //meeting
    private final String id = "id";
    private final String title ="title";
    private final String desc = "meetingDesc";
    private final String startDate ="startDate";
    private final String durationTime ="durationTime";
    private final String modifyTime = "modifyTime";
    private final String createMember ="createMember";
    private final String startMeeting = "startMeeting";
    private final String member ="member";

    ResultSet resultset ;

    public ResponseModel getMeetingByFeature(UUID featureId, String lang) {
        logger.warn("issue getMeetingByFeature ");

        ResultSet resultsetIssue = configSession.getSession().execute(QueryBuilder.select()
                .all()
                .from(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByFeature()).allowFiltering()
                .where(eq("featureId", featureId)))/*.and(QueryBuilder.eq(IissueFromString,UUID.fromString(issueId))))*/;

        ArrayList<MeetingByFeature> meetingByFeatures = new ArrayList<>();
        for (Row row:resultsetIssue) {
            MeetingByFeature meetingByFeature = new MeetingByFeature();
            meetingByFeature = setMeetingByFeature(meetingByFeature,row);
            meetingByFeatures.add(meetingByFeature);
            responseModel.setContent(meetingByFeature);
            responseModel.setResult(requirementsProperties.getSuccessful());
        }

        if (meetingByFeatures.size() <= 0) {
            logger.warn("issue null ");
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "ISSUE_NOT_EXIST"));
            responseModel.setRecordCount(0);
        }else {
            responseModel.setRecordCount(meetingByFeatures.size());
        }
        return responseModel;
    }

    private MeetingByFeature setMeetingByFeature(MeetingByFeature meetingByFeature, Row row) {
        meetingByFeature.setMeeting_id(row.getUUID(id));
        meetingByFeature.setfeatureId(row.getUUID(MFfeatureId));
        meetingByFeature.setFeature_name(row.getString(MFfeatureName));
        meetingByFeature.setMeeting_title(row.getString(MFmeetingTitle));
        meetingByFeature.setMeeting_desc(row.getString(MFmeetingDesc));
        meetingByFeature.setStart_date(row.getLong(MFstartDate));
        meetingByFeature.setDuration_time(row.getString(MFdurationTime));
        meetingByFeature.setModifyTime(row.getLong(MFmodifyTime));
//        meetingByFeature.setCreate_member(row.get);
        meetingByFeature.setStart_meeting(row.getLong(MFstartMeeting));
        meetingByFeature.setProject_id(row.getUUID(MFprojectId));
        return meetingByFeature;
    }

    public ResultModel addMeetingByFeature(MeetingByFeature meetingByFeature, String lang) {
        logger.warn("addMeetingByFeature" );
        batch = QueryBuilder.batch();
        try {
            UUID uuid;
            Insert meetingByFeatureInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByFeature())
                    .value(id, uuid = UUID.randomUUID())
                    .value(MFfeatureId, meetingByFeature.getfeatureId())
                    .value(MFfeatureName, meetingByFeature.getFeature_name())
                    .value(MFmeetingTitle, meetingByFeature.getMeeting_title())
                    .value(MFmeetingDesc, meetingByFeature.getMeeting_desc())
                    .value(MFstartDate, meetingByFeature.getStart_date())
                    .value(MFdurationTime, meetingByFeature.getDuration_time())
                    .value(MFmodifyTime, meetingByFeature.getModifyTime());


            Insert meetingInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                    .value(id, uuid)
                    .value(title, meetingByFeature.getMeeting_title())
                    .value(desc, meetingByFeature.getMeeting_desc())
                    .value(startDate, meetingByFeature.getStart_date())
                    .value(durationTime, meetingByFeature.getDuration_time())
                    .value(modifyTime, meetingByFeature.getModifyTime())
//                .value(createMember , meetingByFeature.getCreate_member())
                    .value(startMeeting, meetingByFeature.getStart_meeting());
//                .value(member , meetingByFeature.getMeeting_);


            batch.add(meetingByFeatureInsert);
            batch.add(meetingInsert);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(0);
        }
        return resultModel;
    }

    public ResultModel addMeetingByRelease(MeetingByRelease meetingByRelease, String lang) {
        logger.warn("addMeetingByRelease" );
        batch = QueryBuilder.batch();
        try {
            UUID uuid;
            Insert meetingByFeatureInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByRelease())
                    .value(MRmeetingId, uuid = UUID.randomUUID())
                    .value(MRreleaseId, meetingByRelease.getReleaseId())
                    .value(MRreleaseName, meetingByRelease.getReleaseName())
                    .value(MRmeetingTitle, meetingByRelease.getMeetingTitle())
                    .value(MRmeetingDesc, meetingByRelease.getMeetingDesc())
                    .value(MRstartDate, meetingByRelease.getStartDate())
                    .value(MRdurationTime, meetingByRelease.getDurationTime())
                    .value(MRmodifyTime, meetingByRelease.getModifyTime());


            Insert meetingInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                    .value(id, uuid)
                    .value(title, meetingByRelease.getMeetingTitle())
                    .value(desc, meetingByRelease.getMeetingDesc())
                    .value(startDate, meetingByRelease.getStartDate())
                    .value(durationTime, meetingByRelease.getDurationTime())
                    .value(modifyTime, meetingByRelease.getModifyTime())
//                .value(createMember , meetingByRelease.getCreate_member())
                    .value(startMeeting, meetingByRelease.getStartMeeting());
//                .value(member , meetingByRelease.getMeeting_);

            batch.add(meetingByFeatureInsert);
            batch.add(meetingInsert);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel addMeetingByProject(MeetingByProject meetingByProject, String lang) {
        logger.warn("addMeetingByRelease" );
        batch = QueryBuilder.batch();
        try {
            UUID uuid;
            Insert meetingByFeatureInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByProject())
                    .value(id, uuid = UUID.randomUUID())
                    .value(MPprojectId, meetingByProject.getProjectId())
                    .value(MPprojectName, meetingByProject.getProjectName())
                    .value(MPmeetingTitle, meetingByProject.getMeetingTitle())
                    .value(MPmeetingDesc, meetingByProject.getMeetingDesc())
                    .value(MPstartDate, meetingByProject.getStartDate())
                    .value(MPdurationTime, meetingByProject.getDurationTime())
                    .value(MPmodifyTime, meetingByProject.getModifyTime());


            Insert meetingInsert = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                    .value(id, uuid)
                    .value(title, meetingByProject.getMeetingTitle())
                    .value(desc, meetingByProject.getMeetingDesc())
                    .value(startDate, meetingByProject.getStartDate())
                    .value(durationTime, meetingByProject.getDurationTime())
                    .value(modifyTime, meetingByProject.getModifyTime())
//                .value(createMember , meetingByRelease.getCreate_member())
                    .value(startMeeting, meetingByProject.getStartMeeting());
//                .value(member , meetingByRelease.getMeeting_);

            batch.add(meetingByFeatureInsert);
            batch.add(meetingInsert);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setResult(e.toString());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel updateMeetingByFeature(MeetingByFeature meetingByFeature, String lang){
        try {
            batch = QueryBuilder.batch();
            UUID fetchId = null ,uuid = null ;
            Select.Where selectallow = QueryBuilder.select(id).
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByFeature()).allowFiltering()
                    .where(QueryBuilder.eq(MFmeetingTitle,meetingByFeature.getMeeting_title()))
                    .and(QueryBuilder.eq(MFfeatureId,meetingByFeature.getfeatureId()));

            resultset = configSession.getSession().execute(selectallow);

            for (Row row : resultset) {
                fetchId = row.getUUID(id);
//                featureId = row.getUUID(MFfeatureId);
            }
            if(fetchId == null) {
                uuid = UUID.randomUUID();
//                featureId = meetingByFeature.getfeatureId();
            }
            else {
                uuid = fetchId;
            }

            Update.Where update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByFeature()).
                    with()
                    .and(QueryBuilder.set(MFmeetingTitle, meetingByFeature.getMeeting_title()))
//                    .and(QueryBuilder.set(id, meetingByFeature.getMeeting_id()))
                    .and(QueryBuilder.set(MFmodifyTime, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(MFdurationTime, meetingByFeature.getDuration_time()))
                    .and(QueryBuilder.set(MFstartDate, meetingByFeature.getStart_date()))
                    .and(QueryBuilder.set(MFmeetingDesc, meetingByFeature.getMeeting_desc()))
                    .and(QueryBuilder.set(MFfeatureName, meetingByFeature.getFeature_name()))
//                    .and(QueryBuilder.set(MFfeatureId, meetingByFeature.getfeatureId()))
//                    .and(QueryBuilder.set(MFcreateMember, meetingByFeature.getCreate_member()))
                    .and(QueryBuilder.set(MFprojectId, meetingByFeature.getProject_id()))
                    .and(QueryBuilder.set(MFstartMeeting, meetingByFeature.getStart_meeting()))
                    .where(QueryBuilder.eq(id, uuid)).and(eq(MFfeatureId , meetingByFeature.getfeatureId()));


            Update.Where meetingUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                    .with()
//                    .and(QueryBuilder.set(id, uuid))
                    .and(QueryBuilder.set(title, meetingByFeature.getMeeting_title()))
                    .and(QueryBuilder.set(desc, meetingByFeature.getMeeting_desc()))
                    .and(QueryBuilder.set(startDate, meetingByFeature.getStart_date()))
                    .and(QueryBuilder.set(durationTime, meetingByFeature.getDuration_time()))
                    .and(QueryBuilder.set(modifyTime, meetingByFeature.getModifyTime()))
//                .value(createMember , meetingByRelease.getCreate_member())
                    .and(QueryBuilder.set(startMeeting, meetingByFeature.getStart_meeting()))
                    .where(QueryBuilder.eq(id, uuid));

            batch.add(update);
            batch.add(meetingUpdate);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }

        return resultModel;
    }

    public ResultModel updateMeetingByRelease(MeetingByRelease meetingByRelease, String lang){

        batch = QueryBuilder.batch();
        try {
            UUID fetchId = null ,uuid = null ;
            Select.Where selectallow = QueryBuilder.select(MRmeetingId).
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByRelease()).allowFiltering()
                    .where(QueryBuilder.eq(MRmeetingTitle,meetingByRelease.getMeetingTitle()))
                    .and(QueryBuilder.eq(MRreleaseId,meetingByRelease.getReleaseId()));

            resultset = configSession.getSession().execute(selectallow);

            for (Row row : resultset) {
                fetchId = row.getUUID(MRmeetingId);
//                featureId = row.getUUID(MFfeatureId);
            }
            if(fetchId == null) {
                uuid = UUID.randomUUID();
//                featureId = meetingByFeature.getfeatureId();
            }
            else {
                uuid = fetchId;
            }

            Update.Where update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByRelease()).
                    with()
                    .and(QueryBuilder.set(MRmeetingTitle, meetingByRelease.getMeetingTitle()))
//                    .and(QueryBuilder.set(id, meetingByFeature.getMeeting_id()))
                    .and(QueryBuilder.set(MRmodifyTime, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(MRdurationTime, meetingByRelease.getDurationTime()))
                    .and(QueryBuilder.set(MRstartDate, meetingByRelease.getStartDate()))
                    .and(QueryBuilder.set(MRmeetingDesc, meetingByRelease.getMeetingDesc()))
                    .and(QueryBuilder.set(MRreleaseName, meetingByRelease.getReleaseName()))
//                    .and(QueryBuilder.set(MFfeatureId, meetingByFeature.getfeatureId()))
//                    .and(QueryBuilder.set(MFcreateMember, meetingByFeature.getCreate_member()))
                    .and(QueryBuilder.set(MRprojectId, meetingByRelease.getProjectId()))
                    .and(QueryBuilder.set(MRstartMeeting, meetingByRelease.getStartMeeting()))
                    .where(QueryBuilder.eq(MRmeetingId, uuid)).and(eq(MRreleaseId , meetingByRelease.getReleaseId()));


            Update.Where meetingUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                .with()
//                    .and(QueryBuilder.set(id, uuid))
                .and(QueryBuilder.set(title, meetingByRelease.getMeetingTitle()))
                .and(QueryBuilder.set(desc, meetingByRelease.getMeetingDesc()))
                .and(QueryBuilder.set(startDate, meetingByRelease.getStartDate()))
                .and(QueryBuilder.set(durationTime, meetingByRelease.getDurationTime()))
                .and(QueryBuilder.set(modifyTime, meetingByRelease.getModifyTime()))
//                .value(createMember , meetingByRelease.getCreate_member())
                .and(QueryBuilder.set(startMeeting, meetingByRelease.getStartMeeting()))
                .where(QueryBuilder.eq(id, uuid));

            batch.add(update);
            batch.add(meetingUpdate);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }

            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());

        }catch (Exception e){
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }

        return resultModel;
    }

    public ResultModel updateMeetingByProject(MeetingByProject meetingByProject, String lang){
        try {
            batch = QueryBuilder.batch();

            UUID fetchId = null ,uuid = null ;
            Select.Where selectallow = QueryBuilder.select(id).
                    from(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByProject()).allowFiltering()
                    .where(QueryBuilder.eq(MPmeetingTitle,meetingByProject.getMeetingTitle()))
                    .and(QueryBuilder.eq(MPprojectId,meetingByProject.getProjectId()));

            resultset = configSession.getSession().execute(selectallow);

            for (Row row : resultset) {
                fetchId = row.getUUID(id);
//                featureId = row.getUUID(MFfeatureId);
            }
            if(fetchId == null) {
                uuid = UUID.randomUUID();
//                featureId = meetingByFeature.getfeatureId();
            }
            else {
                uuid = fetchId;
            }

            Update.Where update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeetingByProject()).
                    with()
                    .and(QueryBuilder.set(MPmeetingTitle, meetingByProject.getMeetingTitle()))
//                    .and(QueryBuilder.set(id, meetingByFeature.getMeeting_id()))
                    .and(QueryBuilder.set(MPmodifyTime, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(MPdurationTime, meetingByProject.getDurationTime()))
                    .and(QueryBuilder.set(MPstartDate, meetingByProject.getStartDate()))
                    .and(QueryBuilder.set(MPmeetingDesc, meetingByProject.getMeetingDesc()))
                    .and(QueryBuilder.set(MPprojectName, meetingByProject.getProjectName()))
//                    .and(QueryBuilder.set(MFfeatureId, meetingByFeature.getfeatureId()))
//                    .and(QueryBuilder.set(MFcreateMember, meetingByFeature.getCreate_member()))
//                    .and(QueryBuilder.set(MRprojectId, meetingByProject.getProjectId()))
                    .and(QueryBuilder.set(MPstartMeeting, meetingByProject.getStartMeeting()))
                    .where(QueryBuilder.eq(id, uuid)).and(eq(MPprojectId , meetingByProject.getProjectId()));

            Update.Where meetingUpdate = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMeeting())
                    .with()
//                    .and(QueryBuilder.set(id, uuid))
                    .and(QueryBuilder.set(title, meetingByProject.getMeetingTitle()))
                    .and(QueryBuilder.set(desc, meetingByProject.getMeetingDesc()))
                    .and(QueryBuilder.set(startDate, meetingByProject.getStartDate()))
                    .and(QueryBuilder.set(durationTime, meetingByProject.getDurationTime()))
                    .and(QueryBuilder.set(modifyTime, meetingByProject.getModifyTime()))
//                .value(createMember , meetingByRelease.getCreate_member())
                    .and(QueryBuilder.set(startMeeting, meetingByProject.getStartMeeting()))
                    .where(QueryBuilder.eq(id, uuid));

            batch.add(update);
            batch.add(meetingUpdate);
            ResultSet batchExecute = configSession.getSession().execute(batch);
            if (batchExecute.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());

            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }


        }catch (Exception e){
            resultModel.setError(1);
            resultModel.setResult(e.toString());
        }

        return resultModel;
    }
}
