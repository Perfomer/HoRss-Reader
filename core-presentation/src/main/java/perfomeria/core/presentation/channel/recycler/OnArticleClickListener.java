package perfomeria.core.presentation.channel.recycler;

import androidx.annotation.NonNull;

import perfomeria.core.domain.model.ArticleChannelModel;

@FunctionalInterface
public interface OnArticleClickListener {

    void onBrowserClick(@NonNull ArticleChannelModel article);

}