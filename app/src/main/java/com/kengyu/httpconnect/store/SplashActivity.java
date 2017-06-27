package com.kengyu.httpconnect.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kengyu.httpconnect.store.R;

public class SplashActivity extends AppCompatActivity {

    //GO_MAIN int
    private static final int GO_MAIN_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Go LoginActivity after 1 second
        mHandler.sendEmptyMessageDelayed(GO_MAIN_ACTIVITY, 1000);
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case GO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, LoginAvtivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enteralpha, R.anim.exitalpha);
                    finish();
                    break;
                default:
                    break;
            }

        }
    };
}
