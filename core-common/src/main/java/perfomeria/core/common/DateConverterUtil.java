package perfomeria.core.common;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateConverterUtil {

    private static final List<SimpleDateFormat> dateFormats = new ArrayList<>();

    static {
        dateFormats.add(new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss ZZZ", Locale.ENGLISH));
        dateFormats.add(new SimpleDateFormat("dd MMM yyyy HH:mm:ss ZZZ", Locale.ENGLISH));
        dateFormats.add(new SimpleDateFormat("E MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH));
        dateFormats.add(new SimpleDateFormat("E, dd MMM yyyy HH:mm zzz", Locale.ENGLISH));
    }

    public static long dateToLong(final @NonNull String date) {
        for (int i = 0; i < dateFormats.size(); i++) {
            final long parsed = parseDate(dateFormats.get(i), date);
            if (parsed != 0L) return parsed;
        }

        return 0L;
    }

    private static long parseDate(
            final @NonNull SimpleDateFormat format,
            final @NonNull String date
    ) {
        try {
            return format.parse(date).getTime();
        } catch (ParseException ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

}