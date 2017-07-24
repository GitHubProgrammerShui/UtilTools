package shui.test;

import java.lang.reflect.Field;

import org.junit.Test;

import shui.utiltools.reader.spring.annotation.Property;

public class CommonTester{
	@Test
	public void testReflect() throws ClassNotFoundException{
		Class<?> beanClass=ClassLoader.getSystemClassLoader().loadClass("shui.utiltools.test.bean.TestBean");
		System.out.println(beanClass.isLocalClass());
		Field[] fields=beanClass.getDeclaredFields();
		System.out.println(fields.length);
		for(Field field:fields){
			System.out.println(field.getName());
			System.out.println(field.getAnnotation(Property.class).getClass().getName());
		}
	}
	
	@Test
	public void testClassLoader() throws ClassNotFoundException{
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		Class<?> cls=loader.loadClass("shui.utiltools.generator.PrimaryKeyGenerator");
		System.out.println(cls.getName());
	}
}
