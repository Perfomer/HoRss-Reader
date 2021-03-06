package perfomeria.core.data.mapper;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import perfomeria.core.data.internal.entity.ArticleChannel;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.domain.model.ArticleChannelModel;

public class ArticleChannelMapper implements Mapper<ArticleChannel, ArticleChannelModel> {

    @Inject
    public ArticleChannelMapper() {
    }

    @Override
    @NonNull
    public ArticleChannelModel map(final ArticleChannel articleChannel) {
        ArticleChannelModel model = new ArticleChannelModel();

        model.setDate(articleChannel.getDate());
        model.setDateLong(articleChannel.getDateLong());
        model.setDescription(articleChannel.getDescription());
        model.setId(articleChannel.getId());
        model.setChannelId(articleChannel.getChannelId());
        model.setLink(articleChannel.getLink());
        model.setTitle(articleChannel.getTitle());
        model.setTitleChannel(articleChannel.getChannelTitle());
        model.setUrl(articleChannel.getChannelUrl());
        model.setUrlIcoChannel(articleChannel.getChannelIconUrl());
        model.setUrlImage(articleChannel.getUrlImage());

        return model;
    }

}
