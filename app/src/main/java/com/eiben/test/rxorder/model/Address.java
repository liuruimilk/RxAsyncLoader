package com.eiben.test.rxorder.model;


import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Address extends Data implements Serializable {


    public Address(String url,View v) {
        super(url,v);
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
    }

    @Override
    public void doSomeThing(View v) {
        TextView tv = (TextView) v;
        tv.setText(data + "address");
    }

    @Override
    public String toString() {
        return "Address{" +
                "data='" + data + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
