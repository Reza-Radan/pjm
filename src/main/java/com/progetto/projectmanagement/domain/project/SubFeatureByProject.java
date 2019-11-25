package com.progetto.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import com.progetto.projectmanagement.domain.uuidClass;
import com.progetto.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("subfeature_by_project")
public class SubFeatureByProject {

//	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID local_id;

	@IdColumn(name = "projectId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	private String title;
	private String subFeatureDesc;

	@NotNull
	private UUID modifyTime;

	@NotNull
	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID id;

	@CassandraType(type = DataType.Name.UUID)
	private UUID featureId;

	private String featureTitle;
	private UUID date;
	private int status;

	@Frozen
	private List<uuidClass> members;

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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getprojectId() {
		return this.projectId;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param projectId
	 */
	public void setprojectId(UUID projectId) {
		this.projectId = projectId;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubFeatureDesc() {
		return subFeatureDesc;
	}

	public void setSubFeatureDesc(String subFeatureDesc) {
		this.subFeatureDesc = subFeatureDesc;
	}

	public UUID getFeature_id() {
		return this.featureId;
	}

	/**
	 * 
	 * @param feature_id
	 */
	public void setFeature_id(UUID feature_id) {
		this.featureId = feature_id;
	}

	public String getFeature_title() {
		return this.featureTitle;
	}

	/**
	 * 
	 * @param feature_title
	 */
	public void setFeature_title(String feature_title) {
		this.featureTitle = feature_title;
	}

	public UUID getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(UUID date) {
		this.date = date;
	}

	public List<uuidClass> getMembers() {return members;}

	public void setMembers(List<uuidClass> members) {this.members = members;}

	public MemberType getCreator() {
		return creator;
	}

	public void setCreator(MemberType creator) {
		this.creator = creator;
	}

	public UUID getLocal_id() {
		return local_id;
	}

	public void setLocal_id(UUID local_id) {
		this.local_id = local_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}