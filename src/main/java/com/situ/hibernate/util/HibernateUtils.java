package com.situ.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	static {
		// 1 创建,调用空参构造
		Configuration conf = new Configuration().configure();
		// 2 根据配置信息,创建 SessionFactory对象
		sessionFactory = conf.buildSessionFactory();
	}

	// 获得session => 获得全新session
	public static Session openSession() {
		//SessionFactory这样创建，多个用户访问会创建过个SessionFactory
		// 1 创建,调用空参构造
		//Configuration conf = new Configuration().configure();
		// 2 根据配置信息,创建 SessionFactory对象
		//sessionFactory = conf.buildSessionFactory();
		
		// 3 获得session
		Session session = sessionFactory.openSession();

		return session;
	}

	// 获得session => 获得与线程绑定的session
	public static Session getCurrentSession() {
		// 3 获得session
		Session session = sessionFactory.getCurrentSession();

		return session;
	}
}
