package com.newlecture.web.dao.java;
import java.lang.reflect.InvocationTargetException;

import com.newlecture.web.entity.File;

public class Program {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		java.io.File directory = new java.io.File("D:\\tools");
		
		java.io.File[] files = directory.listFiles();
		
		File f1 = new File(files[0]);	
		System.out.println(f1.toString());
		
		File f2 = new File(files[1]);	
		System.out.println(f2.toString());
		
		for(java.io.File file : files) {
			File f = new File(file);
			System.out.println(f.toJSON());
		}
	}

}
