package com.eiben.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eiben.test.rxmvp.ui.UserActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("liumingrui","MainActivity oncreate");
    }

    public void button1(View view) {
        startActivity(new Intent(this, UserActivity.class));
    }
}
