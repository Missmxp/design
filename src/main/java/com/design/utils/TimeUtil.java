package com.design.utils;
/**
 * Created by cvter on 2017/5/24.
 */

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String strReplaceChar(String strDate, String str) {
        strDate = strDate.replace(str, " ") + ":00";
        return strDate;
    }
}

