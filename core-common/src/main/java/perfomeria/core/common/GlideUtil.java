package perfomeria.core.common;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

public class GlideUtil {

    public static void loadImage(
        final @NonNull ImageView target,
        final @Nullable String url,
        final @DrawableRes int thumbnailResource
    ) {
        Glide.with(target)
            .load(url)
            .thumbnail(Glide.with(target).load(thumbnailResource))
            .into(target);
    }

}
