package com.sys.manage.common.utils;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {
	

	/** 
     * 获得一个UUID 32位
     *  
     * @return String UUID 
     */  
	public static String generateID(){
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}


}
