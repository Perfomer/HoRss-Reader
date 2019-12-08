package perfomeria.core.data.internal.entity;

import java.util.Objects;

public class ArticleChannel {

    private int id;

    private int channelId;

    private long dateLong;

    private String title;

    private String link = "http://";

    private String description;

    private String date;

    private String urlImage = "http://";

    private String channelUrl = "http://";

    private String channelIconUrl = "http://";

    private String channelTitle;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleChannel that = (ArticleChannel) o;
        return id == that.id &&
            channelId == that.channelId &&
            dateLong == that.dateLong &&
            Objects.equals(title, that.title) &&
            Objects.equals(link, that.link) &&
            Objects.equals(description, that.description) &&
            Objects.equals(date, that.date) &&
            Objects.equals(urlImage, that.urlImage) &&
            Objects.equals(channelUrl, that.channelUrl) &&
            Objects.equals(channelIconUrl, that.channelIconUrl) &&
            Objects.equals(channelTitle, that.channelTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, channelId, title, link, description, date, dateLong, urlImage, channelUrl, channelIconUrl, channelTitle);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(long dateLong) {
        this.dateLong = dateLong;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelIconUrl() {
        return channelIconUrl;
    }

    public void setChannelIconUrl(String channelIconUrl) {
        this.channelIconUrl = channelIconUrl;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

}