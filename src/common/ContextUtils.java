package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContextUtils {
	//double类型转换为字符串
	public static String doubleToStr(double d){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance(); 
		nf.setGroupingUsed(false); 
		return nf.format(d);
	}
	//上传文件重命名函数
	public static String getFilename(){
		Date d = new Date();
		int year = d.getYear()+1900;
		int month = d.getMonth()+1;
		int day = d.getDate();
		int hour = d.getHours();
		int minute = d.getMinutes();
		int second = d.getSeconds();
		String sdate = year+"";
		if(month<10)
			sdate += "0"+month;
		else
			sdate += month;
		if(day<10){
			sdate += "0"+day;
		}else{
			sdate += day;
		}
		if(hour<10){
			sdate += "0"+hour;
		}else{
			sdate += hour;
		}
		if(minute<10){
			sdate += "0"+minute;
		}else{
			sdate += minute;
		}
		if(second<10){
			sdate += "0"+second;
		}else{
			sdate += second;
		}
		return sdate;
	}

	//date类型转换为格式化后的字符串类型
	public static String dateToStrLong(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	} 
	//date类型转换为格式化后的字符串类型
	public static String dateToStrShort(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	} 
	//get方式提交数据，进行乱码处理
	public String toStr(String str){
		try{
			str = new String(str.getBytes("iso-8859-1"),"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
}
