package shui.utiltools.reader.spring;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import shui.utiltools.exception.PropertiesInjectException;
import shui.utiltools.reader.PropertiesReader;

public class BeanPropertiesScanner{
	
	@Autowired
	private ApplicationContext context;
	
	private String basePackage;
	private PropertiesReader propertiesReader;

	public String getBasePackage(){
		return basePackage;
	}
	public void setBasePackage(String basePackage){
		this.basePackage = basePackage;
	}
	public PropertiesReader getPropertiesReader(){
		return propertiesReader;
	}
	public void setPropertiesReader(PropertiesReader propertiesReader){
		this.propertiesReader = propertiesReader;
	}
	/*
	 * 下面两个方法配合使用，扫描指定包下所有的类，包括子包中的类也一并扫描
	 */
	private List<Class<?>> scanClasses() throws URISyntaxException, ClassNotFoundException{
		List<Class<?>> classList=new ArrayList<>();
		ClassLoader classLoader=ClassLoader.getSystemClassLoader();
		String basePackagePath = StringUtils.replaceChars(basePackage, ".",File.separator);
		File basePackageFile=FileUtils.toFile(ClassLoader.getSystemResource(basePackagePath));
		if(basePackageFile!=null&&basePackageFile.isDirectory()){
			Collection<File> classFileList=FileUtils.listFiles(basePackageFile,null,true);
			for(File classFile:classFileList){
				Class<?> loadClass = classLoader.loadClass(basePackage+this.getClassName(basePackageFile,classFile));
				if(loadClass.isLocalClass()){
					classList.add(loadClass);
				}
			}
		}
		return classList;
	}
	private String getClassName(File base,File classFile){
		String packagePath=StringUtils.removeStart(classFile.getAbsolutePath(),base.getAbsolutePath());
		packagePath=StringUtils.removeEnd(packagePath,".class");
		return StringUtils.replaceChars(packagePath,File.separatorChar,'.');
	}
	
	public void inject(){
		List<Class<?>> classList;
		try {
			classList = this.scanClasses();
		}catch(ClassNotFoundException | URISyntaxException e) {
			throw new PropertiesInjectException("找不到指定的类",e);
		}
		for (Class<?> cls:classList){
		}
	}
}
