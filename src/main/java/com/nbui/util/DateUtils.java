package com.nbui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 生成订单编号
 * @author ggz
 *
 */
public class DateUtils{

	public static String getCurrentDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmssdd");
		String str = sdf.format(new java.util.Date());
		return str;
	}
	
	
	//通过生日日期计算年龄
    @SuppressWarnings("unused")
	public static Integer calculateAge(Date docBirthday) {
       /* Object docBirthday = doc.get("birthday");*/
        if(docBirthday!=null){
            Calendar cal = Calendar.getInstance();
 
            if (cal.before((Date)docBirthday)) {
                throw new IllegalArgumentException(
                        "The birthDay is before Now.It's unbelievable!");
            }
 
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
 
            cal.setTime((Date)docBirthday);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
 
            int age = yearNow - yearBirth;
 
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            }
            return age;
        }
		return null;
    }


}
