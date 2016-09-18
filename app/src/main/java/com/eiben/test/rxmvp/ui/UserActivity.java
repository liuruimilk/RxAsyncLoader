package com.eiben.test.rxmvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eiben.test.R;
import com.eiben.test.rxmvp.contact.IUserContact;
import com.eiben.test.rxmvp.presenter.UserPresenter;

public class UserActivity extends AppCompatActivity implements IUserContact.IView, IUserContact.IPresenter {

    private IUserContact.IPresenter presenter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        presenter = new UserPresenter(this);
        tv = (TextView) findViewById(R.id.tv);
        loadUser();
    }

    @Override
    public void showUserInfo(String s) {
        tv.setText(s);
    }

    @Override
    public void loadUser() {
        presenter.loadUser();
    }
}
