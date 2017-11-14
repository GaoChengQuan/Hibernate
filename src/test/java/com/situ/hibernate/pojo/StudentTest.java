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
		Session session = openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		Student student = new Student();//没有id，没有和Session建立关联：临时状态
		student.setId(999);
		student.setName("王五");//临时状态
		//保存数据库
		session.save(student);//有id,在session缓存中:持久化状态
		//提交事物
		transaction.commit();
		//关闭
		session.close();//有id,没有在session缓存中:游离状态
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
		Transaction transaction = session.beginTransaction();
		//保存数据库
		Student student1 = session.get(Student.class, 74);
		Student student2 = session.get(Student.class, 75);
		Student student3 = session.get(Student.class, 74);
		Student student4 = session.get(Student.class, 74);
		System.out.println(student1 == student3);
		System.out.println(student1 == student4);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testGet2() {
		//加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		//创建session的工厂对象,常一个session-factory节点代表一个数据库 
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//创建Session（代表的是一次会话，就是域数据库链接的会话）
		Session session = sessionFactory.openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		//保存数据库
		Student student = session.get(Student.class, 74);
		student.setName("Java1707");//持久化状态的任何变化都会自动同步到数据库中
		//提交事物
		transaction.commit();
		//关闭
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testUpdate() {
		Session session = openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		Student student = new Student();
		student.setId(77);
		student.setName("李四6666");
		session.update(student);
		student.setName("李四7777");
	    session.update(student);
		//提交事物
		transaction.commit();
		//关闭
		session.close();
	}
	
	@Test
	public void testUpdate1() {
		Session session = openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, 77);//持久化状态
		student.setName("王五6666");
		//session.update(student);
		student.setName("王五7777");
	    //session.update(student);
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
		Query createQuery = session.createQuery("from com.situ.hibernate.pojo.Student where id=78");//查询的是 对象以及对象的属性
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
