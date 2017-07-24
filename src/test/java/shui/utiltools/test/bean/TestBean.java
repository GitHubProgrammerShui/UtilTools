package shui.utiltools.test.bean;

import org.springframework.stereotype.Component;

import shui.utiltools.reader.spring.annotation.Property;

@Component
public class TestBean{
	
	@Property(key="stringkey1")
	private String key;

	public String getKey(){
		return key;
	}
	public void setKey(String key){
		this.key=key;
	}
	
}
