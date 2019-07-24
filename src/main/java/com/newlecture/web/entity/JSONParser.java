package com.newlecture.web.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONParser {

	public static String toJSON(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> clazz = object.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		int num=1;
		for(Field field : fields) {
			String name = field.getName();
			String n1 = name.substring(0, 1).toUpperCase();
			String n2 = name.substring(1);
			String getterName = "get" + n1 + n2;
			
			Method method = clazz.getDeclaredMethod(getterName, new Class[] {});
			String value = String.valueOf(method.invoke(object));
			
			json.append(String.format("\"%s\":\"%s\"", name, value));
			
			if(num < fields.length)
				json.append(",");
			
			num++;
		}
		json.append("}");
		
		return json.toString();
	}
	
}
