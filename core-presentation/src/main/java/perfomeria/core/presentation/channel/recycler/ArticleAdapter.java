package perfomeria.core.presentation.channel.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.presentation.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private final OnArticleClickListener onClickListener;
    private List<ArticleChannelModel> articles = Collections.emptyList();

    public ArticleAdapter(final @NonNull OnArticleClickListener onClickListener) {
        this.onClickListener = onClickListener;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(final @NonNull ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view, this::onItemClick);
    }

    @Override
    public void onBindViewHolder(final @NonNull ArticleViewHolder holder, final int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public long getItemId(int position) {
        if (articles == null) return 0;
        return articles.get(position).getId();
    }

    @Override
    public int getItemCount() {
        if (articles == null) return 0;
        return articles.size();
    }

    public void setArticles(final @NonNull List<ArticleChannelModel> articles) {
        final ArticleDiffCallback callback = new ArticleDiffCallback(this.articles, articles);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        this.articles = articles;
        diffResult.dispatchUpdatesTo(this);
    }

    private void onItemClick(final int position) {
        onClickListener.onBrowserClick(articles.get(position));
    }

}