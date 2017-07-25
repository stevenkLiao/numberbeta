package com.kengyu.httpconnect.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
                String storeName;
                storeName = storeText.getText().toString();
                storeName = "Name=" + storeName;

                Thread connthread = new HTTP.ConnectThread(storeName);
                connthread.start();

                Bundle bundle = new Bundle();
                bundle.putString("storeName",storeName);
                Intent intent = new Intent( LoginAvtivity.this, qrcode.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        storeText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String storeName;
                    storeName = storeText.getText().toString();
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
}
