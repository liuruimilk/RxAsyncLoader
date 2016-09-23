package com.eiben.test.rxorder;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Address implements IData, Serializable {

    private String data;
    private String url;

    public Address(String url) {
        this.url = url;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Address{" +
                "data='" + data + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
