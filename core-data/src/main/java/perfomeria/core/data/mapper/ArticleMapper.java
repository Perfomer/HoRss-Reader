package perfomeria.core.data.mapper;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.domain.model.ArticleModel;

public class ArticleMapper implements Mapper<Article, ArticleModel> {

    @Inject
    public ArticleMapper() {
    }

    @Override
    @NonNull
    public ArticleModel map(final Article article) {
        ArticleModel model = new ArticleModel();

        model.setId(article.getId());
        model.setDate(article.getDate());
        model.setDateLong(article.getDateLong());
        model.setDescription(article.getDescription());
        model.setChannelId(article.getChannelId());
        model.setTitle(article.getTitle());
        model.setUrl(article.getUrl());
        model.setUrlImage(article.getUrlImage());

        return model;
    }

}