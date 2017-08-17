package com.kengyu.httpconnect.store;

import android.os.Handler;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Keng-Yu on 2017/6/21.
 */

public class HTTP {
    /*Insert Thread variable*/
    private static String connectIP = "http://220.135.192.24/insert.php";
    private static String Httpparam;

    /*Update Thread variable*/
    private static String updateIP = "http://220.135.192.24/update_all.php?";
    private static String Updateparam;

    /*Update Thread variable*/
    private static String updatenIP = "http://220.135.192.24/update_now.php?";
    private static String nUpdateparam;


    /*Insert Thread*/
    public static class ConnectThread extends Thread {
        URLConnection conn;
        Handler handler;
        public ConnectThread(String httpparam, Handler mhandler) {
            handler = mhandler;

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
                byte[] conbyte = Httpparam.getBytes();
                write.write(conbyte);
                write.flush();
                write.close();
                conn.connect();

                int status = ((HttpURLConnection)conn).getResponseCode();
                switch (status) {
                    case java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT://504
                        handler.obtainMessage(0,1,0).sendToTarget();
                        break;
                    case java.net.HttpURLConnection.HTTP_FORBIDDEN://403
                        handler.obtainMessage(0,1,0).sendToTarget();
                        break;
                    case java.net.HttpURLConnection.HTTP_INTERNAL_ERROR://500
                        handler.obtainMessage(0,1,0).sendToTarget();
                        break;
                    case java.net.HttpURLConnection.HTTP_NOT_FOUND://404
                        handler.obtainMessage(0,1,0).sendToTarget();
                        break;
                    case java.net.HttpURLConnection.HTTP_OK:
                        handler.obtainMessage(0,2,0).sendToTarget();
                        break;

                }
                conn.getInputStream();

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
                String Updateparam = "";
                Updateparam = updateIP + updateparam;
                URL url = new URL(Updateparam);
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
                uconn.getInputStream();


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
                String nUpdateparam = "";
                nUpdateparam = updatenIP + updatenparam;
                URL url = new URL(nUpdateparam);
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

                unconn.getInputStream();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
