package shui.test;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import shui.utiltools.reader.PropertiesReader;

public class PropertiesReaderTester extends TestCase{
	
	@Test
	public void testGetList(){
		PropertiesReader reader=new PropertiesReader();
		reader.load("classpath:prop.xml");
		assertTrue(reader.containsKey("listkey2"));
		List<Object> list=reader.getList("listkey2");
		for(Object obj:list){
			System.out.println(obj);
		}
	}
	
	@Test
	public void testGetListValue(){
		PropertiesReader reader=new PropertiesReader("classpath:prop.xml");
		String value=reader.getListValue("innerList",1,0,0);
		System.out.println(value);
	}
	
	@Test
	public void testGetMapValue(){
		PropertiesReader reader=new PropertiesReader("classpath:prop.xml");
		String value=reader.getMapValue("innerMap","map1","map10","map100");
		System.out.println(value);
	}
	
	@Test
	public void testGetCollectionValue(){
		PropertiesReader reader=new PropertiesReader("classpath:prop.xml");
		String value=reader.getCollectionValue("collection",1,"coll11","coll111");
		System.out.println(value);
	}
	
}
