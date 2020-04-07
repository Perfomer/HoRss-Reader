package perfomeria.core.data.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import perfomeria.core.BuildConfig;
import perfomeria.core.common.di.CoreModule;
import perfomeria.core.data.datasource.DatabaseDataSource;
import perfomeria.core.data.datasource.NetworkDataSource;
import perfomeria.core.data.datasource.PreferencesDataSource;
import perfomeria.core.data.internal.database.AppDatabase;
import perfomeria.core.data.internal.entity.Article;
import perfomeria.core.data.internal.entity.ArticleChannel;
import perfomeria.core.data.internal.entity.Channel;
import perfomeria.core.data.internal.network.NetworkManager;
import perfomeria.core.data.internal.network.RssApi;
import perfomeria.core.data.mapper.ArticleChannelMapper;
import perfomeria.core.data.mapper.ArticleMapper;
import perfomeria.core.data.mapper.ChannelMapper;
import perfomeria.core.data.mapper.base.ListMapper;
import perfomeria.core.data.mapper.base.Mapper;
import perfomeria.core.data.repository.ArticleRepository;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ArticleModel;
import perfomeria.core.domain.model.ChannelModel;
import perfomeria.core.domain.repository.Repository;

@Module(
    includes = {
        CoreModule.class
    }
)
public interface DataModule {

    @Singleton
    @Provides
    static Repository getRepository(
        final @NonNull PreferencesDataSource preferencesDataSource,
        final @NonNull DatabaseDataSource databaseDataSource,
        final @NonNull NetworkDataSource networkDataSource,
        final @NonNull Mapper<Channel, ChannelModel> channelMapper,
        final @NonNull ListMapper<ArticleChannel, ArticleChannelModel> articleChannelMapper
    ) {
        return new ArticleRepository(
            preferencesDataSource,
            databaseDataSource,
            networkDataSource,
            channelMapper,
            articleChannelMapper
        );
    }

    @Singleton
    @Provides
    static SharedPreferences getSharedPreferences(
        final @NonNull Context context
    ) {
        return context.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static AppDatabase getDatabase(final @NonNull Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration() // Attention
            .build();
    }

    @Singleton
    @Provides
    static RssApi getApi() {
        return NetworkManager.createApi();
    }

    @Singleton
    @Provides
    static Mapper<Channel, ChannelModel> getChannelMapper(
        final @NonNull ListMapper<Article, ArticleModel> articleMapper
    ) {
        return new ChannelMapper(articleMapper);
    }

    @Singleton
    @Provides
    static ListMapper<ArticleChannel, ArticleChannelModel> getArticleChannelListMapper(
        final @NonNull Mapper<ArticleChannel, ArticleChannelModel> mapper
    ) {
        return ListMapper.from(mapper);
    }

    @Singleton
    @Provides
    static ListMapper<Article, ArticleModel> getArticleListMapper(
        final @NonNull Mapper<Article, ArticleModel> mapper
    ) {
        return ListMapper.from(mapper);
    }

    @Singleton
    @Binds
    Mapper<Article, ArticleModel> getArticleMapper(
        final @NonNull ArticleMapper mapper
    );

    @Singleton
    @Binds
    Mapper<ArticleChannel, ArticleChannelModel> getArticleChannelMapper(
        final @NonNull ArticleChannelMapper mapper
    );

}