package edu.hm.schatter.shareit.businesslayer;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility for checking tokens.
 */
public final class TokenChecker {
    /**
     * Private useless constructor.
     */
    private TokenChecker() {
    }

    /**
     * Current URL of the auth server.
     */
    private static final String AUTH_SERVER_URL = "https://auth-teamxy.herokuapp.com/check/";

    /**
     * Checks whether a token is valid by asking the auth server.
     * @param token The token to be checked.
     * @return Whether the token is valid.
     */
    public static boolean isValid(String token) {
        String authServerResponse = sendToAuthServer(token);

        if (authServerResponse == null) {
            return false;
        } else {
            JSONObject obj = new JSONObject(authServerResponse);
            return obj.getBoolean("valid");
        }
    }

    /**
     * Sends a token to the auth server and returns the response.
     * @param token The token to be sent.
     * @return The response of the auth server.
     */
    private static String sendToAuthServer(String token) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(AUTH_SERVER_URL + token);
            connection = (HttpURLConnection) url.openConnection();

            StringBuilder response = new StringBuilder();

            try (InputStream is = connection.getInputStream();
                 BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
            }

            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
