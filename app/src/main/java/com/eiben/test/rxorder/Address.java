package com.eiben.test.rxorder;


import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Address extends Data implements Serializable {


    public Address(String url) {
        super(url);
    }

    @Override
    public void analysisResult(String result) {
        setData("address");
    }

    @Override
    public String toString() {
        return "Address{" +
                "data='" + data + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
