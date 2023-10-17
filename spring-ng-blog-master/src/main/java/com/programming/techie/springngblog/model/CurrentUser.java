package com.programming.techie.springngblog.model;

import java.util.List;

public class CurrentUser {
    private Long id;
    private String username;
    private List<String> roles;

    public CurrentUser(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
