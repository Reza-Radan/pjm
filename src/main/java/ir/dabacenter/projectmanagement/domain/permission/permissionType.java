//
//package ir.dabacenter.projectmanagement.domain.permission;
//
//import com.datastax.driver.core.DataType;
//import org.springframework.data.cassandra.mapping.CassandraType;
//import org.springframework.data.cassandra.mapping.UserDefinedType;
//
//import java.util.UUID;
//
//@UserDefinedType("permissionType")
//public class permissionType {
//
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID id;
//
//    private int add;
//
//    private int delete;
//    private int edit;
//    private int changeStatus;
//    private int list;
//    private int viewPermission;
//
//    public permissionType() {}
//
//    public int getViewPermission() { return viewPermission; }
//
//    public void setViewPermission(int viewPermission) {  this.viewPermission = viewPermission;  }
//
//    public int getEdit() {return edit; }
//
//    public void setEdit(int edit) {this.edit = edit; }
//
//    public UUID getId() { return id; }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public int getAdd() {
//        return add;
//    }
//
//    public void setAdd(int add) {
//        this.add = add;
//    }
//
//    public int getDelete() {
//        return delete;
//    }
//
//    public void setDelete(int delete) {
//        this.delete = delete;
//    }
//
//    public int getChangeStatus() { return changeStatus; }
//
//    public void setChangeStatus(int changeStatus) {
//        this.changeStatus = changeStatus;
//    }
//
//    public int getList() {
//        return list;
//    }
//
//    public void setList(int list) {
//        this.list = list;
//    }
//
//}
