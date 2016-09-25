package com.eiben.test.rxorder.model;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Price extends Data implements Serializable {
    private int type;

    public Price(String url, View v) {
        super(url, v);
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
        setType(1);
    }

    @Override
    public void doSomeThing(View v) {
        TextView tv = (TextView) v;
        tv.setText(data + " price");
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
