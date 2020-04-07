package perfomeria.core.presentation.channel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import perfomeria.core.common.IntentUtil;
import perfomeria.core.common.di.CoreModuleProvider;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;
import perfomeria.core.presentation.R;
import perfomeria.core.presentation.channel.di.DaggerPresentationComponent;
import perfomeria.core.presentation.channel.recycler.ArticleAdapter;

public class ChannelActivity extends AppCompatActivity {

    @Inject
    ChannelViewModel viewModel;

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final ArticleAdapter adapter = new ArticleAdapter(this::onBrowserClick);

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView channelTitle;
    private TextView articlesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        CoreModuleProvider coreModuleProvider = (CoreModuleProvider) getApplicationContext();

        DaggerPresentationComponent
            .builder()
            .coreModule(coreModuleProvider.getCoreModule())
            .build()
            .inject(this);

        final RecyclerView recyclerView = findViewById(R.id.channel_articleslist);
        final AppBarLayout appbar = findViewById(R.id.channel_appbarlayout);
        final Toolbar toolbar = findViewById(R.id.channel_toolbar);
        final View.OnClickListener scrollToTop = (v) -> recyclerView.smoothScrollToPosition(0);

        channelTitle = findViewById(R.id.channel_toolbar_title);
        articlesCount = findViewById(R.id.channel_toolbar_articlecount);
        swipeRefreshLayout = findViewById(R.id.channel_swiperefresh);

        appbar.setOnClickListener(scrollToTop);
        toolbar.setOnClickListener(scrollToTop);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(viewModel::synchronizeWithNetwork);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.channel_settings) {
            openChannelEditor();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        disposable.add(viewModel.getArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(articles -> articlesCount.setText(getString(R.string.channel_articles_count, articles.size())))
            .subscribe(adapter::setArticles, Throwable::printStackTrace));

        disposable.add(viewModel.isLoading()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(swipeRefreshLayout::setRefreshing, Throwable::printStackTrace));

        disposable.add(viewModel.getChannel()
            .observeOn(AndroidSchedulers.mainThread())
            .map(ChannelModel::getTitleChannel)
            .subscribe(channelTitle::setText, Throwable::printStackTrace));
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.clear();
    }

    private void openChannelEditor() {
        final ChannelModel channel = viewModel.getCurrentChannel();
        final String currentLink = channel == null ? null : channel.getUrl();

        ChannelLinkEditDialogManager.showDialog(this, currentLink, viewModel::updateChannelLink);
    }

    private void onBrowserClick(@NonNull ArticleChannelModel article) {
        IntentUtil.startBrowser(this, article.getLink());
    }

}