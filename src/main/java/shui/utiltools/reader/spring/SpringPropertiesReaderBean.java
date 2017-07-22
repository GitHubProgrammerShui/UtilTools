package shui.utiltools.reader.spring;

import shui.utiltools.reader.PropertiesReader;

public class SpringPropertiesReaderBean extends PropertiesReader{
	
	private String location;

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
		this.load(location);
	}
}
