package perfomeria.core.data.repository;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import perfomeria.core.common.IconUtil;
import perfomeria.core.common.RxUtil;
import perfomeria.core.data.datasource.DatabaseDataSource;
import perfomeria.core.data.datasource.NetworkDataSource;
import perfomeria.core.data.datasource.PreferencesDataSource;
import perfomeria.core.data.internal.entity.ArticleChannel;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.internal.entity.Rss;
import perfomeria.core.data.mapper.ArticleChannelMapper;
import perfomeria.core.data.mapper.ChannelMapper;
import perfomeria.core.data.mapper.base.ListMapper;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;
import perfomeria.core.domain.repository.Repository;

public class ArticleRepository implements Repository {

    private static ArticleRepository INSTANCE;

    private final PreferencesDataSource preferencesDataSource;
    private final DatabaseDataSource databaseDataSource;
    private final NetworkDataSource networkDataSource;

    private final Mapper<Channel, ChannelModel> channelMapper;
    private final ListMapper<ArticleChannel, ArticleChannelModel> articleChannelMapper;


    public ArticleRepository(
            final @NonNull PreferencesDataSource preferencesDataSource,
            final @NonNull DatabaseDataSource databaseDataSource,
            final @NonNull NetworkDataSource networkDataSource,
            final @NonNull Mapper<Channel, ChannelModel> channelMapper,
            final @NonNull ListMapper<ArticleChannel, ArticleChannelModel> articleChannelMapper
    ) {
        this.preferencesDataSource = preferencesDataSource;
        this.databaseDataSource = databaseDataSource;
        this.networkDataSource = networkDataSource;
        this.channelMapper = channelMapper;
        this.articleChannelMapper = articleChannelMapper;
    }

    @NonNull
    public static ArticleRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArticleRepository(
                    PreferencesDataSource.getInstance(),
                    DatabaseDataSource.getInstance(),
                    NetworkDataSource.getInstance(),
                    ChannelMapper.getInstance(),
                    ListMapper.from(ArticleChannelMapper.getInstance())
            );
        }

        return INSTANCE;
    }


    @Override
    @NonNull
    @SuppressWarnings("ConstantConditions")
    public Completable synchronizeWithNetwork() {
        return preferencesDataSource.getCurrentChannelLink()
                .flatMap((link) -> RxUtil.zip(networkDataSource.loadRss(link), Single.just(link)))
                .map((pair) -> extractChannel(pair.first, pair.second))
                .flatMapCompletable(databaseDataSource::saveChannel);
    }

    @Override
    @NonNull
    public Observable<ChannelModel> getChannel() {
        return preferencesDataSource.getCurrentChannelLink().toObservable()
                .flatMap(databaseDataSource::getChannel)
                .map(channelMapper::map);
    }

    @Override
    @NonNull
    public Observable<List<ArticleChannelModel>> getArticlesChannel() {
        return databaseDataSource.getArticles().map(articleChannelMapper::map);
    }

    @Override
    @NonNull
    public Single<String> getCurrentChannelLink() {
        return preferencesDataSource.getCurrentChannelLink();
    }

    @Override
    @NonNull
    public Completable updateChannelLink(final @NonNull String link) {
        return preferencesDataSource.setCurrentChannelLink(link)
                .andThen(databaseDataSource.clearArticles());
    }


    @NonNull
    private static Channel extractChannel(
            final @NonNull Rss rss,
            final @NonNull String channelLink
    ) {
        Channel channel = rss.getChannel();

        channel.setIconUrl(IconUtil.getIconUri(channelLink));
        channel.setId(channel.getTitleChannel().hashCode());
        channel.setUrl(channelLink);

        return channel;
    }

}