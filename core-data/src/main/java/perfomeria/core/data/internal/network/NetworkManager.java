package perfomeria.core.data.internal.network;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkManager {

    private static RssApi rssApi;

    @NonNull
    public static RssApi getInstance() {
        if (rssApi != null) return rssApi;

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build();

        rssApi = new Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://www")
            .build()
            .create(RssApi.class);

        return rssApi;
    }

}
