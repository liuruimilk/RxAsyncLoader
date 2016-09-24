package com.eiben.test.rxorder;

import android.view.View;

/**
 * Created by liumingrui on 16/9/23.
 */

public class AsyncLoader {

    private AsyncLoader() {
    }

    private static class Holder {
        static final AsyncLoader loader = new AsyncLoader();
    }

    public static AsyncLoader getInstance() {
        return Holder.loader;
    }

    public <T> void load(String url, View view, T t) {

    }
}
