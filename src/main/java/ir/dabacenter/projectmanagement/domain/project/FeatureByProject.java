package ir.dabacenter.projectmanagement.domain.project;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Table
public class FeatureByProject {


	private UUID projectId;

	private UUID creatorID;

	private String title;
	private String featureDesc;

	private List<uuidClass> meetingId;

	private boolean hasSubfeature;
	private MemberType creator;
	private boolean undefinedSub;
	private String lang;

	@NotNull
	private UUID modifyTime;

	@NotNull
	@Id
	private UUID id;

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

	/**
	 * 
	 * @param projectId
	 */
	public void setprojectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeatureDesc() {
		return featureDesc;
	}

	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}


	public List<uuidClass> getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(List<uuidClass> meetingId) {
		this.meetingId = meetingId;
	}

	public boolean getHasSubfeature() {
		return this.hasSubfeature;
	}

	/**
	 * 
	 * @param hasSubfeature
	 */
	public void setHasSubfeature(boolean hasSubfeature) {
		this.hasSubfeature = hasSubfeature;
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

	public boolean getUndefinedSub() {
		return this.undefinedSub;
	}

	/**
	 * 
	 * @param undefinedSub
	 */
	public void setUndefinedSub(boolean undefinedSub) {
		this.undefinedSub = undefinedSub;
	}

	public UUID getLocal_id() {
		return local_id;
	}

	public void setLocal_id(UUID local_id) {
		this.local_id = local_id;
	}
}