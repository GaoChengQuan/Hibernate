package com.situ.hibernate.one2many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.situ.hibernate.pojo.Banji;
import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class Demo {

	// 保存班级以及班级下面学生
	@Test
	public void test1() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		Banji banji = new Banji("Java1707");
		Student student1 = new Student();
		student1.setName("张三");
		Student student2 = new Student();
		student2.setName("李四");
		// 表达关系
		// 表达一对多关系：一个班级下面有多个学生
		banji.getStudents().add(student1);
		banji.getStudents().add(student2);
		// 表达多对一关系：这个学生属于哪个班级
		student1.setBanji(banji);
		student2.setBanji(banji);

		session.save(banji);
		session.save(student1);
		session.save(student2);

		// 提交事物
		transaction.commit();
	}

	// 位班级增加学生
	@Test
	public void test2() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		Banji banji = session.get(Banji.class, 1);
		Student student = new Student();
		student.setName("王五");
		// 把学生添加到班级里面
		banji.getStudents().add(student);
		student.setBanji(banji);

		session.save(banji);
		session.save(student);

		// 提交事物
		transaction.commit();
	}

	// 删除班级中某个学生
	@Test
	public void test3() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		Banji banji = session.get(Banji.class, 1);
		Student student = session.get(Student.class, 2);
		// 删除班级中某个学生
		banji.getStudents().remove(student);
		student.setBanji(null);

		//session.save(banji);
		//session.save(student);

		// 提交事物
		transaction.commit();
	}
	
	@Test
	public void set() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Banji banji = (Banji) session.get(Banji.class, 1);
		System.out.println(banji.getName());
		System.out.println("------");
		System.out.println(banji.getStudents().size());  //  SQL
		
		session.getTransaction().commit();
		session.close();
	}
}
