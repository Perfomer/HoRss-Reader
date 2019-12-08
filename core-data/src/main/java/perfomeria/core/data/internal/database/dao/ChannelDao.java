package perfomeria.core.data.internal.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import perfomeria.core.data.internal.entity.Channel;

@Dao
public interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertElement(Channel channel);

    @Query("SELECT * FROM Channel WHERE Channel.url IN (:url)")
    Observable<Channel> get(String url);

    @Query("SELECT * FROM Channel ORDER BY titleChannel ASC")
    Observable<List<Channel>> getAll();

    @Query("DELETE FROM Channel WHERE id IN (:idChannel)")
    void deleteElement(int idChannel);

}