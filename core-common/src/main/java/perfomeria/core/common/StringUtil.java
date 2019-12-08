package perfomeria.core.common;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;

public class StringUtil {

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(final @NonNull String string) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(string);
        }
    }

}
