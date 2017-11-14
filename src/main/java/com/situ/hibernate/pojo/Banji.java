package com.situ.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

public class Banji {
	private int id;
	private String name;
	// 使用set集合,表达一对多关系
	private Set<Student> students = new HashSet<Student>();

	private Set<Course> courses = new HashSet<Course>();

	public Banji() {
		super();
	}

	public Banji(String name) {
		super();
		this.name = name;
	}

	public Banji(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Banji [id=" + id + ", name=" + name + ", students=" + students + "]";
	}

}
