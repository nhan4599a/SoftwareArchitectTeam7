package Network;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RestApiCaller {
    private final OkHttpClient client;

    private static RestApiCaller instance;

    private RestApiCaller() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static RestApiCaller getInstance() {
        if (instance == null)
            instance = new RestApiCaller();
        return instance;
    }

    public String get(String url, HashMap<String, String> parameters) throws IOException {
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, String> entry : parameters.entrySet())
                builder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder().url(builder.build()).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            return response.body().string();
        }
        return "";
    }

    public void post(String url, String rawContent) throws IOException {
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), rawContent);
        Request request = new Request.Builder().url(builder.build()).post(body).build();
        client.newCall(request).execute();
    }

}
