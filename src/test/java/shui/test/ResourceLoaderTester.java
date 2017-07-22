package shui.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import junit.framework.TestCase;
import shui.utiltools.loader.ResourceLoader;

public class ResourceLoaderTester extends TestCase{
	
	public void write(InputStream inputStream,OutputStream outputStream) throws IOException{
		int length=0;
		byte[] info=new byte[1024];
		while((length=inputStream.read(info))!=-1){
			outputStream.write(info, 0, length);
		}
	}
	
	@Test
	public void testLoad() throws IOException{
		InputStream input=ResourceLoader.loadResource("classpath*:org/springframework/beans/factory/xml/spring-beans-4.3.xsd");
		write(input, new FileOutputStream("F:/c.txt"));
	}
}
