package perfomeria.core.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import perfomeria.core.data.internal.entity.ArticleChannel;
import perfomeria.core.data.mapper.ArticleChannelMapper;
import perfomeria.core.domain.model.ArticleChannelModel;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ArticleChannelMapperTest {

    private final static int MOCKDATA_ID = 100;
    private final static int MOCKDATA_CHANNELID = 200;
    private final static long MOCKDATA_DATELONG = 100L;
    private final static String MOCKDATA_TITLE = "articleTitle";
    private final static String MOCKDATA_DESCRIPTION = "articleDescription";
    private final static String MOCKDATA_URL = "articleUrl";
    private final static String MOCKDATA_LINK = "channelUrl";
    private final static String MOCKDATA_ICON = "channelIcon";
    private final static String MOCKDATA_CHANNEL_TITLE = "channelTitle";
    private final static String MOCKDATA_IMAGEURL = "articleImageUrl";
    private final static String MOCKDATA_DATE = "articleDate";

    private final ArticleChannelMapper articleMapper = ArticleChannelMapper.getInstance();

    @Test
    public void map() {
        final ArticleChannel article = new ArticleChannel();
        article.setId(MOCKDATA_ID);
        article.setChannelId(MOCKDATA_CHANNELID);
        article.setDate(MOCKDATA_DATE);
        article.setDateLong(MOCKDATA_DATELONG);
        article.setChannelUrl(MOCKDATA_URL);
        article.setUrlImage(MOCKDATA_IMAGEURL);
        article.setTitle(MOCKDATA_TITLE);
        article.setDescription(MOCKDATA_DESCRIPTION);
        article.setLink(MOCKDATA_LINK);
        article.setChannelIconUrl(MOCKDATA_ICON);
        article.setChannelTitle(MOCKDATA_CHANNEL_TITLE);

        final ArticleChannelModel articleModel = new ArticleChannelModel();
        articleModel.setId(MOCKDATA_ID);
        articleModel.setChannelId(MOCKDATA_CHANNELID);
        articleModel.setDate(MOCKDATA_DATE);
        articleModel.setDateLong(MOCKDATA_DATELONG);
        articleModel.setUrl(MOCKDATA_URL);
        articleModel.setUrlImage(MOCKDATA_IMAGEURL);
        articleModel.setTitle(MOCKDATA_TITLE);
        articleModel.setDescription(MOCKDATA_DESCRIPTION);
        articleModel.setLink(MOCKDATA_LINK);
        articleModel.setUrlIcoChannel(MOCKDATA_ICON);
        articleModel.setTitleChannel(MOCKDATA_CHANNEL_TITLE);

        assertEquals(articleModel, articleMapper.map(article));
    }

}