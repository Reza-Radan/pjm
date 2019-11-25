package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table("vision_by_project")
public class VisionByProject {


	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID id;

	@CassandraType(type = DataType.Name.UUID)
	private UUID local_id;

	@IdColumn(name = "projectId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID projectId;

	private String title;
	private String visionDesc;

	@NotNull
	private UUID modifyTime;

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


	public String getVisionDesc() {
		return visionDesc;
	}

	public void setVisionDesc(String visionDesc) {
		this.visionDesc = visionDesc;
	}

	public List<MemberType> getMembers() {
		return this.members;
	}

	/**
	 * 
	 * @param members
	 */
	public void setMembers(List<MemberType> members) {
		this.members = members;
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