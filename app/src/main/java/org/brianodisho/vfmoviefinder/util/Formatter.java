package org.brianodisho.vfmoviefinder.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Formatting util methods.
 */

public final class Formatter {
    private static Locale __locale;

    private static SimpleDateFormat __dateFormat;

    private Formatter() {
        throw new RuntimeException("No Instances Allowed");
    }


    public static String fromUnixTimestampToDate(long timestamp) {
        Date date = new Date(timestamp);
        if (__locale == null) {
            __locale = Locale.getDefault();
        }
        __dateFormat = new SimpleDateFormat("yyyy-MM-dd", __locale);
        return __dateFormat.format(date);
    }
}
