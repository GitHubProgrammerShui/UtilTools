package shui.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import shui.utiltools.date.DateUtil;
import shui.utiltools.date.TruncateOrFillRange;

public class DateTester{
	
	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	
	@Test
	public void testDateTrunc(){
		Date date=new Date();
		Date resultDate=DateUtil.truncateDate(date,TruncateOrFillRange.DAY);
		System.out.println(dateFormat.format(resultDate));
	}
}
