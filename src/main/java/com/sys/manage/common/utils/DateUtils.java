package com.sys.manage.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式(yyyyMMddHHmmss) */
	public final static String DATE_TIME = "yyyyMMddHHmmss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    /**
     * 将日期转换为yyyyMMddHHmmss
     * @Title: yyyyMMddHHmmssFormat 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param date
     * @param @return    设定文件 
     * @return String    返回类型
     * @author tianms
     * @throws 
     * @date 2018年5月18日 上午9:55:22
     */
    public static String yyyyMMddHHmmssFormat(Date date){
    	return format(date, DATE_TIME);
    }
}
