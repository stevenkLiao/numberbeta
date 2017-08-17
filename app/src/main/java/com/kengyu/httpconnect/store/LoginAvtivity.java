package com.kengyu.httpconnect.store;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginAvtivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText storeText;
    int Fstclick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_avtivity);

        //enter store name
        loginBtn = (Button) findViewById(R.id.Btnlogin);
        storeText = (EditText) findViewById(R.id.EdtStorename);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String storeName, storeNameTrn;
                storeName = storeText.getText().toString();
                storeNameTrn = "Name=" + storeName;

                //Transpoprt the storeName to server
                Thread connthread = new HTTP.ConnectThread(storeNameTrn, handler);
                connthread.start();

                Bundle bundle = new Bundle();
                bundle.putString("storeName",storeName);
                Intent intent = new Intent( LoginAvtivity.this, qrcode.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        //Press keycode DONE
        storeText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    String storeName, storeNameTrn;
                    storeName = storeText.getText().toString();
                    storeNameTrn = "Name=" + storeName;

                    Thread connthread = new HTTP.ConnectThread(storeNameTrn, handler);
                    connthread.start();

                    Bundle bundle = new Bundle();
                    bundle.putString("storeName",storeName);
                    Intent intent = new Intent( LoginAvtivity.this, qrcode.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                return false;
            }
        });


        storeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Fstclick == 0) {
                    storeText.setText("");
                    Fstclick = 1;
                }
            }
        });
    }

    private final Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    switch (msg.arg1) {
                        case 1:
                            Toast.makeText(LoginAvtivity.this, "伺服器維修中，請稍後再試", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(LoginAvtivity.this, "登入成功，啟用號碼牌功能", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    };
}
