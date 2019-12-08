package perfomeria.core.data.internal.database.dao;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertElements(List<Article> articles);

    @Query(" SELECT Article.*, Channel.url, Channel.iconUrl, Channel.titleChannel " +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    Observable<List<ArticleChannel>> getAllArticlesChannel();

    @Query(" SELECT Article.*, Channel.url, Channel.iconUrl, Channel.titleChannel " +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    Observable<List<Article>> getArticles();

    @Query(" SELECT Article.*, Channel.url, Channel.iconUrl, Channel.titleChannel " +
            "FROM Article " +
            "   LEFT JOIN Channel ON (Article.channelId == Channel.id)" +
            "ORDER BY dateLong DESC")
    List<ArticleChannel> getArticlesChannel();

    @Query(" SELECT COUNT(id) " +
            "FROM Article " +
            "WHERE channelId = :idChannel")
    int getSizeChannel(int idChannel);

    @Query(" SELECT COUNT(id)" +
            " FROM Article")
    int getSizeChannel();

    @Query(" DELETE FROM Article " +
            "WHERE channelId IN (:idChannel)")
    void deleteElements(int idChannel);

    @Query("DELETE FROM Article")
    void dropTable();

}
