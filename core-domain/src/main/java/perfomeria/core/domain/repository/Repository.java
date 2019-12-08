package perfomeria.core.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;

public interface Repository {

    Completable synchronizeWithNetwork();

    Observable<ChannelModel> getChannel();

    Observable<List<ArticleChannelModel>> getArticlesChannel();

    Single<String> getCurrentChannelLink();

    Completable updateChannelLink(String link);

}