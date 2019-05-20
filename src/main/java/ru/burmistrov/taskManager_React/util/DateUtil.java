package ru.burmistrov.taskManager_React.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    public static Date parseString(String date) throws ParseException {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(date);
        return dateEnd;
    }

    public static String parseDate(Date date) throws ParseException {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(date);
    }
}
