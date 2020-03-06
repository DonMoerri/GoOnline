package com.example.goonlinejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import android.util.Base64;

public final class APIHandler {
    public static String baseURL = "http://localhost:3000/api/";
    public static String deviceURL = "device/";
    public static String checkLoginURL = "check_login/";
    public static String locationsURL = "location/";
    public static String locationDevicesURL = "location/<locationid>/room"; //<locationid> mit tatsächlicher id ersetzen
    public static String postBody = "{ \"roomid\" : <roomid>, type\": <devicetype>}";
    public static HttpURLConnection con;
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

    public static void addDevice(String roomID, String deviceType) throws ProtocolException {
        postBody = postBody.replace("<roomid>", roomID);
        postBody = postBody.replace("<devicetype>", deviceType);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = postBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch(IOException e) {
            e.printStackTrace();
        }

        readResponse(con);
    }
}
