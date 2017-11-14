package com.situ.hibernate.one2many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.situ.hibernate.pojo.Banji;
import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class Demo2 {

	// 保存班级以及班级下面学生
	@Test
	public void test1() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.开启事务
		Transaction transaction = session.beginTransaction();
		Banji banji = new Banji("Java1707");
		Student student1 = new Student();
		student1.setName("张三11");
		Student student2 = new Student();
		student2.setName("李四11");
		// 表达关系
		// 表达一对多关系：一个班级下面有多个学生
		banji.getStudents().add(student1);
		banji.getStudents().add(student2);
		// 表达多对一关系：这个学生属于哪个班级
		student1.setBanji(banji);
		student2.setBanji(banji);

		session.save(banji);
		//session.save(student1);
		//session.save(student2);

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
		Banji banji = session.get(Banji.class, 5);
		//没有加：cascade="delete"，只是把banji删除而且解除这个关系
		session.delete(banji);

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
}
