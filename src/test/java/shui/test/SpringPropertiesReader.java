package shui.test;

import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shui.utiltools.test.bean.TestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext-test.xml")
public class SpringPropertiesReader{
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testContext() throws ClassNotFoundException{
	}
	
	@Test
	public void testInject(){
		TestBean testBean=context.getBean(TestBean.class);
		System.out.println(testBean.getKey());
	}
	
}
