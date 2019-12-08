package perfomeria.core.common;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import io.reactivex.Single;

public class RxUtil {

    public static <T1, T2> Single<Pair<T1, T2>> zip(
            final @NonNull Single<T1> source1,
            final @NonNull Single<T2> source2
    ) {
        return source1.zipWith(source2, Pair::new);
    }

}