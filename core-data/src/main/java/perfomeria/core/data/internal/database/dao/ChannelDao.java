package perfomeria.core.data.internal.database.dao;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import perfomeria.core.data.internal.entity.Channel;

@Dao
public interface ChannelDao {

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertElement(Channel channel);

    @WorkerThread
    @Query("SELECT * FROM Channel WHERE Channel.id == :id")
    Channel getById(int id);

    @Query("SELECT * FROM Channel WHERE Channel.url IN (:url)")
    Observable<Channel> getByUrl(String url);

    @Query("SELECT * FROM Channel ORDER BY titleChannel ASC")
    Observable<List<Channel>> getAll();

    @WorkerThread
    @Query("DELETE FROM Channel WHERE id IN (:idChannel)")
    void deleteElement(int idChannel);

}