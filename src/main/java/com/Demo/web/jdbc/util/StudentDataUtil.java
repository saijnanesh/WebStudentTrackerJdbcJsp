package com.Demo.web.jdbc.util;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	
	public static List<Student> getStudentRequest() {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("sai","jnanesh","saijnanesh.12345@gmail.com"));
		list.add(new Student("Srujana","Thalli","chujji.12@gmail.com"));
		list.add(new Student("chandana","proya","chandu.12345@gmail.com"));
		
		return list;
	}
}
