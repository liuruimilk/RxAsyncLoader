package com.eiben.test.rxorder.load.address;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eiben.asyncloader.base.BaseTask;
import com.eiben.asyncloader.base.IData;

/**
 * Created by liumingrui on 16/9/27.
 */

public class AddressTask extends BaseTask {

    public AddressTask(IData data) {
        super(data);
    }

    @Override
    public String fromNet() {
        return "address";
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
    }

    @Override
    public void doSomeThingUI(View v) {
        TextView tv = (TextView) v;
        tv.setText(getData());
    }

    @Override
    public void doSomeThingIO() {

    }

    @Override
    public boolean checkDataValid() {
        return !TextUtils.isEmpty(getData());
    }
}
