package com.example.goonlinejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.util.Base64;

public final class APIHandler {
    public static String baseURL = "http://localhost:3000/api/";
    public static String deviceURL = "device/";
    public static String checkLoginURL = "check_login/";
    public static String locationsURL = "location/";
    public static String locationDevicesURL = "location/<locationid>/room"; //<locationid> mit tatsächlicher id ersetzen
    public HttpURLConnection con;
    public static String authHash;

    public APIHandler(String email, String passwort) {
        try {
            byte[] nachricht = (email + ":" + passwort).getBytes("UTF-8");
            authHash = Base64.encodeToString(nachricht, Base64.DEFAULT);
            authHash = "Base " + authHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loginReq() throws MalformedURLException {
        URL url = new URL(baseURL + checkLoginURL);
        try {
            HttpURLConnection con = setDefaultHeaders(url);
            con.setRequestMethod("GET");
            String antwort = readResponse(con);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllDevices() throws MalformedURLException{
        URL url = new URL(baseURL + deviceURL);
        try {
            HttpURLConnection con = setDefaultHeaders(url);
            con.setRequestMethod("GET");
            String antwort = readResponse(con);

            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection setDefaultHeaders(URL conURL) throws IOException {
        HttpURLConnection ret = (HttpURLConnection) conURL.openConnection();
        ret.setRequestProperty("Authorization", authHash);
        return ret;
    }

    public static String readResponse(HttpURLConnection con) {
        StringBuffer inhalt = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while((line = br.readLine()) != null) {
                inhalt.append(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return inhalt.toString();
    }
}
