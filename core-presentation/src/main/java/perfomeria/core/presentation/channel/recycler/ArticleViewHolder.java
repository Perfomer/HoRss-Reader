package perfomeria.core.presentation.channel.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.presentation.R;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;
import static perfomeria.core.common.GlideUtil.loadImage;
import static perfomeria.core.common.StringUtil.fromHtml;

class ArticleViewHolder extends RecyclerView.ViewHolder {

    private final TextView date;
    private final TextView title;
    private final TextView description;

    private final ImageView image;
    private final ImageView channelIcon;

    ArticleViewHolder(
            final @NonNull View view,
            final @NonNull OnItemClickListener onClickListener
    ) {
        super(view);

        final ImageView browserButton = view.findViewById(R.id.item_article_browserbutton);

        channelIcon = view.findViewById(R.id.item_article_channel_icon);
        date = view.findViewById(R.id.item_article_date);
        title = view.findViewById(R.id.item_article_title);
        description = view.findViewById(R.id.item_article_description);
        image = view.findViewById(R.id.item_article_image);

        browserButton.setOnClickListener(v -> {
            final int position = getAdapterPosition();
            if (position != NO_POSITION) onClickListener.onItemClick(position);
        });
    }

    void bind(final @NonNull ArticleChannelModel article) {
        date.setText(TimeAgo.using(article.getDateLong()));
        description.setText(fromHtml(article.getDescription()));
        title.setText(fromHtml(article.getTitle()));

        setImage(article.getUrlImage(), R.drawable.placeholder);
        loadImage(channelIcon, article.getUrlIcoChannel(), R.drawable.ic_folder);
    }

    private void setImage(
            final @Nullable String url,
            final @DrawableRes int placeholderResource
    ) {
        if (url == null || url.equals("http://")) {
            image.setVisibility(View.GONE);
        } else {
            image.setVisibility(View.VISIBLE);
            loadImage(image, url, placeholderResource);
        }
    }

}