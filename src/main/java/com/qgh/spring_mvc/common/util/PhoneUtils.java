package com.qgh.spring_mvc.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtils {
	
	private static String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

	/**
	 * 11位且1开头则返回true
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (StringUtils.isBlank(phone) || phone.length() != 11 || !"1".equals(phone.substring(0, 1))) {
			return false;
		}
		return true;
	}
	
	public static boolean isPhoneNew(String phone) {
		if (phone.length() != 11) {
	        return false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        return m.matches();
	    }
	}
	
}
