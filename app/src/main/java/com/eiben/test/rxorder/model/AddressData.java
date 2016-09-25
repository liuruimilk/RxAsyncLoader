package com.eiben.test.rxorder.model;


import android.view.View;

import com.eiben.test.rxorder.loader.base.BaseData;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class AddressData extends BaseData implements Serializable {

    public AddressData(String url, View v) {
        super(url, v);
    }
}
