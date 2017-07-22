package shui.utiltools.generator.impl;

import shui.utiltools.generator.LengthPrimaryKeyGenerator;

/**
 * 
 * 随机字符串主键生成器，生成随机字符串
 * @author shuibaoqin
 *
 */
public class StringPrimaryKeyGenerator implements LengthPrimaryKeyGenerator<String>{
	
	private String charCollection="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private int size=62;
	
	public StringPrimaryKeyGenerator(String charCollection){
		this.charCollection=charCollection;
		this.size=charCollection.length();
	}
	public StringPrimaryKeyGenerator() {
	}
	
	@Override
	public String generatePrimaryKey() {
		return this.generatePrimaryKey(15);
	}
	
	@Override
	public String generatePrimaryKey(int length) {
		char[] strArr=new char[length];
		for(int i=0;i<length;i++){
			strArr[i]=charCollection.charAt((int)(Math.random()*size));
		}
		return new String(strArr);
	}
	
	public void setCharCollection(String charCollection){
		this.charCollection=charCollection;
		this.size=charCollection.length();
	}
}
