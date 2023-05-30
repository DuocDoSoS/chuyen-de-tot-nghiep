package com.example.silver.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commons {

    private static final Logger LOGGER = LoggerFactory.getLogger(Commons.class);
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String TIME_STAMP = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.isEmpty() || s.equals("null")) {
            return true;
        }
        s = s.trim();
        return "" .equals(s);
    }

    public static boolean isNotNullAndEmpty(String str){
        return str != null && str.length() > 0;
    }

    public static Date convertStringToDate(String str, String format){
        if (isNotNullAndEmpty(str)){
            var simpleDateFormat = new SimpleDateFormat(format);
            try {
                return simpleDateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
