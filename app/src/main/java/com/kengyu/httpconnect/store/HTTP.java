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
    /*Insert Thread variable*/
    private static String connectIP = "http://61.228.19.108/post.php";
    private static String Httpparam;

    /*Update Thread variable*/
    private static String updateIP = "http://61.228.19.108/post.php";
    private static String Updateparam;

    /*Update Thread variable*/
    private static String updatenIP = "http://61.228.19.108/post.php";
    private static String nUpdateparam;
    /*Insert Thread*/
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

    /*Update_all Thread*/
    public static class UpdateThread extends Thread {
        URLConnection uconn;
        public UpdateThread(String updateparam) {
            try {
                Updateparam = updateparam;
                URL url = new URL(updateIP);
                uconn = url.openConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {

                uconn.setDoInput(true);
                uconn.setDoOutput(true);

                uconn.connect();

                Reader in = new InputStreamReader(uconn.getInputStream());
                /*
                int data = 0;
                String get = "";
                while (data != -1) {
                    data = in.read();
                    get = get + (char)data;
                }
                Log.d("URL data is", get);
                */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /*Update_now Thread*/
    public static class nUpdateThread extends Thread {
        URLConnection unconn;
        public nUpdateThread(String updatenparam) {
            try {
                nUpdateparam = updatenparam;
                URL url = new URL(updatenIP);
                unconn = url.openConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {

                unconn.setDoInput(true);
                unconn.setDoOutput(true);

                unconn.connect();

                Reader in = new InputStreamReader(unconn.getInputStream());
                /*
                int data = 0;
                String get = "";
                while (data != -1) {
                    data = in.read();
                    get = get + (char)data;
                }
                Log.d("URL data is", get);
                */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
