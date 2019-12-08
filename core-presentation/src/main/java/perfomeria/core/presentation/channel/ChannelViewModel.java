package perfomeria.core.presentation.channel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import perfomeria.core.data.repository.ArticleRepository;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;
import perfomeria.core.domain.repository.Repository;

public class ChannelViewModel extends ViewModel {

    private final Repository repository = ArticleRepository.getInstance();

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final Subject<ChannelModel> channelSubject = BehaviorSubject.create();
    private final Subject<List<ArticleChannelModel>> articlesSubject = BehaviorSubject.create();
    private final Subject<Boolean> isLoading = BehaviorSubject.create();

    private volatile ChannelModel currentChannel;

    public ChannelViewModel() {
        initializeChannel();
        synchronizeWithNetwork();
        subscribeOnArticles();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

    @NonNull
    Observable<List<ArticleChannelModel>> getArticles() {
        return articlesSubject;
    }

    @NonNull
    Observable<Boolean> isLoading() {
        return isLoading;
    }

    @NonNull
    Observable<ChannelModel> getChannel() {
        return channelSubject;
    }

    @Nullable
    ChannelModel getCurrentChannel() {
        return currentChannel;
    }

    void synchronizeWithNetwork() {
        disposable.add(startSynchronization().subscribe());
    }

    void updateChannelLink(final @NonNull String link) {
        disposable.add(repository.updateChannelLink(link)
                .subscribeOn(Schedulers.io())
                .andThen(startSynchronization())
                .subscribe(this::initializeChannel, Throwable::printStackTrace));
    }

    private void initializeChannel() {
        disposable.add(repository.getChannel()
                .subscribeOn(Schedulers.io())
                .subscribe((channel) -> {
                    currentChannel = channel;
                    channelSubject.onNext(currentChannel);
                }));
    }

    private void subscribeOnArticles() {
        disposable.add(repository.getArticlesChannel()
                .subscribeOn(Schedulers.io())
                .subscribe(articlesSubject::onNext, Throwable::printStackTrace));
    }

    @NotNull
    private Completable startSynchronization() {
        return repository.synchronizeWithNetwork()
                .subscribeOn(Schedulers.io())
                .onErrorComplete()
                .doOnSubscribe(disposable -> isLoading.onNext(true))
                .doOnTerminate(() -> isLoading.onNext(false));
    }

}