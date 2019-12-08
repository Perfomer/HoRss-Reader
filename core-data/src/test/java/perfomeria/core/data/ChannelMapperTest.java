package perfomeria.core.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.mapper.ChannelMapper;
import perfomeria.core.data.mapper.base.ListMapper;
import perfomeria.core.domain.model.ArticleModel;
import perfomeria.core.domain.model.ChannelModel;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class ChannelMapperTest {

    private final static int MOCKDATA_ID = 100;
    private final static String MOCKDATA_TITLE = "channelTitle";
    private final static String MOCKDATA_URL = "channelUrl";
    private final static String MOCKDATA_ICONURL = "iconUrl";
    private final static List<ArticleModel> MOCKDATA_ARTICLES = Collections.emptyList();

    @Mock
    private ListMapper<Article, ArticleModel> articleMapper = Mockito.mock(ListMapper.class);

    private final ChannelMapper channelMapper = new ChannelMapper(articleMapper);

    @Test
    public void map() {
        final Channel channel = new Channel();
        channel.setId(MOCKDATA_ID);
        channel.setTitleChannel(MOCKDATA_TITLE);
        channel.setUrl(MOCKDATA_URL);
        channel.setIconUrl(MOCKDATA_ICONURL);

        final ChannelModel channelModel = new ChannelModel();
        channelModel.setId(MOCKDATA_ID);
        channelModel.setTitleChannel(MOCKDATA_TITLE);
        channelModel.setUrl(MOCKDATA_URL);
        channelModel.setIconUrl(MOCKDATA_ICONURL);
        channelModel.setArticles(MOCKDATA_ARTICLES);

        assertEquals(channelModel, channelMapper.map(channel));
    }

}