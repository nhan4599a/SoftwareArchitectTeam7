package vlu.architect.team7.futa.caller.Helper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RestApiCaller {
    private final HttpURLConnection connection;

    public RestApiCaller(String url) throws IOException {
        connection = (HttpURLConnection) new URL(url).openConnection();
    }

    public String get(HashMap<String, String> parameters) throws Exception {
        connection.setRequestMethod("GET");
        if (parameters != null && !parameters.isEmpty()) {
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(getParametersString(parameters));
            outputStream.flush();
            outputStream.close();
        }
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inputReader.readLine()) != null) {
            content.append(inputLine);
        }
        inputReader.close();
        return content.toString();
    }

    private String getParametersString(HashMap<String, String> parameters) {
        try {
            StringBuilder output = new StringBuilder();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                output.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                output.append("=");
                output.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                output.append("&");
            }
            return output.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
