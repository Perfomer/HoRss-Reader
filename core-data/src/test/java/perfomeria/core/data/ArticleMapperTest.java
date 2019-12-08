package perfomeria.core.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.mapper.ArticleMapper;
import perfomeria.core.domain.model.ArticleModel;
import perfomeria.core.domain.model.ChannelModel;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ArticleMapperTest {

    private final static int MOCKDATA_ID = 100;
    private final static int MOCKDATA_CHANNELID = 200;
    private final static long MOCKDATA_DATELONG = 100L;
    private final static String MOCKDATA_TITLE = "articleTitle";
    private final static String MOCKDATA_DESCRIPTION = "articleDescription";
    private final static String MOCKDATA_URL = "articleUrl";
    private final static String MOCKDATA_IMAGEURL = "articleImageUrl";
    private final static String MOCKDATA_DATE = "articleDate";

    private final ArticleMapper articleMapper = ArticleMapper.getInstance();

    @Test
    public void map() {
        final Article article = new Article();
        article.setId(MOCKDATA_ID);
        article.setChannelId(MOCKDATA_CHANNELID);
        article.setDate(MOCKDATA_DATE);
        article.setDateLong(MOCKDATA_DATELONG);
        article.setUrl(MOCKDATA_URL);
        article.setUrlImage(MOCKDATA_IMAGEURL);
        article.setTitle(MOCKDATA_TITLE);
        article.setDescription(MOCKDATA_DESCRIPTION);

        final ArticleModel articleModel = new ArticleModel();
        articleModel.setId(MOCKDATA_ID);
        articleModel.setChannelId(MOCKDATA_CHANNELID);
        articleModel.setDate(MOCKDATA_DATE);
        articleModel.setDateLong(MOCKDATA_DATELONG);
        articleModel.setUrl(MOCKDATA_URL);
        articleModel.setUrlImage(MOCKDATA_IMAGEURL);
        articleModel.setTitle(MOCKDATA_TITLE);
        articleModel.setDescription(MOCKDATA_DESCRIPTION);

        assertEquals(articleModel, articleMapper.map(article));
    }

}