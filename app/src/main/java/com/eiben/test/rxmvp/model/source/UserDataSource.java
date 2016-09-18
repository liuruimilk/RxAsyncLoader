package com.eiben.test.rxmvp.model.source;

import com.eiben.test.rxmvp.model.User;

/**
 * Created by liumingrui on 16/9/18.
 */
public class UserDataSource implements IUserDataSource {
    @Override
    public User getUser() {
        return new User("liumingrui","liumingrui@yongche.com");
    }
}
