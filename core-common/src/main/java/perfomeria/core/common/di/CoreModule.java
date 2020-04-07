package perfomeria.core.common.di;

import android.content.Context;

import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class CoreModule {

    private Context context;

    public CoreModule(final @NonNull Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

}