package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;


public class RenamePolicy implements FileRenamePolicy{

	public File rename(File filename) {
		
		String head = "";
		String body = "";
		//获取系统当前日期
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		//获取文件的后缀标识
		int index = filename.getName().lastIndexOf(".");
		if(index!=-1){
			head = simple.format(date);
			body = filename.getName().substring(index);
			System.out.println("文件后缀名:"+body);
		}else{
			head = date.getTime()+"";
		}
		//生成随机数
		Random rand = new Random();
		String fileName = head + Math.abs(rand.nextInt(9000)+1000)+body;
		filename = new File(filename.getParent(),fileName);
		return filename;
	}

}
