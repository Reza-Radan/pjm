package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import ir.dabacenter.projectmanagement.domain.uuidClass;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.List;
import java.util.UUID;

@UserDefinedType("MissionByProjectType")
public class MissionByProjectType{

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.UUID)
    private UUID projectId;

    private String title;

    private String missionDesc;

    private List<uuidClass> attachment;

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

    public UUID getId() {
        return id;
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

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public List<uuidClass> getAttachment() { return attachment; }

    public void setAttachment(List<uuidClass> attachment) {this.attachment = attachment; }

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
