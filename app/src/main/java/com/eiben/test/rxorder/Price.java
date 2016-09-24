package com.eiben.test.rxorder;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Price extends Data implements Serializable {
    private int type;

    public Price(String url, int type) {
        super(url);
        this.type = type;
    }

    @Override
    public void analysisResult(String result) {
        setData("price");
        setType(1);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Price{" +
                "type=" + type +
                ", data='" + data + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
