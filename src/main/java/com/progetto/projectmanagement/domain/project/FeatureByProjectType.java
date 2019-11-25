//package ir.dabacenter.projectmanagement.domain.project;
//
//import com.datastax.driver.core.DataType;
//import ir.dabacenter.projectmanagement.domain.member.MemberType;
//import uuidClass;
//import org.springframework.data.cassandra.mapping.CassandraType;
//import org.springframework.data.cassandra.mapping.UserDefinedType;
//
//import java.util.List;
//import java.util.UUID;
//
//@UserDefinedType("FeatureByProjectType")
//public class FeatureByProjectType {
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID id;
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID projectId;
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID creatorID;
//
//    private String title;
//    private String featureDesc;
//    private List<uuidClass> meetingId;
//    private boolean hasSubfeature;
//    private MemberType creator;
//    private boolean undefinedSub;
//    private String lang;
//
//
//    public UUID getCreatorID() {
//        return creatorID;
//    }
//
//    public void setCreatorID(UUID creatorID) {
//        this.creatorID = creatorID;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public UUID getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(UUID projectId) {
//        this.projectId = projectId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getFeatureDesc() {
//        return featureDesc;
//    }
//
//    public void setFeatureDesc(String featureDesc) {
//        this.featureDesc = featureDesc;
//    }
//
//    public List<uuidClass> getMeetingId() {return meetingId; }
//
//    public void setMeetingId(List<uuidClass> meetingId) {this.meetingId = meetingId;}
//
//    public boolean isHasSubfeature() {
//        return hasSubfeature;
//    }
//
//    public void setHasSubfeature(boolean hasSubfeature) {
//        this.hasSubfeature = hasSubfeature;
//    }
//
//    public MemberType getCreator() {
//        return creator;
//    }
//
//    public void setCreator(MemberType creator) {
//        this.creator = creator;
//    }
//
//    public boolean isUndefinedSub() {
//        return undefinedSub;
//    }
//
//    public void setUndefinedSub(boolean undefinedSub) {
//        this.undefinedSub = undefinedSub;
//    }
//
//    public String getLang() {
//        return lang;
//    }
//
//    public void setLang(String lang) {
//        this.lang = lang;
//    }
//}