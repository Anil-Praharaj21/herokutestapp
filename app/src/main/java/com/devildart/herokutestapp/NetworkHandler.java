package com.devildart.herokutestapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkHandler extends AsyncTask<String, String, String> {

    private Response response;

    public NetworkHandler(Response response) {
        this.response = response;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://mp-android-challenge.herokuapp.com/data");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(150000);
            con.setConnectTimeout(150000);
            con.setRequestMethod("GET");
            con.connect();

            int code = con.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null && !s.equals("")) {
            response.onSucces(s);
        }
        super.onPostExecute(s);
    }

    interface Response {
        void onSucces(String response);
    }
}
