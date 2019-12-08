package perfomeria.core.data;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import perfomeria.core.data.internal.database.AppDatabase;
import perfomeria.core.data.internal.database.dao.ChannelDao;
import perfomeria.core.data.internal.entity.Channel;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ChannelDaoTest {

    private AppDatabase database;
    private ChannelDao channelDao;

    @Before
    public void createDatabase() {
        final Context context = ApplicationProvider.getApplicationContext();

        database = Room.databaseBuilder(context, AppDatabase.class, "testdb")
                .allowMainThreadQueries()
                .build();

        channelDao = database.getChannelDao();
    }

    @After
    public void closeDatabase() {
        database.close();
    }

    @Test
    public void insertElement() {
        final Channel channel = new Channel();
        channel.setId(810);
        channel.setTitleChannel("Title");
        channel.setIconUrl("IconUrl");
        channel.setUrl("Url");

        channelDao.insertElement(channel);

        assertEquals(channel, channelDao.getById(810));
    }

}