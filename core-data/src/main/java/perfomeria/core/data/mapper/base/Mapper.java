package perfomeria.core.data.mapper.base;

import androidx.annotation.WorkerThread;

/**
 * Mapper from [FROM]-type to [TO]-type
 *
 * @param <FROM> "from"-type
 * @param <TO>   "to"-type
 */
public interface Mapper<FROM, TO> {

    @WorkerThread
    TO map(FROM from);

}
