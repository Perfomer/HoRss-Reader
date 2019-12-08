package perfomeria.core.data.mapper;

import androidx.annotation.NonNull;

import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.domain.model.ArticleModel;

public class ArticleMapper implements Mapper<Article, ArticleModel> {

    private static ArticleMapper INSTANCE;

    private ArticleMapper() {}

    @NonNull
    public static ArticleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArticleMapper();
        }

        return INSTANCE;
    }

    @Override
    @NonNull
    public ArticleModel map(final Article article) {
        ArticleModel model = new ArticleModel();

        model.setDate(article.getDate());
        model.setDateLong(article.getDateLong());
        model.setDescription(article.getDescription());
        model.setId(article.getId());
        model.setChannelId(article.getChannelId());
        model.setTitle(article.getTitle());
        model.setUrl(article.getUrl());
        model.setUrlImage(article.getUrlImage());

        return model;
    }

}