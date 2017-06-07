package com.kengyu.httpconnect.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginAvtivity extends AppCompatActivity {
    private Button Button1;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_avtivity);
        Button1 = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeName;
                storeName = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("storeName",storeName);
                Intent intent = new Intent( LoginAvtivity.this, QRcode.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
