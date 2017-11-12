package com.situ.hibernate.pojo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class StudentTest {

	@Test
	public void testSave() {
		Student student = new Student();
		student.setName("张三2222222");
		student.setAge(23);
		student.setBirthday(new Date());
		
		Session session = openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		//保存数据库
		session.save(student);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
		//sessionFactory.close();
	}

	private Session openSession() {
		//加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure();
		//创建session的工厂对象,常一个session-factory节点代表一个数据库 
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//创建Session（代表的是一次会话，就是域数据库链接的会话）
		Session session = sessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testGet() {
		//加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		//创建session的工厂对象,常一个session-factory节点代表一个数据库 
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//创建Session（代表的是一次会话，就是域数据库链接的会话）
		Session session = sessionFactory.openSession();
		//开启事务
		//Transaction transaction = session.beginTransaction();
		//保存数据库
		Student student = session.get(Student.class, 74);
		System.out.println(student);
		//提交事物
		//transaction.commit();
		//关闭
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testUpdate() {
		Student student = new Student();
		student.setId(77);
		student.setName("李四2222222");
		
		Session session = openSession();
		
		//开启事务
		Transaction transaction = session.beginTransaction();
		session.update(student);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
	}
	
	@Test
	public void testSaveOrUpdate() {
		Student student = new Student();
		student.setId(78);
		student.setName("王五dddddddd");
		
		Session session = openSession();
		
		//开启事务
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
	}
	
	@Test
	public void testHQL() {
		Student student = new Student();
		student.setId(78);
		student.setName("王五dddddddd");
		
		Session session = openSession();
		
		//开启事务
		Transaction transaction = session.beginTransaction();
		Query createQuery = session.createQuery("from Student where id=78");//查询的是 对象以及对象的属性
		List list = createQuery.list();
		System.out.println(list);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
	}

	@Test
	public void testCriteria() {
		Student student = new Student();
		student.setId(78);
		student.setName("王五dddddddd");
		
		Session session = openSession();
		
		//开启事务
		Transaction transaction = session.beginTransaction();
		Criteria createCriteria = session.createCriteria(Student.class);
		//条件
		createCriteria.add(Restrictions.eq("id", 78));
		List list = createCriteria.list();
		System.out.println(list);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
	}
	
	@Test
	public void testUUID() {
		String uuid = UUID.randomUUID().toString(); 
		System.out.println(uuid);
	}
}
