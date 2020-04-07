package perfomeria.core.presentation.channel.di;

import javax.inject.Singleton;

import dagger.Component;
import perfomeria.core.data.di.DataModule;
import perfomeria.core.presentation.channel.ChannelActivity;

@Singleton
@Component(
    modules = {
        DataModule.class
    }
)
public interface PresentationComponent {

    void inject(ChannelActivity activity);

}