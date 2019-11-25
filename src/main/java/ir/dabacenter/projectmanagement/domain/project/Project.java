package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.Frozen;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;
import java.util.UUID;



@Table("project")
public class Project {

	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;

	private String name;
	private String projectDescription;
	private boolean status;
	private int percentage;
	private Long startDate;
	private Long endDate;
	private MemberType creator;
	private String lang;

	@CassandraType(type = DataType.Name.TIMEUUID)
	private UUID modifyTime;

	@CassandraType(type = DataType.Name.TIMEUUID)
	@IdColumn(name = "time",  type = PrimaryKeyType.CLUSTERED ,ordering = Ordering.DESCENDING)
	private UUID time;


	@Frozen
	List<RoleByProjectType> members;

	@CassandraType(type = DataType.Name.UUID)
	private UUID creatorID;

	public List<RoleByProjectType> getMembers() {
		return members;
	}

	public void setMembers(List<RoleByProjectType> members) {
		this.members = members;
	}

	public UUID getCreatorID() {return creatorID;}

	public void setCreatorID(UUID creatorID) {this.creatorID = creatorID;	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public MemberType getCreator() {return creator;	}

	public void setCreator(MemberType creator) {this.creator = creator;}

	public UUID getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(UUID modifyTime) {
		this.modifyTime = modifyTime;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.projectDescription;
	}

	public void setDesc(String desc) {
		this.projectDescription = desc;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPercentage() {
		return this.percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Long getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

//	public MemberType getMember() {	return creator;	}
//
//	public void setMember(MemberType member) {this.creator = member;}

	public UUID getTime() {
		return time;
	}

	public void setTime(UUID time) {
		this.time = time;
	}
}