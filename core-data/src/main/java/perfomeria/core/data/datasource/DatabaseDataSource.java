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


    @NonNull
    public Completable saveChannel(final @NonNull Channel channel) {
        return insertChannel(channel).andThen(optionallyInsertArticles(channel));
    }

    @NonNull
    public Observable<List<ArticleChannel>> getArticles() {
        return articleDao.getAllArticlesChannel();
    }

    @NonNull
    public Observable<Channel> getChannel(final @NonNull String channelUrl) {
        return channelDao.getByUrl(channelUrl);
    }

    @NonNull
    public Completable clearArticles() {
        return Completable.fromAction(articleDao::dropTable);
    }


    @NonNull
    private Completable optionallyInsertArticles(final @NonNull Channel channel) {
        final List<Article> articles = channel.getArticles();
        return articles == null ? Completable.complete() : insertArticles(articles, channel.getId());
    }

    @NonNull
    private Completable insertArticles(
            final @NonNull List<Article> articles,
            final int idChannel
    ) {
        return Completable.fromAction(() -> {
            for (Article currentArticle : articles) {
                currentArticle.setId(currentArticle.getTitle().hashCode());
                currentArticle.setDateLong(dateToLong(currentArticle.getDate()));
                currentArticle.setChannelId(idChannel);
            }

            articleDao.insertElements(articles);
        });
    }

    @NonNull
    private Completable insertChannel(final @NonNull Channel channel) {
        return Completable.fromAction(() -> channelDao.insertElement(channel));
    }

}