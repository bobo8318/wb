package cn.openui.model;

public class AdminRolePermission {
    int role_permission_id;
    int role_id;
    int permission_id;

    public int getRole_permission_id() {
        return role_permission_id;
    }

    public void setRole_permission_id(int role_permission_id) {
        this.role_permission_id = role_permission_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }
}
