package com.example.security.Model;


public class mapRoleToUser{
    private String userName;
    private String roleName;

    public mapRoleToUser(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
