package shui.utiltools.generator.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import shui.utiltools.generator.PrimaryKeyGenerator;


/**
 * 以Date为依据生成主键字符串，需要提供一个格式化表达式
 * @author shuibaoqin
 *
 */
public class DateFormatPrimaryKeyGenerator implements PrimaryKeyGenerator<String>{
	
	private String pattern;
	private SimpleDateFormat format;
	
	public DateFormatPrimaryKeyGenerator(String format){
		this.format=new SimpleDateFormat(format);
	}
	public DateFormatPrimaryKeyGenerator() {
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public void refresh(){
		format=new SimpleDateFormat(pattern);
	}
	public void refresh(String pattern){
		format=new SimpleDateFormat(pattern);
	}
	
	@Override
	public String generatePrimaryKey() {
		if(format==null){
			format=new SimpleDateFormat(pattern);
		}
		return format.format(new Date());
	}
	
	public String generatePrimaryKey(Date date){
		return format.format(date);
	}
}
