package ir.dabacenter.projectmanagement.domain.role;

import com.datastax.driver.core.DataType;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("role")
public class Role  {

	@IdColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;
	private String nameEn;
	private String nameFa;
	private String permissionName;
	private String permissionUuid;
	private Long modifyTime;
	private String lang;


	public String getLang() {return lang;}

	public void setLang(String lang) {this.lang = lang;}

	public String getPermissionUuid() {return permissionUuid;	}

	public void setPermissionUuid(String permissionUuid) {this.permissionUuid = permissionUuid;}

	public String getPermissionName() {	return permissionName;}

	public void setPermissionName(String permissionName) {this.permissionName = permissionName;	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {this.modifyTime = modifyTime;}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getnameEn() {	return this.nameEn;}

	public void setnameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getnameFa() {
		return this.nameFa;
	}

	public void setnameFa(String nameFa) {
		this.nameFa = nameFa;
	}

}