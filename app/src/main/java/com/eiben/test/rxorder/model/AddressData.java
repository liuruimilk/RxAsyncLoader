package com.eiben.test.rxorder.model;


import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class AddressData extends BaseData implements Serializable {

    public AddressData(String url, View v) {
        super(url, v);
    }
}
