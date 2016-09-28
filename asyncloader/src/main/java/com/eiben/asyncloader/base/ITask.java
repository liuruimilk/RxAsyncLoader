package com.eiben.asyncloader.base;

/**
 * Created by liumingrui on 16/9/23.
 */

public interface ITask extends IData, IOperate {

    IData getDataSource();
}
