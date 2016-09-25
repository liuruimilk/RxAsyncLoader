package com.eiben.test.rxorder.loader.base;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by liumingrui on 16/9/23.
 */

public class ViewsHolder {
    public Map<String, WeakReference<View>> cache = Collections.synchronizedMap(new WeakHashMap<>());
}
