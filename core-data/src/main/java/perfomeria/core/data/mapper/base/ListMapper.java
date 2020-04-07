package perfomeria.core.data.mapper.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ListMapper<FROM, TO> implements Mapper<List<FROM>, List<TO>> {

    @Override
    @NonNull
    public final List<TO> map(final @Nullable List<FROM> from) {
        List<TO> result = new ArrayList<>();

        if (from != null) {
            for (FROM fromItem : from) {
                result.add(mapItem(fromItem));
            }
        }

        return result;
    }

    protected abstract TO mapItem(FROM from);

    /**
     * Wraps received [mapper] into the [ListMapper]
     *
     * @param mapper source mapper
     * @param <T>    mapper "from"-type
     * @param <R>    mapper "to"-type
     * @return ListMapper
     */
    @NonNull
    public static <T, R> ListMapper<T, R> from(final @NonNull Mapper<T, R> mapper) {
        return new ListMapper<T, R>() {
            @Override
            protected R mapItem(T t) {
                return mapper.map(t);
            }
        };
    }

}