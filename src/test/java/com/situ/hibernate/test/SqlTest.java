package com.situ.hibernate.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import com.situ.hibernate.pojo.Student;
import com.situ.hibernate.util.HibernateUtils;

public class SqlTest {
	//返回数组List
	@Test
	public void testBasic() {
		//1、Sql语句
		String sql = "select * from student";
		//2、根据sql语句创建查询对象
		Session session = HibernateUtils.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		//3、根据查询对象获得结果
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			/*for (Object object : objects) {
				System.out.println(object);
			}*/
			System.out.println(Arrays.toString(objects));;
		}
	}
	
	//返回对象List
	@Test
	public void testBasic1() {
		//1、Sql语句
		String sql = "select * from student";
		//2、根据sql语句创建查询对象
		Session session = HibernateUtils.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		//将结果集封装到哪个对象
		query.addEntity(Student.class);
		//3、根据查询对象获得结果
		List<Student> list = query.list();
		System.out.println(list);
	}
	
	@Test
	public void testCondition() {
		//1、Sql语句
		String sql = "select * from student where stu_id=?";
		//2、根据Sql语句创建查询对象
		Session session = HibernateUtils.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		//3、设置参数
		query.setParameter(0, 74);
		query.addEntity(Student.class);
		//4、根据查询对象获得结果
		Student student = (Student) query.uniqueResult();
		System.out.println(student);
	}
	
	@Test
	public void testPage() {
		//1、sql语句
		String hql = "select * from student limit ?,?";
		//2、根据sql语句创建查询对象
		Session session = HibernateUtils.openSession();
		SQLQuery query = session.createSQLQuery(hql);
		//3、设置分页信息 limit ?,?
		query.setParameter(0, 2);
		query.setParameter(1, 2);
		query.addEntity(Student.class);
		//4、根据查询对象获得结果
		List<Student> list = query.list();
		System.out.println(list);
	}
}
