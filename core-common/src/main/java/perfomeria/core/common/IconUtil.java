package perfomeria.core.common;

import androidx.annotation.NonNull;

public class IconUtil {

    @NonNull
    public static String getIconUri(final @NonNull String url) {
        final StringBuilder iconUrl = new StringBuilder(url);

        for (int i = 8; i < iconUrl.length(); i++) {
            if (url.charAt(i) != '/') continue;

            iconUrl.delete(i, iconUrl.length());
            iconUrl.append("/favicon.ico");
            break;
        }

        return iconUrl.toString();
    }

}