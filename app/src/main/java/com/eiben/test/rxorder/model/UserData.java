package com.eiben.test.rxorder.model;

import android.view.View;

import com.eiben.test.rxorder.loader.base.BaseData;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class UserData extends BaseData implements Serializable {
    private String username = "eiben";
    private int age = 18;

    public UserData(String url, View v) {
        super(url, v);
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
}
