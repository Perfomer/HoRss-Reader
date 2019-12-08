package perfomeria.core.data.internal.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import perfomeria.core.data.internal.database.dao.ArticleDao;
import perfomeria.core.data.internal.database.dao.ChannelDao;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.internal.entity.Article;

@Database(entities = {Article.class, Channel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao getArticleDao();

    public abstract ChannelDao getChannelDao();

}
