package com.expedia.excercise.hotelsearch;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.lang3.tuple.Pair;

import com.expedia.excercise.hotelsearch.search.client.SearchManager;

public class Utilities {
	
	public static String generateSetterMathodName(String fieldName) {
		if(fieldName == null || fieldName.length() < 1 || !Character.isJavaIdentifierStart(fieldName.charAt(0))) {
			throw new IllegalArgumentException("field name is invalid");
		}
		 
		return new StringBuffer("set")
				.append(Character.toUpperCase(fieldName.charAt(0)))
				.append(fieldName.substring(1)).toString();
	}
	
	public static void invokePublicVoidMethod(Object obj, String methodName, Object[] parameters) {
		
		try {
			Class<?>[] parameterTypes;
			if(parameters != null) {
				parameterTypes = new Class[parameters.length];
				for(int i = 0; i<parameters.length; i++) {
					parameterTypes[i] = parameters[i].getClass();
				}
			} else {
				parameterTypes = new Class[0];
			}
			
			Method m = obj.getClass().getMethod(methodName, parameterTypes);
			
			if(m == null) {
				throw new IllegalArgumentException("Method " + methodName + " was not found in the object from class " + obj.getClass().getName());
			}
			try {
				m.invoke(obj, parameters);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
		} catch(Exception ex) {
			throw new RuntimeException("Could not invoke the method " + methodName, ex);
		}
		
	}
	
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Loads a config file from one of the following sources respectively:
	 * 1- environment variable
	 * 2- properties file/key
	 * 3- default value supplied
	 * @return
	 */
	public static String loadConfig(String environmentVarName, Pair<String, String> propertiesFileAndKey, String defaultValue) {
		
		/*
		 * Load From Environment variable
		 */
		String value = null;
		if(!isNullOrEmpty(environmentVarName)) {
			value = System.getenv(environmentVarName);
		}
		
		if(!isNullOrEmpty(value)) {
			return value;
		}
		
		/*
		 * load from properties file
		 */
		
		value = loadConfigFromPropertiesFile(propertiesFileAndKey);
		
		if(!isNullOrEmpty(value)) {
			return value;
		}
		
		return defaultValue;
	}
	
	public static String loadConfigFromPropertiesFile( Pair<String, String> propertiesFileAndKey) {
		
		if(propertiesFileAndKey == null) {
			return null;
		}
		String fileName = propertiesFileAndKey.getLeft();
		String keyName = propertiesFileAndKey.getRight();
		
		Object propValue = null;
		try {
			InputStream configFileInputStream = SearchManager.class.getResourceAsStream(fileName);
			Properties props = new Properties();
			props.load(configFileInputStream);
			propValue = props.get(keyName);
		} catch (IOException e) {
			//ignore
		}
		if(propValue != null) {
			return propValue.toString();
		}
		return null;
	}

}
