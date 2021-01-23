import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801
 */
public class OKHttpClient {

    private static OkHttpClient client = new OkHttpClient();

    public static void testGet() throws IOException {
        String url = "http://localhost:8801/";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }

    public static void main(String[] args) {
        try {
            testGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
