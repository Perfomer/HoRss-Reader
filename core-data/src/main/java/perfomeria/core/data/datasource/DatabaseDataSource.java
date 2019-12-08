package perfomeria.core.data.datasource;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import perfomeria.core.data.internal.database.AppDatabase;
import perfomeria.core.data.internal.database.DatabaseManager;
import perfomeria.core.data.internal.database.dao.ArticleDao;
import perfomeria.core.data.internal.database.dao.ChannelDao;
import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.ArticleChannel;
import perfomeria.core.data.internal.entity.Channel;

import static perfomeria.core.common.DateConverterUtil.dateToLong;

public class DatabaseDataSource {

    private static DatabaseDataSource INSTANCE;

    private final ArticleDao articleDao;
    private final ChannelDao channelDao;


    DatabaseDataSource(final AppDatabase database) {
        articleDao = database.getArticleDao();
        channelDao = database.getChannelDao();
    }

    @NonNull
    public static DatabaseDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseDataSource(DatabaseManager.getDatabase());
        }

        return INSTANCE;
    }


    /**
     * Saves the [channel] into the database.
     * Also can save its articles if there are articles at all.
     *
     * @param channel saving channel
     * @return [Completable] with success/error
     */
    @NonNull
    public Completable saveChannel(final @NonNull Channel channel) {
        return insertChannel(channel).andThen(optionallyInsertArticles(channel));
    }

    /**
     * Provides cold-observable of the articles list of the current channel (RSS-feed).
     *
     * @return [List] of [ArticleChannelModel>]
     */
    @NonNull
    public Observable<List<ArticleChannel>> getArticles() {
        return articleDao.getAllArticlesChannel();
    }

    /**
     * Provides cold-observable of the current channel (RSS-feed)
     *
     * @return [ChannelModel] channel
     */
    @NonNull
    public Observable<Channel> getChannel(final @NonNull String channelUrl) {
        return channelDao.getByUrl(channelUrl);
    }

    /**
     * Drops the articles table in the database.
     *
     * @return [Completable] with success/error
     */
    @NonNull
    public Completable clearArticles() {
        return Completable.fromAction(articleDao::dropTable);
    }


    /**
     * If there are articles in the [Channel] object then saves them to the database.
     *
     * @param channel channel
     * @return [Completable] with success/error
     */
    @NonNull
    private Completable optionallyInsertArticles(final @NonNull Channel channel) {
        final List<Article> articles = channel.getArticles();
        return articles == null ? Completable.complete() : insertArticles(articles, channel.getId());
    }

    /**
     * Inserts the articles into the database for the defined channel.
     *
     * @param articles  articles to save
     * @param channelId channel that holds the articles
     * @return [Completable] with success/error
     */
    @NonNull
    private Completable insertArticles(
            final @NonNull List<Article> articles,
            final int channelId
    ) {
        return Completable.fromAction(() -> {
            for (Article currentArticle : articles) {
                currentArticle.setId(currentArticle.getTitle().hashCode());
                currentArticle.setDateLong(dateToLong(currentArticle.getDate()));
                currentArticle.setChannelId(channelId);
            }

            articleDao.insertElements(articles);
        });
    }

    /**
     * Inserts the [Channel] into the database.
     *
     * @param channel channel
     * @return [Completable] with success/error
     */
    @NonNull
    private Completable insertChannel(final @NonNull Channel channel) {
        return Completable.fromAction(() -> channelDao.insertElement(channel));
    }

}