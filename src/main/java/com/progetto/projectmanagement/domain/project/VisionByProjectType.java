package com.progetto.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.List;
import java.util.UUID;

@UserDefinedType("VisionByProjectType")
public class VisionByProjectType {


    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.UUID)
    private UUID projectId;

    private String title;
    private String visionDesc;

    @Frozen
    private List<MemberType> members;

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

    public String getVisionDesc() {
        return visionDesc;
    }

    public void setVisionDesc(String visionDesc) {
        this.visionDesc = visionDesc;
    }

    public List<MemberType> getMembers() {
        return members;
    }

    public void setMembers(List<MemberType> members) {
        this.members = members;
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
