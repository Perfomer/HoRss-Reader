package perfomeria.core.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

public class IntentUtil {

    public static void startBrowser(final @NonNull Context context, final @NonNull String link) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

}