package com.vinay.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory sessionFactoryWithPath = null;

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static SessionFactory buildSessionFactorywithPath(String path) {
		try {
			return new Configuration().configure(path).buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static SessionFactory getSessionFactoryWithPath(String path) {
		if (path != null && sessionFactoryWithPath == null) {
			sessionFactoryWithPath = buildSessionFactorywithPath(path);
		}
		return sessionFactoryWithPath;
	}

	// Clear the caches and connection pools
	public static void shutDown() {
		getSessionFactory().close();
	}

}