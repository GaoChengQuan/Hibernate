package com.situ.hibernate.search;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class SearchDemo {

	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		// 排序
		String sql1 = " from Student order by id asc ";
		String sql2 = " from Student order by id desc ";
		Query query = session.createQuery(sql2);
		List list = query.list();
		System.out.println(list);

		tx.commit();
	}

	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " select count(*) from Student ";
		String sql2 = " select sum(id) from Student ";
		String sql3 = " select avg(id) from Student ";
		String sql4 = " select max(id) from Student ";
		Query query = session.createQuery(sql4);
		// Integer count = (Integer) query.uniqueResult();
		Number count = (Number) query.uniqueResult();
		System.out.println(count);
		tx.commit();
	}

	// 投影查询
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " select id,name from Student ";
		Query query = session.createQuery(sql1);
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tx.commit();
	}

	// 投影查询
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " from  Student ";
		Query query = session.createQuery(sql1);
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		tx.commit();
	}
	
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " select new Student(id,name) from  Student ";
		Query query = session.createQuery(sql1);
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		tx.commit();
	}
	
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " from Student s inner join s.banji ";
		//SELECT * 
		//FROM student AS s INNER JOIN banji AS b
		//ON s.banji_id = b.id;
		Query query = session.createQuery(sql1);
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
			//[Student [id=1, name=李四, age=null, banji=Banji [id=1, name=Java1707]], Banji [id=1, name=Java1707]]
		}
		tx.commit();
	}
	
	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		String sql1 = " from Student s inner join fetch s.banji ";
		//SELECT * 
		//FROM student AS s INNER JOIN banji AS b
		//ON s.banji_id = b.id;
		Query query = session.createQuery(sql1);
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
			//Student [id=1, name=李四, banji=Banji [id=1, name=Java1707]]
		}
		tx.commit();
	}
	
	@Test
	public void test8() {
		//web层
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		detachedCriteria.add(Restrictions.eq("id", 1));
		
		//dao层
		Session session = HibernateUtils.openSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Student student = (Student) criteria.uniqueResult();
		System.out.println(student);
		Transaction tx = session.beginTransaction();
		
	}
}
