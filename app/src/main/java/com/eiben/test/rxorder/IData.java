package com.eiben.test.rxorder;

/**
 * Created by liumingrui on 16/9/23.
 */

public interface IData {

    String getUrl();

    String getData();

    void analysisResult(String result);

    void setErrorCode(int code);

    int getErrorCode();
}
