package shui.utiltools.test.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import shui.utiltools.reader.spring.annotation.Property;

@Component
public class TestBean{
	
	@Property(key="innerList")
	private List<Object> key;

	public List<Object> getKey(){
		return key;
	}
	public void setKey(List<Object> key){
		this.key=key;
	}
	
}
