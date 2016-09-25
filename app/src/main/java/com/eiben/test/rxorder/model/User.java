package com.eiben.test.rxorder.model;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class User extends Data implements Serializable {
    private String username;
    private int age;

    public User(String url, View v) {
        super(url, v);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
        setUsername("liumingrui");
        setAge(18);

    }

    @Override
    public void doSomeThing(View v) {
        TextView tv = (TextView) v;
        tv.setText(data + " user");
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
