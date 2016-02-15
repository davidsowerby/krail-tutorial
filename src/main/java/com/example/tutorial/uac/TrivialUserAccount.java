package com.example.tutorial.uac;

import java.util.Arrays;
import java.util.List;

public class TrivialUserAccount {

    private String password;
    private List<String> permissions;
    private String userId;

    public TrivialUserAccount(String userId, String password, String... permissions) {
        this.userId = userId;
        this.password = password;
        this.permissions = Arrays.asList(permissions);
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}