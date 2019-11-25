package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("project_all_details")
public class ProjectAllDetails {


	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;

	private MemberType creator;
	private Long createDate;
	private String projectDescription;
	private Long endDate;

	@CassandraType(type = DataType.Name.UUID)
	private UUID creatorID;

	private String name;
	private int percentage;
	private Long startDate;
	private boolean status;

	@NotNull
	private Long modifyTime;

	private List<FeatureByProjectType> feature;
	private List<MissionByProjectType> mission;
	private List<SubFeatureByProjectType> subfeature;
	private List<ReleaseByProjectType> release;
	private List<VisionByProjectType> vision;
	private List<RoleByProjectType> members;
	private String lang;


	public UUID getCreatorID() {return creatorID;}

	public void setCreatorID(UUID creatorID) {this.creatorID = creatorID;}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public MemberType getCreator() {
		return this.creator;
	}

	/**
	 * 
	 * @param creator
	 */
	public void setCreator(MemberType creator) {
		this.creator = creator;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Long getEnd_date() {
		return this.endDate;
	}

	/**
	 * 
	 * @param end_date
	 */
	public void setEnd_date(Long end_date) {
		this.endDate = end_date;
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

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getPercentage() {
		return this.percentage;
	}

	/**
	 * 
	 * @param percentage
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Long getStart_date() {
		return this.startDate;
	}

	/**
	 * 
	 * @param start_date
	 */
	public void setStart_date(Long start_date) {
		this.startDate = start_date;
	}

	public boolean getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<FeatureByProjectType> getFeature() {
		return this.feature;
	}

	/**
	 * 
	 * @param feature
	 */
	public void setFeature(List<FeatureByProjectType> feature) {
		this.feature = feature;
	}

	public List<MissionByProjectType> getMission() {
		return this.mission;
	}

	/**
	 * 
	 * @param mission
	 */
	public void setMission(List<MissionByProjectType> mission) {
		this.mission = mission;
	}

	public List<SubFeatureByProjectType> getSubfeature() {
		return this.subfeature;
	}

	/**
	 * 
	 * @param subfeature
	 */
	public void setSubfeature(List<SubFeatureByProjectType> subfeature) {
		this.subfeature = subfeature;
	}

	public List<ReleaseByProjectType> getRelease() {
		return this.release;
	}

	/**
	 * 
	 * @param release
	 */
	public void setRelease(List<ReleaseByProjectType> release) {
		this.release = release;
	}

	public List<VisionByProjectType> getVision() {
		return this.vision;
	}

	/**
	 * 
	 * @param vision
	 */
	public void setVision(List<VisionByProjectType> vision) {
		this.vision = vision;
	}

	public List<RoleByProjectType> getMembers() {
		return this.members;
	}

	/**
	 * 
	 * @param members
	 */
	public void setMembers(List<RoleByProjectType> members) {
		this.members = members;
	}

}