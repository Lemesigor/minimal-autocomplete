package org.autocomplete.infrastructure.controller;

import spark.utils.IOUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;
import java.util.Map;

public class HttpClientUtils {

    public TestResponse request(String method, String path, String requestBody) throws IOException {
        URL url = new URL("http://localhost:4567" + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);

        if (requestBody != null) {
            connection.getOutputStream().write(requestBody.getBytes());
        }

        connection.connect();
        String body = IOUtils.toString(connection.getInputStream());
        return new TestResponse(connection.getResponseCode(), body);
    }

    public class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String, Object> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }
}
