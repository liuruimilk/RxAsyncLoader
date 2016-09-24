package com.eiben.test.rxorder;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class User extends Data implements Serializable {
    private String username;
    private int age;

    public User(String url) {
        super(url);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void analysisResult(String result) {
        setData("liumingrui@yongche.com");
        setUsername("liumingrui");
        setAge(18);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", data=" + data +
                '}';
    }
}
