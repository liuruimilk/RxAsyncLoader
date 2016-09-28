package com.eiben.asyncloader.base;



/**
 * Created by liumingrui on 16/9/26.
 */

public class LoadUri {

    public String baseUri;
    public String uri;

    private LoadUri(String baseUri,String paramsStr) {
        this.baseUri = baseUri;
        uri = baseUri + paramsStr;
    }

    public static LoadUri createTextLoadUri(String baseUri, String paramsStr) {
        return new LoadUri(baseUri, paramsStr);
    }
}
