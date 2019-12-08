package perfomeria.core.domain.model;

import java.util.List;
import java.util.Objects;

public class ChannelModel {

    private int id;

    private String url = "http://";

    private String iconUrl = "http://";

    private String titleChannel;

    private List<ArticleModel> articles;


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelModel channelModel = (ChannelModel) o;
        return id == channelModel.id &&
            Objects.equals(url, channelModel.url) &&
            Objects.equals(iconUrl, channelModel.iconUrl) &&
            Objects.equals(titleChannel, channelModel.titleChannel) &&
            Objects.equals(articles, channelModel.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, iconUrl, titleChannel, articles);
    }


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(final String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitleChannel() {
        return titleChannel;
    }

    public void setTitleChannel(final String titleChannel) {
        this.titleChannel = titleChannel;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(final List<ArticleModel> articles) {
        this.articles = articles;
    }

}