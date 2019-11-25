package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.DataType;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.UUID;

@UserDefinedType("RoleByProjectType")
public class RoleByProjectType {

    @CassandraType(type = DataType.Name.UUID)
    private UUID roleId;

    @CassandraType(type = DataType.Name.UUID)
    private UUID projectId;

    @CassandraType(type = DataType.Name.UUID)
    private UUID memberID;

    private MemberType member;
    private String projectName;
    private boolean isStakeholder;
    private String roleName;
    private int permissionKey;
    private String permissionName;
    private String lang;


    public UUID getMemberID() {
        return memberID;
    }

    public void setMemberID(UUID memberID) {
        this.memberID = memberID;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public MemberType getMember() {
        return member;
    }

    public void setMember(MemberType member) {
        this.member = member;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isStackholder() {
        return isStakeholder;
    }

    public void setStackholder(boolean stakeholder) {
        isStakeholder = stakeholder;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(int permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
