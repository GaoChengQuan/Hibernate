package com.situ.hibernate.one2many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.situ.hibernate.pojo.Banji;
import com.situ.hibernate.pojo.Course;
import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class DemoMany2Many {

	// 保存班级以及班级下面学生
	@Test
	public void test1() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		
		Banji banji1 = new Banji("Java111");
		Banji banji2 = new Banji("Java222");
		
		Course course1 = new Course();
		course1.setName("java");
		Course course2 = new Course();
		course2.setName("UI");
		
		banji1.getCourses().add(course1);
		banji2.getCourses().add(course2);
		
		course1.getBanjis().add(banji1);
		course2.getBanjis().add(banji2);
		
		session.save(banji1);
		session.save(banji2);
		session.save(course1);
		session.save(course2);
		
		// 提交事物
		transaction.commit();
	}

	
	@Test
	public void test2() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		
		Banji banji = session.get(Banji.class, 11);
		Course course = new Course();
		course.setName("H5111");
		banji.getCourses().add(course);
		
		//session.save(course);
		
		// 提交事物
		transaction.commit();
	}


	@Test
	public void test3() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		Banji banji = session.get(Banji.class, 11);
		Course course1 = session.get(Course.class, 5);
		Course course2 = session.get(Course.class, 6);
		//将course1和course2从banji里面删除
		banji.getCourses().remove(course1);
		banji.getCourses().remove(course2);
		
		// 提交事物
		transaction.commit();
	}
}
