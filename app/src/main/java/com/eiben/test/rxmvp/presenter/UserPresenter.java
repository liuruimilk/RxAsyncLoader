package com.eiben.test.rxmvp.presenter;

import com.eiben.test.rxmvp.contact.IUserContact;
import com.eiben.test.rxmvp.model.source.IUserDataSource;
import com.eiben.test.rxmvp.model.User;
import com.eiben.test.rxmvp.model.source.UserDataSource;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/18.
 */
public class UserPresenter implements IUserContact.IPresenter {

    private IUserContact.IView view;
    private IUserDataSource dataSource;

    public UserPresenter(IUserContact.IView view) {
        this.view = view;
        dataSource = new UserDataSource();
    }

    @Override
    public void loadUser() {
        Observable.just(dataSource.getUser())
                .map(new Func1<User, String>() {
                    @Override
                    public String call(User user) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("name:")
                                .append(user.getName())
                                .append(" email:")
                                .append(user.getEmail());
                        return sb.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        view.showUserInfo(s);
                    }
                });
    }
}
