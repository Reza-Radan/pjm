package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import ir.dabacenter.projectmanagement.domain.uuidClass;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.List;
import java.util.UUID;

@UserDefinedType("ReleaseByProjectType")
public class ReleaseByProjectType {

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.UUID)
    private UUID projectId;

    private String title;
    private String releaseDesc;

    private List<MissionNameID> missionId;

    private List<uuidClass> meetingId;

    private List<uuidClass> issueId;

    private MemberType creator;
    private String lang;

    @CassandraType(type = DataType.Name.UUID)
    private UUID creatorID;

    public UUID getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(UUID creatorID) {
        this.creatorID = creatorID;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDesc() {
        return releaseDesc;
    }

    public void setReleaseDesc(String releaseDesc) {
        this.releaseDesc = releaseDesc;
    }

    public List<MissionNameID> getMissionId() {
        return missionId;
    }

    public void setMissionId(List<MissionNameID> missionId) {
        this.missionId = missionId;
    }

    public List<uuidClass> getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(List<uuidClass> meetingId) {
        this.meetingId = meetingId;
    }

    public List<uuidClass> getIssueId() {
        return issueId;
    }

    public void setIssueId(List<uuidClass> issueId) {
        this.issueId = issueId;
    }

    public MemberType getCreator() {
        return creator;
    }

    public void setCreator(MemberType creator) {
        this.creator = creator;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
