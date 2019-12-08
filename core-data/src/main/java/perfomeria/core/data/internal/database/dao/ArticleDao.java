package perfomeria.core.data.internal.database.dao;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.ArticleChannel;

@Dao
public interface ArticleDao {

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertElements(List<Article> articles);

    @Query(" SELECT Article.*, " +
            "       Channel.url as channelUrl, Channel.iconUrl as channelIconUrl, Channel.titleChannel as channelTitle " +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    Observable<List<ArticleChannel>> getAllArticlesChannel();

    @Query(" SELECT Article.*" +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    Observable<List<Article>> getArticles();

    @WorkerThread
    @Query(" SELECT Article.*, " +
            "       Channel.url as channelUrl, Channel.iconUrl as channelIconUrl, Channel.titleChannel as channelTitle " +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    List<ArticleChannel> getArticlesChannel();

    @WorkerThread
    @Query(" SELECT COUNT(id) " +
            "FROM Article " +
            "WHERE channelId = :idChannel")
    int getSizeChannel(int idChannel);

    @WorkerThread
    @Query(" SELECT COUNT(id)" +
            " FROM Article")
    int getSizeChannel();

    @WorkerThread
    @Query(" DELETE FROM Article " +
            "WHERE channelId IN (:idChannel)")
    void deleteElements(int idChannel);

    @WorkerThread
    @Query("DELETE FROM Article")
    void dropTable();

}