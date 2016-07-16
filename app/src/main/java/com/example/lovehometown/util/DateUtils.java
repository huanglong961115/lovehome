package com.example.lovehometown.util;

/**
 * Created by Administrator on 2016/6/18.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtils {
    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期以指定格式格式化
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
         return sdf.format(date);
    }
}
