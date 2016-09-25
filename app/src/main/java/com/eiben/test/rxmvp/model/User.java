package com.eiben.test.rxmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * git  * Created by liumingrui on 16/9/17.
 */
public class User {
    @SerializedName(value = "name", alternate = {"name", "username"})
    private String name = "";
    @SerializedName(value = "email", alternate = {"email", "email_address"})
    private String email = "";

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
