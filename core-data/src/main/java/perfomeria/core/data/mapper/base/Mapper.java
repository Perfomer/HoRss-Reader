package perfomeria.core.data.mapper.base;

public interface Mapper<FROM, TO> {

    TO map(FROM from);

}
