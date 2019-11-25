package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import ir.dabacenter.projectmanagement.domain.uuidClass;
import jnr.ffi.types.time_t;
import org.apache.cassandra.utils.UUIDGen;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("release_by_project")
public class ReleaseByProject {

//	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID local_id;

	@IdColumn(name = "projectId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	private String title;
	private String releaseDesc;


	private List<MissionNameID> missionId;

	private List<uuidClass> meetingId;

	private List<uuidClass> issueId;

	private MemberType creator;
	private String lang;

	@NotNull
//	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID modifyTime;

	@NotNull
	@IdColumn(name = "id",  type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID id;

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
		return this.creator;
	}

	/**
	 * 
	 * @param creator
	 */
	public void setCreator(MemberType creator) {
		this.creator = creator;
	}

	public UUID getLocal_id() {
		return local_id;
	}

	public void setLocal_id(UUID local_id) {
		this.local_id = local_id;
	}
}