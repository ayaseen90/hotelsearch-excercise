package com.expedia.excercise.hotelsearch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

}
