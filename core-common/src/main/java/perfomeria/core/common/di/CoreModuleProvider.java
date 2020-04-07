package perfomeria.core.common.di;

import androidx.annotation.NonNull;

public interface CoreModuleProvider {

    @NonNull
    CoreModule getCoreModule();

}