package com.haoxitech.canzhaopin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用校验工具类
 * 
 * @author Andy
 * 
 */
public class CheckUtils {

	/**
	 * 检查字符串是否为电话号码的方法
	 * 
	 * @return true or false的判断值
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		Matcher m = p.matcher(mobiles.trim());
		return m.matches();
	}

	/**
	 * 检查字符串是否为正确密码
	 *
	 * @return true or false的判断值
	 */
	public static boolean isPassWord(String passWord) {
		Pattern p = Pattern.compile("^[0-9A-Za-z]{6,16}$");
		Matcher m = p.matcher(passWord.trim());
		return m.matches();
	}

	/**
	 * 检查字符串是否为邮箱地址的方法
	 * 
	 * @return true or false的判断值
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email.trim());
		return m.matches();
	}

	/**
	 * 检测String是否全是中文
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkNameChese(String name) {
		boolean res = true;
		char[] cTemp = name.toCharArray();
		for (int i = 0; i < name.length(); i++) {
			if (!isChinese(cTemp[i])) {
				res = false;
				break;
			}
		}
		return res;
	}

	/**
	 * 检查字符串是否为汉字的方法
	 * 
	 * @return true or false的判断值
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
}
