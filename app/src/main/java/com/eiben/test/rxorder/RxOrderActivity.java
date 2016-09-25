package com.eiben.test.rxorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eiben.test.R;
import com.eiben.test.rxorder.loader.AsyncLoader;
import com.eiben.test.rxorder.model.AddressParam;
import com.eiben.test.rxorder.model.PriceParam;
import com.eiben.test.rxorder.model.UserParam;


public class RxOrderActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    private TextView tv7;
    private TextView tv8;
    private TextView tv9;

    private TextView tv10;
    private TextView tv11;
    private TextView tv12;

    private TextView tv13;
    private TextView tv14;
    private TextView tv15;

    private TextView tv16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_order);

        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);

        tv4 = (TextView) findViewById(R.id.tv_4);
        tv5 = (TextView) findViewById(R.id.tv_5);
        tv6 = (TextView) findViewById(R.id.tv_6);

        tv7 = (TextView) findViewById(R.id.tv_7);
        tv8 = (TextView) findViewById(R.id.tv_8);
        tv9 = (TextView) findViewById(R.id.tv_9);

        tv10 = (TextView) findViewById(R.id.tv_10);
        tv11 = (TextView) findViewById(R.id.tv_11);
        tv12 = (TextView) findViewById(R.id.tv_12);

        tv13 = (TextView) findViewById(R.id.tv_13);
        tv14 = (TextView) findViewById(R.id.tv_14);
        tv15 = (TextView) findViewById(R.id.tv_15);

        tv16 = (TextView) findViewById(R.id.tv_16);
    }

    public void click(View v) {
        AsyncLoader.getInstance().load(new PriceParam("com.eiben1", tv1), new AddressParam("com.zhouxue2", tv2), new UserParam("com.liumingrui3", tv3));
        AsyncLoader.getInstance().load(new PriceParam("com.eiben4", tv4), new AddressParam("com.zhouxue5", tv5), new UserParam("com.liumingrui6", tv6));
        AsyncLoader.getInstance().load(new PriceParam("com.eiben7", tv7), new AddressParam("com.zhouxue8", tv8), new UserParam("com.liumingrui9", tv9));
        AsyncLoader.getInstance().load(new PriceParam("com.eiben10", tv10), new AddressParam("com.zhouxue11", tv11), new UserParam("com.liumingrui12", tv12));
        AsyncLoader.getInstance().load(new PriceParam("com.eiben13", tv13), new AddressParam("com.zhouxue14", tv14), new UserParam("com.liumingrui15", tv15));

        AsyncLoader.getInstance().load(new AddressParam("xihuan", tv16));
    }

    public void clearData(View v) {
        tv1.setText("本地数据1");
        tv2.setText("本地数据2");
        tv3.setText("本地数据3");

        tv4.setText("本地数据1");
        tv5.setText("本地数据2");
        tv6.setText("本地数据3");

        tv7.setText("本地数据1");
        tv8.setText("本地数据2");
        tv9.setText("本地数据3");

        tv10.setText("本地数据1");
        tv11.setText("本地数据2");
        tv12.setText("本地数据3");

        tv13.setText("本地数据1");
        tv14.setText("本地数据2");
        tv15.setText("本地数据3");

        tv16.setText("本地数据16");
    }

    public void clearCache(View v) {
        AsyncLoader.getInstance().clearCache();
    }
}