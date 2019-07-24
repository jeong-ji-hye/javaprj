package com.newlecture.web.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class File {
	@Override
	public String toString() {
		return "File [name=" + name + ", type=" + type + ", size=" + size + ", createDate=" + createDate + "]";
	}
	private boolean isFile;
	private String name;
	private String type;
	private long size;
	private Date createDate;
	
	public File(java.io.File file){
		name = file.getName();
		size = file.length();
		
		if(file.isDirectory())
			type = "dir";
		else if(name.lastIndexOf(".")==-1)
			type = "noext";
		else		
			type = name.substring(name.lastIndexOf("."));
		
		//createDate = //file.toPath()
		try {
			//���� ���� �ð� �̾Ƴ���
			BasicFileAttributes fattr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			
			createDate = new Date(fattr.creationTime().toMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean getIsFile() {
		return isFile;
	}



	public void fromFile(java.io.File file) {
		//io.file�� �о�� ä��� �Ұ���??
		name = file.getName();
	}
	//io������ ������ ���� ���ϴ� �����͸� �̰� ���̽����� �ٲܰž�
	public String toJSON() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return JSONParser.toJSON(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
