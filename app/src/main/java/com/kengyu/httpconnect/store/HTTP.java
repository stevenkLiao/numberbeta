package com.kengyu.httpconnect.store;

import android.util.Log;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Keng-Yu on 2017/6/21.
 */

public class HTTP {


    public static class ConnectThread extends Thread {
        URLConnection conn;
        public ConnectThread(String connectIP) {
            try {

                URL url = new URL(connectIP);
                conn = url.openConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                conn.setDoInput(true);
                conn.connect();
                conn.getInputStream();
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
