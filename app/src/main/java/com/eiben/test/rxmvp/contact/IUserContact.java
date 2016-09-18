package com.eiben.test.rxmvp.contact;


/**
 * Created by liumingrui on 16/9/18.
 */
public interface IUserContact {

    interface IView {
        void showUserInfo(String s);
    }

    interface IPresenter {
        void loadUser();
    }
}
