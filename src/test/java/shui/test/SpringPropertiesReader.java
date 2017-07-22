package shui.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext-test.xml")
public class SpringPropertiesReader{
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testContext(){
//		SpringPropertiesReaderBean reader=context.getBean(SpringPropertiesReaderBean.class);
		System.out.println("测试");
	}
}
