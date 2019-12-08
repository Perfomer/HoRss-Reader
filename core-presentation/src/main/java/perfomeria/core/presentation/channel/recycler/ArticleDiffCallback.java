package perfomeria.core.presentation.channel.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import perfomeria.core.domain.model.ArticleChannelModel;

class ArticleDiffCallback extends DiffUtil.Callback {

    private final List<ArticleChannelModel> oldList;
    private final List<ArticleChannelModel> newList;

    ArticleDiffCallback(
            final @NonNull List<ArticleChannelModel> oldList,
            final @NonNull List<ArticleChannelModel> newList
    ) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

}