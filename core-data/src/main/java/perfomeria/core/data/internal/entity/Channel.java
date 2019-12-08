package perfomeria.core.data.internal.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
import java.util.Objects;

@Entity
@Root(strict = false, name = "channel")
public class Channel {

    @PrimaryKey
    private int id;

    private String url = "http://";

    private String iconUrl = "http://";

    @Element(required = false, name = "title")
    private String titleChannel;

    @Ignore
    @ElementList(inline = true, name = "item")
    private List<Article> articles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return id == channel.id &&
            Objects.equals(url, channel.url) &&
            Objects.equals(iconUrl, channel.iconUrl) &&
            Objects.equals(titleChannel, channel.titleChannel) &&
            Objects.equals(articles, channel.articles);
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(final List<Article> articles) {
        this.articles = articles;
    }

}