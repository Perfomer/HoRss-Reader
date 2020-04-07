package perfomeria.horss;

import android.app.Application;

import androidx.annotation.NonNull;

import com.facebook.stetho.Stetho;

import perfomeria.core.BuildConfig;
import perfomeria.core.common.di.CoreModule;
import perfomeria.core.common.di.CoreModuleProvider;

public class App extends Application implements CoreModuleProvider {

    private CoreModule coreModule;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        coreModule = new CoreModule(this);
    }

    @NonNull
    @Override
    public CoreModule getCoreModule() {
        return coreModule;
    }

}