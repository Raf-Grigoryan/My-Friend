package org.example.myfriend.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SneakyThrows
    public static Date sqlStringToDate(String dateStr) {
        return simpleDateFormat.parse(dateStr);
    }

}
