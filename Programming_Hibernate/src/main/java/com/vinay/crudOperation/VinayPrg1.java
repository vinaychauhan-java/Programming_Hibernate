package com.vinay.crudOperation;

import java.sql.SQLException;
import java.util.Date;

import org.h2.tools.Server;
import org.hibernate.Session;

import com.vinay.util.HibernateUtil;

public class VinayPrg1 {
	public static void main(String[] args) throws SQLException {
		openServerModeInBrowser();

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Student student1 = new Student();
		student1.setRollNo(100);
		student1.setName("Vipin Chauhan");
		student1.setCreatedDate(new Date());
		session.save(student1);
		Student student2 = new Student();
		student2.setRollNo(101);
		student2.setName("Vinay Chauhan");
		student2.setCreatedDate(new Date());
		session.save(student2);
		session.getTransaction().commit();
		session.close();

		// Updating the Record-Set
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Student student3 = new Student();
		student3.setRollNo(101);
		student3.setName("Ananya Chauhan");
		student3.setCreatedDate(new Date());
		session.saveOrUpdate(student3);
		session.getTransaction().commit();
		session.close();

		// Deleting the Record-Set
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Student student4 = new Student();
		student4.setRollNo(100);
		session.delete(student4);
		session.getTransaction().commit();
		session.close();

	}

	private static void openServerModeInBrowser() throws SQLException {
		Server server = Server.createTcpServer().start();
		System.out.println("H2 Server started and connection is open at URL - ");
		System.out.println("jdbc:h2:" + server.getURL() + "/mem:testDB");
	}
}
