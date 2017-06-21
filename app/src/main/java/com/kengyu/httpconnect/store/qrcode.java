package com.kengyu.httpconnect.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.Map;


public class qrcode extends AppCompatActivity {

    private TextView StoreName, QRtextv;
    private Button QRBtn;
    private int QRnumber = 0;
    private int CMnumber = 0;
    private String QRtext, CMtext;
    private HTTP http;
    private Thread Httpconnectthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        http = new HTTP();
        StoreName = (TextView) findViewById(R.id.textView2);
        QRBtn = (Button) findViewById(R.id.button2);
        QRtextv = (TextView) findViewById(R.id.textView5);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String storename = bundle.getString("storeName");
        StoreName.setText(storename);
        QRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QRcontent = "This is " + storename;

                int QRcodeWidth = 200;
                int QRcodeHeight = 200;

                QRnumber ++;
                if (QRnumber < 10) {
                    QRtext = "00" + QRnumber;
                    QRtextv.setText(QRtext);
                    QRcontent = QRcontent + "\nNumber is: " + QRnumber;
                } else if (QRnumber >= 10 && QRnumber < 100) {
                    QRtext = "0" + QRnumber;
                    QRtextv.setText(QRtext);
                    QRcontent = QRcontent + "\nNumber is: " + QRnumber;
                } else {
                    QRtext = ""+QRnumber;
                    QRtextv.setText(QRtext);
                    QRcontent = QRcontent + "\nNumber is: " + QRnumber;
                }
                //QRcode
                Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

                    // 建立 QR code 的資料矩陣
                    BitMatrix result = writer.encode(QRcontent, BarcodeFormat.QR_CODE, QRcodeWidth, QRcodeHeight, hints);
                    // ZXing 還可以生成其他形式條碼，如：BarcodeFormat.CODE_39、BarcodeFormat.CODE_93、BarcodeFormat.CODE_128、BarcodeFormat.EAN_8、BarcodeFormat.EAN_13...

                    //建立點陣圖
                    Bitmap bitmap = Bitmap.createBitmap(QRcodeWidth, QRcodeHeight, Bitmap.Config.ARGB_8888);
                    // 將 QR code 資料矩陣繪製到點陣圖上
                    for (int y = 0; y<QRcodeHeight; y++)
                    {
                        for (int x = 0;x<QRcodeWidth; x++)
                        {
                            bitmap.setPixel(x, y, result.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }

                    ImageView imgView = (ImageView) findViewById(R.id.imageView);
                    // 設定為 QR code 影像
                    imgView.setImageBitmap(bitmap);
                } catch (WriterException e)
                {
                    Httpconnectthread = new HTTP.ConnectThread("sss");
                }

            }
        });
    }
}
