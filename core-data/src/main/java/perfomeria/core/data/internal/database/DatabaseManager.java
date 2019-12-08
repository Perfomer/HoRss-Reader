package perfomeria.core.data.internal.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

public class DatabaseManager {

    private static AppDatabase database;

    public static void initialize(final Context context) {
        if (database != null) return;

        database = Room.databaseBuilder(context, AppDatabase.class, "rssreaderdb")
                .fallbackToDestructiveMigration() // Attention
                .build();
    }

    @NonNull
    public static AppDatabase getDatabase() {
        return database;
    }

}