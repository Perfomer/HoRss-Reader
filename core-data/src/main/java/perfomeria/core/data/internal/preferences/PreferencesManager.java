package perfomeria.core.data.internal.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class PreferencesManager {

    private static final String PREF_NAME = "HorssPreferences";

    private static SharedPreferences preferences;

    public static void initialize(final @NonNull Context context) {
        if (preferences != null) return;

        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @NonNull
    public static SharedPreferences getPreferences() {
        return preferences;
    }

}