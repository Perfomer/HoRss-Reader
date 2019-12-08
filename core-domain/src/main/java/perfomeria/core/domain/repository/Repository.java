package perfomeria.core.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;

public interface Repository {

    /**
     * Requests the data of the current RSS-feed
     * Also requests all the articles from the feed
     * Then saves it into the database
     *
     * @return [Completable] with success/error
     */
    Completable synchronizeWithNetwork();

    /**
     * Provides cold-observable of the current channel (RSS-feed)
     *
     * @return [ChannelModel] channel
     */
    Observable<ChannelModel> getChannel();

    /**
     * Provides cold-observable of the articles list of the current channel (RSS-feed)
     *
     * @return [List] of [ArticleChannelModel>]
     */
    Observable<List<ArticleChannelModel>> getArticlesChannel();

    /**
     * Updates current channel link. Allows to change the channel (RSS-feed)
     *
     * @param link channel source link
     * @return [Completable] with success/error
     */
    Completable updateChannelLink(String link);

}