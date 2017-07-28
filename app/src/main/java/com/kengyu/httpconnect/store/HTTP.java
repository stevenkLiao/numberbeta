package com.kengyu.httpconnect.store;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Keng-Yu on 2017/6/21.
 */

public class HTTP {
    private static String connectIP = "http://61.228.19.108/post.php";
    private static String Httpparam;

    public static class ConnectThread extends Thread {
        URLConnection conn;
        public ConnectThread(String httpparam) {
            try {
                Httpparam = httpparam;
                URL url = new URL(connectIP);
                conn = url.openConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {

                conn.setDoInput(true);
                conn.setDoOutput(true);

                DataOutputStream write = new DataOutputStream(conn.getOutputStream());
                //String Httpparam2 = URLEncoder.encode(Httpparam,"UTF-8");
                byte[] conbyte = Httpparam.getBytes();
                write.write(conbyte);
                write.flush();
                write.close();
                conn.connect();

                Reader in = new InputStreamReader(conn.getInputStream());
                int data = 0;
                String get = "";
                while (data != -1) {
                    data = in.read();
                    get = get + (char)data;
                }
                Log.d("URL data is", get);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
