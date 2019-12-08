package perfomeria.core.domain.model;

import java.util.Objects;

public class ArticleModel {

    private int id;

    private int channelId;

    private String title;

    private String url = "http://";

    private String urlImage = "http://";

    private String description;

    private String date;

    private long dateLong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleModel aArticle = (ArticleModel) o;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

}