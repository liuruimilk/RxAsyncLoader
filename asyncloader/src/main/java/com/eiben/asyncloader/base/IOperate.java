package com.eiben.asyncloader.base;

import android.view.View;

/**
 * Created by liumingrui on 16/9/25.
 */

public interface IOperate {

    String fromNet();

    void analysisResult(String result);

    void doSomeThingUI(View v);

    void doSomeThingIO();

    boolean checkDataValid();
}
