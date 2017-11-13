package com.situ.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class HQLTest {
	@Test
	public void testBasic() {
		//1、HQL语句
		String hql = "from Student";
		//2、根据hql语句创建查询对象
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery(hql);
		//3、根据查询对象获得结果
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	public void testCondition() {
		//1、HQL语句
		String hql = "from Student where id=?";
		//2、根据hql语句创建查询对象
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery(hql);
		//3、设置参数
		query.setParameter(0, 74);
		//4、根据查询对象获得结果
		Student student = (Student) query.uniqueResult();
		System.out.println(student);
	}
	
	@Test
	public void testPage() {
		//1、HQL语句
		String hql = "from Student";
		//2、根据hql语句创建查询对象
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery(hql);
		//3、设置分页信息 limit ?,?
		query.setFirstResult(2);
		query.setMaxResults(2);
		//4、根据查询对象获得结果
		List list = query.list();
		System.out.println(list);
	}
}
