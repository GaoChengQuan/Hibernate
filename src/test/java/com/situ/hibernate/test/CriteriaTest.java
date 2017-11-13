package com.situ.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class CriteriaTest {
	@Test
	public void testBasic() {
		Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(Student.class);
		List list = criteria.list();
		System.out.println(list);
	}
	
	@Test
	public void testCondition() {
		Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(Student.class);
		//添加查询参数
		// 	==					eq
		//	>					gt
		//	>= 					ge(greater than or equal)
		//  <					lt
		//  <=					le
		//	!=					ne
		//	in					in
		// between and			between
		// like					like
		// is not null			isNotNull
		// is null				idNull
		// or 					or
		// and 					and
		criteria.add(Restrictions.eq("id", 74));
		Student student = (Student) criteria.uniqueResult();
		System.out.println(student);
	}
	
	@Test
	public void testPage() {
		Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(Student.class);
		//设置分页信息 limit ?,?
		criteria.setFirstResult(0);
		criteria.setMaxResults(2);
		List list = criteria.list();
		System.out.println(list);
	}
	
	@Test
	public void testCount() {
		Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(Student.class);
		//设置查询的聚合函数：总行数
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		System.out.println(count);
	}
}
