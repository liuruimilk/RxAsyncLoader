package com.eiben.test.rxorder.model;

import android.view.View;

import com.eiben.asyncloader.loader.base.BaseData;

/**
 * Created by liumingrui on 16/9/24.
 */

public class PriceData extends BaseData {
    private int type = 1;

    public PriceData(String url, View v) {
        super(url, v);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
