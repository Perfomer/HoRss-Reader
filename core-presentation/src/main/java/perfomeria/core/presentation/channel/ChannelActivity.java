package perfomeria.core.presentation.channel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import perfomeria.core.common.IntentUtil;
import perfomeria.core.domain.model.ArticleChannelModel;
import perfomeria.core.domain.model.ChannelModel;
import perfomeria.core.presentation.R;
import perfomeria.core.presentation.channel.recycler.ArticleAdapter;

public class ChannelActivity extends AppCompatActivity {

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final ArticleAdapter adapter = new ArticleAdapter(this::onBrowserClick);
    private final ChannelLinkEditDialogManager dialogManager = new ChannelLinkEditDialogManager(this);

    private ChannelViewModel viewModel;

    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        viewModel = ViewModelProviders.of(this).get(ChannelViewModel.class);

        final RecyclerView recyclerView = findViewById(R.id.channel_articleslist);
        toolbar = findViewById(R.id.channel_toolbar);
        swipeRefreshLayout = findViewById(R.id.channel_swiperefresh);

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
                .doOnNext(articles -> toolbar.setTitle(getString(R.string.channel_articles_count, articles.size())))
                .subscribe(adapter::setArticles, Throwable::printStackTrace));

        disposable.add(viewModel.isLoading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(swipeRefreshLayout::setRefreshing, Throwable::printStackTrace));
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.clear();
    }

    private void openChannelEditor() {
        final ChannelModel channel = viewModel.getCurrentChannel();
        final String currentLink = channel == null ? null : channel.getUrl();

        dialogManager.showDialog(currentLink, viewModel::updateChannelLink);
    }

    private void onBrowserClick(@NonNull ArticleChannelModel article) {
        IntentUtil.startBrowser(this, article.getLink());
    }

}