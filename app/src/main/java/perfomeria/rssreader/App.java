package perfomeria.rssreader;

import android.app.Application;

import com.facebook.stetho.Stetho;

import perfomeria.core.data.internal.database.DatabaseManager;
import perfomeria.core.data.internal.preferences.PreferencesManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        DatabaseManager.initialize(this);
        PreferencesManager.initialize(this);
    }

}