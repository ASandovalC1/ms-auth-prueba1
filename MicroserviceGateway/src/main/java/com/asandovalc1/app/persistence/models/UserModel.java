package com.asandovalc1.app.persistence.models;

import java.util.List;

public class UserModel {
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private String name;
    private String lastName;
    private String email;
    private List<RoleModel> roleModels;

    public List<RoleModel> getRoles() {
        return roleModels;
    }

    public void setRoles(List<RoleModel> roleModels) {
        this.roleModels = roleModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
