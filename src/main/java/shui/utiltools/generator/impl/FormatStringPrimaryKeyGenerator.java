package shui.utiltools.generator.impl;

import java.util.ArrayList;
import java.util.List;

import shui.utiltools.generator.LengthPrimaryKeyGenerator;
import shui.utiltools.generator.PrimaryKeyGenerator;
import shui.utiltools.generator.exception.ParsePatternException;


/**
 * 按照某种规则生成一个字符串，样例如下：<br>
 * 1，被{}括起来中的内容会被按照一定规则替换掉（包括{}本身），替换方式如下：
 *     ①：{date:yyyy-MM-dd HH:mm:ss}表示该处将以日期主键生成器处理并替换，将当前时间按照
 * “date：”后面的表达式来进行格式化并生成字符串。
 *     ②：{string:15}表示该处将用字符串主键生成器处理并替换，将随机生成指定长度的字符串，字符
 * 串长度为“string：”后面给出的长度为准。
 *     ③：{int:6}表示该处将用Integer类型的数字主键生成器处理并替换，将随机生成指定位数的整数，
 * 位数为“int：”后给出的数字
 *     ④：{long:12}表示该处将用Long类型的数字主键生成器处理并替换，将随机生成指定位数的long
 * 型整数，位数为“long：”后给出的数字。
 * 
 * 2，如果在设计规则式时，需要忽略{，}这样的特殊字符，请在该字符前加一个“$”字符，如果需要匹配的
 * 就是“$”，也做同样处理。
 * 
 * 3，样例：
 *     1，规则式：{string:3}-{date:yyyyMMdd}-{int:5}，结果：REv-20170302-46635
 *     2，规则式：{string:5}-${{date:yyyy$$MM$$dd}$${int:6}，结果：f6wjp-{2017$03$02$985387
 * @author shuibaoqin
 *
 */
public class FormatStringPrimaryKeyGenerator implements PrimaryKeyGenerator<String>{

	private List<PatternStr<? extends PrimaryKeyGenerator<?>>> patternStrList=new ArrayList<>();
	
	
	@Override
	public String generatePrimaryKey() {
		StringBuilder builder=new StringBuilder();
		for (PatternStr<? extends PrimaryKeyGenerator<?>> patternStr : patternStrList) {
			if(patternStr.isPatternStr){
				builder.append(patternStr.format());
			}else{
				builder.append(patternStr.str);
			}
		}
		return new String(builder);
	}
	
	public void formatPattern(String pattern){
		StringBuilder builder=new StringBuilder();
		StringBuilder patternBuilder=new StringBuilder();
		char[] patternArr=pattern.toCharArray();
		boolean isIgnoreChar=false;
		boolean inPatternBuilder=false;
		for(char c:patternArr){
			if(isIgnoreChar){			//如果该字符被$转移字符转译，则无条件加入该字符
				if(inPatternBuilder){
					patternBuilder.append(c);
				}else{
					builder.append(c);
				}
				isIgnoreChar=false;
			}else if(c=='$'){			//如果该字符是$转义字符，则忽略之后第一个字符的含义
				isIgnoreChar=true;
				continue;
			}else{						//按照普通字符的含义处理
				if(c=='}'){
					if(inPatternBuilder){
						addPatternStr(patternBuilder);
						inPatternBuilder=false;
						patternBuilder=new StringBuilder();
					}else{
						throw new ParsePatternException("主键规则表达式有语法错误");
					}
				}else if(c=='{'){
					if(!inPatternBuilder){
						addStr(builder);
						inPatternBuilder=true;
						builder=new StringBuilder();
					}else{
						throw new ParsePatternException("主键规则表达式有语法错误");
					}
				}else{
					if(inPatternBuilder){
						patternBuilder.append(c);
					}else{
						builder.append(c);
					}
				}
			}
		}
	}
	private void addStr(StringBuilder builder){
		String str=new String(builder);
		patternStrList.add(new PatternStr<StringPrimaryKeyGenerator>(str,false,null));
	}
	private void addPatternStr(StringBuilder patternBuilder){
		String pattern=patternBuilder.toString();
		if(pattern.startsWith("date:")){
			pattern=pattern.substring(5);
			patternStrList.add(new PatternStr<DateFormatPrimaryKeyGenerator>(pattern,true,new DateFormatPrimaryKeyGenerator(pattern)));
		}else if(pattern.startsWith("string:")){
			pattern=pattern.substring(7);
			patternStrList.add(new PatternStr<StringPrimaryKeyGenerator>(pattern,true,new StringPrimaryKeyGenerator()));
		}else if(pattern.startsWith("int:")){
			pattern=pattern.substring(4);
			patternStrList.add(new PatternStr<IntegerPrimaryKeyGenerator>(pattern, true, new IntegerPrimaryKeyGenerator()));
		}else if(pattern.startsWith("long:")){
			pattern=pattern.substring(5);
			patternStrList.add(new PatternStr<LongPrimaryKeyGenerator>(pattern, true, new LongPrimaryKeyGenerator()));
		}else{
			patternStrList.add(new PatternStr<StringPrimaryKeyGenerator>(pattern,false,null));
		}
	}
	
}

class PatternStr<T extends PrimaryKeyGenerator<?>>{
	String str;
	int num=0;
	boolean isPatternStr;
	T generator;
	private LengthPrimaryKeyGenerator<?> lengthGenerator;
	
	public PatternStr(String str, boolean isPatternStr, T generator) {
		if(generator instanceof LengthPrimaryKeyGenerator<?>){
			num=Integer.parseInt(str);
		}
		this.str = str;
		this.isPatternStr = isPatternStr;
		this.generator = generator;
	}
	public PatternStr() {
	}

	public String format(){
		if(num<=0){
			return generator.generatePrimaryKey().toString();
		}else{
			lengthGenerator=(LengthPrimaryKeyGenerator<?>) generator;
			return lengthGenerator.generatePrimaryKey(num).toString();
		}
	}
}