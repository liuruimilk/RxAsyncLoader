package com.eiben.test.rxorder.model;

import android.view.View;
import android.widget.TextView;

import com.eiben.test.rxorder.loader.base.BaseParam;

/**
 * Created by liumingrui on 16/9/25.
 */

public class PriceParam extends BaseParam {


    public PriceParam(String url, View v) {
        super(new PriceData(url, v));
    }

    @Override
    public void analysisResult(String result) {
        setData(result);
    }

    @Override
    public void doSomeThing(View v) {
        PriceData source = (PriceData) getDataSource();
        TextView tv = (TextView) v;
        tv.setText(getData() + " price " + source.getType());
    }
}
