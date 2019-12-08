package perfomeria.core.data.mapper;

import androidx.annotation.NonNull;

import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.mapper.base.ListMapper;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.domain.model.ArticleModel;
import perfomeria.core.domain.model.ChannelModel;

public class ChannelMapper implements Mapper<Channel, ChannelModel> {

    private static ChannelMapper INSTANCE;

    private final ListMapper<Article, ArticleModel> articleMapper;

    ChannelMapper(final ListMapper<Article, ArticleModel> articleMapper) {
        this.articleMapper = articleMapper;
    }

    @NonNull
    public static ChannelMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChannelMapper(ListMapper.from(ArticleMapper.getInstance()));
        }

        return INSTANCE;
    }

    @Override
    @NonNull
    public ChannelModel map(final @NonNull Channel channel) {
        ChannelModel model = new ChannelModel();

        model.setUrl(channel.getUrl());
        model.setTitleChannel(channel.getTitleChannel());
        model.setIconUrl(channel.getIconUrl());
        model.setId(channel.getId());
        model.setArticles(articleMapper.map(channel.getArticles()));

        return model;

    }

}