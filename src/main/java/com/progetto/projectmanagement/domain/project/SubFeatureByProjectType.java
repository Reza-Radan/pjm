package com.progetto.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import com.progetto.projectmanagement.domain.uuidClass;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.List;
import java.util.UUID;

@UserDefinedType("SubFeatureByProjectType")
public class SubFeatureByProjectType {

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.UUID)
    private UUID projectId;

    private String title;
    private String subFeatureDesc;

    @CassandraType(type = DataType.Name.UUID)
    private UUID featureId;

    @CassandraType(type = DataType.Name.UUID)
    private UUID creatorID;

    private String featureTitle;
    private Long date;
    private String lang;


    public String getSubFeatureDesc() {
        return subFeatureDesc;
    }

    public void setSubFeatureDesc(String subFeatureDesc) {
        this.subFeatureDesc = subFeatureDesc;
    }

    @Frozen
    private List<uuidClass> members;

    private MemberType creator;

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

    public UUID getFeatureId() {
        return featureId;
    }

    public void setFeatureId(UUID featureId) {
        this.featureId = featureId;
    }

    public String getFeatureTitle() {
        return featureTitle;
    }

    public void setFeatureTitle(String featureTitle) {
        this.featureTitle = featureTitle;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<uuidClass> getMembers() {return members; }

    public void setMembers(List<uuidClass> members) {  this.members = members;   }

    public MemberType getCreator() {
        return creator;
    }

    public void setCreator(MemberType creator) {
        this.creator = creator;
    }
}
