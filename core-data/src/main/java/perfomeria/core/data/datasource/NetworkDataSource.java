package perfomeria.core.data.datasource;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Single;
import perfomeria.core.data.internal.entity.Rss;
import perfomeria.core.data.internal.network.RssApi;

public class NetworkDataSource {

    private final RssApi api;


    @Inject
    public NetworkDataSource(final @NonNull RssApi api) {
        this.api = api;
    }


    /**
     * Loads RSS-feed from the [url]. Requires the Internet connection.
     *
     * @param url source link of the channel (RSS-feed)
     * @return [Rss]
     */
    @NonNull
    public Single<Rss> loadRss(final @NonNull String url) {
        return api.getRssData(url);
    }

}