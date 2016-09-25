package com.eiben.test.rxorder.model;

import android.view.View;
import android.widget.TextView;

import com.eiben.asyncloader.loader.base.BaseParam;


/**
 * Created by liumingrui on 16/9/25.
 */

public class UserParam extends BaseParam {
    public UserParam(String url, View v) {
        super(new UserData(url, v));
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
    }

    @Override
    public void doSomeThing(View v) {
        UserData source = (UserData) getDataSource();

        TextView tv = (TextView) v;
        tv.setText(getData() + " user " + source.getUsername() + "-" + source.getAge());
    }
}
