package com.webmail.config;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 配置文件(global.properties)读取类
 * 
 * @author yulilong
 */
public final class GlobalPropertie {
	private static Logger logger=Logger.getLogger(GlobalPropertie.class);
	private static final Properties properties=new Properties();
	static{
		try {
			properties.load(GlobalPropertie.class.getClassLoader().getResourceAsStream("global.properties"));
		} catch (IOException e) {
			logger.error("配置文件读取异常！", e);
		}
	}
	public static String get(String key){
		return properties.getProperty(key);
	}
	public static String get(String key, String defaultValue){
		return properties.getProperty(key, defaultValue);
	}
	public static Integer getInteger(String key){
		String value=properties.getProperty(key);
		return value!=null?Integer.parseInt(value):null;
	}
	public static Integer getInteger(String key, String defaultValue){
		String value=properties.getProperty(key, defaultValue);
		return value!=null?Integer.parseInt(value):null;
	}
}
