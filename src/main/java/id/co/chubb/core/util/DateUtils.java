package id.co.chubb.core.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String FORMAT_DATE_DDMMYYYY = "dd/MM/yyyy";

    public static LocalDateTime epochToLocalDateTime(Long epochSecond) {
        Instant instantEpoch = Instant.ofEpochSecond(epochSecond);
        LocalDateTime zonedDateTime = LocalDateTime.ofInstant(instantEpoch, ZoneId.of("Asia/Jakarta"));
        return zonedDateTime;
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return dateTime.format(formatter);
    }

    public static LocalDateTime stringToDateTime(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate.atStartOfDay();
    }
}
