package perfomeria.core.data.mapper.base;

/**
 * Mapper from [FROM]-type to [TO]-type
 *
 * @param <FROM> "from"-type
 * @param <TO>   "to"-type
 */
public interface Mapper<FROM, TO> {

    TO map(FROM from);

}
