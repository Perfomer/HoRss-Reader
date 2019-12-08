package perfomeria.core.data.internal.network;

import io.reactivex.Single;
import perfomeria.core.data.internal.entity.Rss;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RssApi {

    @GET
    Single<Rss> getRssData(@Url String url);

}
