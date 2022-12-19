package com.example.bucket.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeToStringUtils {

    private final static DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String convertToStr(LocalDateTime time) {

        return FORMATTER.format(time);
    }

}
