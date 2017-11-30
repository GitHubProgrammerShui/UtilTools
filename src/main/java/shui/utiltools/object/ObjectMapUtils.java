package shui.utiltools.object;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import shui.utiltools.exception.UnsupportedClassException;

public class ObjectMapUtils {

	public final static int OPTION_ONLY = 0;
	public final static int OPTION_RECURSIVE = 1;

	public static Map<String, ?> toMap(Object object) {
		return null;
	}

	public static <T> T getObject(Map<String, ?> map, Class<T> objClass) {
		return null;
	}
}

class ObjectMapper{
	
	public Map<String,?> toMap(Object object,int option){
		Class objClass=object.getClass();
		
		//不支持object为集合或数组或注解
		if(objClass.isArray()||objClass.isAnnotation()||object instanceof Collection||objClass.isEnum()||objClass.isInterface()){
			throw new UnsupportedClassException("对象不能是：数组，注解，继承Collection的集合，枚举，接口");
		}
		
		Map<String,?> objMap=new HashMap<>();
		for(Field field:objClass.getFields()){
			
		}
		return objMap;
	}
	private Collection<?> getCollection(Field field,Object object,int option) throws IllegalArgumentException, IllegalAccessException{
		return null;
	}
}