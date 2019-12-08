package perfomeria.core.data.internal.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Objects;

@Entity
@Root(strict = false, name = "item")
public class Article {

    @PrimaryKey
    private int id;

    private int channelId;

    @Element(required = false, name = "title")
    private String title;

    @Element(required = false, name = "link")
    private String url = "http://";

    @Path("enclosure")
    @Attribute(required = false, name = "url")
    private String urlImage = "http://";

    @Element(required = false, name = "description")
    private String description;

    @Element(required = false, name = "pubDate")
    private String date;

    private long dateLong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article aArticle = (Article) o;
        return id == aArticle.id &&
            channelId == aArticle.channelId &&
            dateLong == aArticle.dateLong &&
            Objects.equals(title, aArticle.title) &&
            Objects.equals(url, aArticle.url) &&
            Objects.equals(urlImage, aArticle.urlImage) &&
            Objects.equals(description, aArticle.description) &&
            Objects.equals(date, aArticle.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, channelId, title, url, urlImage, description, date, dateLong);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(final int channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(final String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(final long dateLong) {
        this.dateLong = dateLong;
    }

}