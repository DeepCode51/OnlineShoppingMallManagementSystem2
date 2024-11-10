package cn.bdqn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证 工具类
 * @author bdqn
 *
 */
public class RegUtils {

	static String emailReg="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
	static String mobileReg="^\\d{5,11}$";
	static String identityCodeReg="^\\w{18}$";
	
	public static boolean checkEmail(String email){
		Pattern pattern=Pattern.compile(emailReg);
		Matcher matcher=pattern.matcher(email);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
	public static boolean checkMobile(String mobile){
		Pattern pattern=Pattern.compile(mobileReg);
		Matcher matcher=pattern.matcher(mobile);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
	public static boolean checkIdentityCodeReg(String identityCode){
		Pattern pattern=Pattern.compile(identityCodeReg);
		Matcher matcher=pattern.matcher(identityCode);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
}
