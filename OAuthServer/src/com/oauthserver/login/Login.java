package com.oauthserver.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

public class Login implements LoginInterface {

    public void CustomerLogin(String customerName, String customerPassword, String number) {
        final String client_id = "5a6cec02e1f1abc65ebee3789fd8b6d9";
        final String client_secret = "d43a1249361e10e6";
        String accessToken = null;
        String request = "{\"mobileNumber\":\"" + number + "\"}";

        try {
            //Construct the request
            String data = URLEncoder.encode("grant_type", "UTF-8") + "=" + URLEncoder.encode("password", "UTF-8");
            data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(customerName, "UTF-8") +
                    "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(customerPassword, "UTF-8") +
                    "&" + URLEncoder.encode("client_id", "UTF-8") + "=" + URLEncoder.encode(client_id, "UTF-8") +
                    "&" + URLEncoder.encode("client_secret", "UTF-8") + "=" + URLEncoder.encode(client_secret, "UTF-8");

            //Send the request to the MB Server
            URL url = new URL("http://api-int-stage.dev.moneybookers.net/v0.8/oauth20");
            URLConnection connection = (URLConnection) url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(data);
            out.flush();

            //Getting the response from the MB server
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String token = "";
            String line = null;
            while ((line = rd.readLine()) != null) {
                token += line;
            }

            //take the access token from the server response 
            JSONObject myJsonObj = new JSONObject(token);
            accessToken = myJsonObj.getString("access_token");

            //Send the built request to the MB Server with header Authorization Bearer + accessToken
            url = new URL("http://api-int-stage.dev.moneybookers.net/v0.8/me/mobile-number");
            connection = url.openConnection();
            connection.setDoOutput(true);
            connection.addRequestProperty("Authorization", "Bearer " + accessToken);
            connection.addRequestProperty("Content-Type", "application/json");

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(request);
            out.flush();
            out.close();

            //Getting the response from the MB server at the same way 
            BufferedReader rd1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = rd1.readLine()) != null) {
                System.out.println(line);
            }
            rd1.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
