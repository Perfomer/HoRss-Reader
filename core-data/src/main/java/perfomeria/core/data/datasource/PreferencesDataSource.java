package perfomeria.core.data.datasource;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import io.reactivex.Completable;
import io.reactivex.Single;
import perfomeria.core.data.internal.preferences.PreferencesManager;

@SuppressLint("ApplySharedPref")
public class PreferencesDataSource {

    private static final String KEY_RSSLINK = "RssLink";
    private static final String DEF_RSSLINK = "https://lenta.ru/rss";

    private static PreferencesDataSource INSTANCE;

    private final SharedPreferences preferences;


    PreferencesDataSource(final SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @NonNull
    public static PreferencesDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesDataSource(PreferencesManager.getPreferences());
        }

        return INSTANCE;
    }

    @NonNull
    public Single<String> getCurrentChannelLink() {
        return Single.fromCallable(this::getCurrentChannelLinkSync);
    }

    @NonNull
    public Completable setCurrentChannelLink(@NonNull final String link) {
        return Completable.fromAction(() -> setCurrentChannelLinkSync(link));
    }


    @WorkerThread
    @NonNull
    @SuppressWarnings("ConstantConditions")
    private String getCurrentChannelLinkSync() {
        return preferences.getString(KEY_RSSLINK, DEF_RSSLINK);
    }

    @WorkerThread
    private void setCurrentChannelLinkSync(final @NonNull String link) {
        if (link.equals(getCurrentChannelLinkSync())) {
            throw new IllegalArgumentException("There's already that link: " + link);
        }

        preferences.edit().putString(KEY_RSSLINK, link).commit();
    }

}