package shui.utiltools.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 这个类用于加载一些资源，包括位于jar包内的资源
 * <br>
 * <br>
 * 对于该类的设置，如果要加载的文件位于类路径下，可以使用classpath:作为前缀，
 * 表示从类路径开始，如果要加载的文件位于jar包中，则可以使用classpath*:作为前缀，
 * 表示从jar包中搜索资源
 * @author shuibaoqin
 *
 */
public class ResourceLoader{
	
	private static String getClassName(File base,File classFile){
		String packagePath=StringUtils.removeStart(classFile.getAbsolutePath(),base.getAbsolutePath());
		packagePath=StringUtils.removeEnd(packagePath,".class");
		return StringUtils.replaceChars(packagePath,File.separatorChar,'.');
	}
	
	/**
	 * 根据所传入的File对象，搜索对应的文件并加载，然后以流的形式返回
	 * @param file
	 * @return
	 */
	public static InputStream loadResource(File file){
		if(file==null){
			throw new NullPointerException("file参数不能为空");
		}
		try{
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ResourceLoaderException("file所指向的资源不存在",e);
		}
	}
	
	/**
	 * 从给定的路径中加载资源，如果资源位于类路径下，可以使用classpath:来标识
	 * ，如果资源位于jar包中，可以使用classpath*:来标识，否则按照普通路径来处理
	 * @param location 给定的路径
	 * @return
	 */
	public static InputStream loadResource(String location){
		if(location.startsWith("classpath:")){
			location=location.substring(10);
			return ResourceLoader.class.getClassLoader().getResourceAsStream(location);
		}else if(location.startsWith("classpath*:")){
			location=location.substring(11);
			return ClassLoader.getSystemResourceAsStream(location);
		}else{
			try {
				return new FileInputStream(location);
			} catch (FileNotFoundException e) {
				throw new ResourceLoaderException("找不到指定的路径",e);
			}
		}
	}
	
	public static <T> List<Class<T>> scanClasses(String basePackage){
		List<Class<T>> classList=new ArrayList<>();
		ClassLoader classLoader=ClassLoader.getSystemClassLoader();
		String basePackagePath = StringUtils.replaceChars(basePackage, ".",File.separator);
		File basePackageFile=FileUtils.toFile(ClassLoader.getSystemResource(basePackagePath));
		if(basePackageFile!=null&&basePackageFile.isDirectory()){
			try {
				Collection<File> classFileList=FileUtils.listFiles(basePackageFile,null,true);
				for(File classFile:classFileList){
					Class loadClass = classLoader.loadClass(basePackage+ResourceLoader.getClassName(basePackageFile,classFile));
					if(loadClass.isLocalClass()){
						classList.add(loadClass);
					}
				}
			} catch (ClassNotFoundException e){
				throw new ResourceLoaderException("没有找到指定的类",e);
			}
		}
		return classList;
	}
}
