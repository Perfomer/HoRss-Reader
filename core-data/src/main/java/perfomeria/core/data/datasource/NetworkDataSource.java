package perfomeria.core.data.datasource;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import perfomeria.core.data.internal.entity.Rss;
import perfomeria.core.data.internal.network.NetworkManager;
import perfomeria.core.data.internal.network.RssApi;

public class NetworkDataSource {

    private static NetworkDataSource INSTANCE;

    private final RssApi api;


    NetworkDataSource(final RssApi api) {
        this.api = api;
    }

    @NonNull
    public static NetworkDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkDataSource(NetworkManager.getInstance());
        }

        return INSTANCE;
    }

    @NonNull
    public Single<Rss> loadRss(final String url) {
        return api.getRssData(url);
    }

}