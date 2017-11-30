package shui.utiltools.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具
 * @author 水保勤
 *
 */
public class DateUtil {
	
	/**
	 * 日期截断工具，可以将指定的日期截断为当月（或当年，当天等等）的第一天（月，小时等等）<br>
	 * ，例如，<br>
	 * 2017年12月8日22时13分54秒547毫秒按月截断：2017年12月1日0时0分0秒0毫秒<br>
	 * 2017年12月8日22时13分54秒547毫秒按分截断：2017年12月8日22时13分0秒0毫秒<br>
	 * @param date 日期
	 * @param range 截断范围
	 * @return
	 */
	public static Date truncateDate(Date date,TimeUnit range){
		Date resultDate=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		switch (range) {
			case YEAR:
				calendar.set(Calendar.MONTH,Calendar.JANUARY);
			case MONTH:
				calendar.set(Calendar.DAY_OF_MONTH,1);
			case DAY:
				calendar.set(Calendar.HOUR_OF_DAY,0);
			case HOUR:
				calendar.set(Calendar.MINUTE,0);
			case MINUTE:
				calendar.set(Calendar.SECOND, 0);
			case SECOND:
				calendar.set(Calendar.MILLISECOND, 0);
				break;
			case WEEK:
				calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				calendar.set(Calendar.HOUR_OF_DAY,0);
				calendar.set(Calendar.MINUTE,0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				break;
		}
		resultDate=calendar.getTime();
		return resultDate;
	}
	
	/**
	 * 日期填充工具，可以将指定日期填充为当月（年，小时等等）的最后一天（月，分等等）<br>
	 * 例如：<br>
	 * 2017年5月25日6时12分24秒258毫秒 按月填充：2017年5月31日23时59分59秒999毫秒<br>
	 * 2017年5月25日6时12分24秒258毫秒 按日填充：2017年5月25日23时59分59秒999毫秒<br>
	 * @param date 日期
	 * @param range 填充范围
	 * @return
	 */
	public static Date fillDate(Date date,TimeUnit range){
		Date resultDate=null;
		Calendar calendar=Calendar.getInstance();
		resultDate=truncateDate(date, range);
		calendar.setTime(resultDate);
		switch (range) {
			case YEAR:
				calendar.add(Calendar.YEAR,1);
				break;
			case MONTH:
				calendar.add(Calendar.MONTH,1);
				break;
			case DAY:
				calendar.add(Calendar.DAY_OF_MONTH,1);
				break;
			case HOUR:
				calendar.add(Calendar.HOUR_OF_DAY,1);
				break;
			case MINUTE:
				calendar.add(Calendar.MINUTE,1);
				break;
			case SECOND:
				calendar.add(Calendar.SECOND,1);
				break;
			case WEEK:
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		calendar.add(Calendar.MILLISECOND,-1);
		resultDate=calendar.getTime();
		return resultDate;
	}
}
