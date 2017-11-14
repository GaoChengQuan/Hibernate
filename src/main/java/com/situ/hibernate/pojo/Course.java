package com.situ.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

public class Course {
	private Integer id;
	private String name;
	private Integer credit;
	private Set<Banji> banjis = new HashSet<Banji>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Set<Banji> getBanjis() {
		return banjis;
	}

	public void setBanjis(Set<Banji> banjis) {
		this.banjis = banjis;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", credit=" + credit + ", banjis=" + banjis + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(11/2);
	}

}
