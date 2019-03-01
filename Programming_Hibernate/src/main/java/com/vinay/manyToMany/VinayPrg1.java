package com.vinay.manyToMany;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.h2.tools.Server;
import org.hibernate.Session;

import com.vinay.util.HibernateUtil;

public class VinayPrg1 {

	public static void main(String[] args) throws SQLException {
		openServerModeInBrowser();

		Session session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/manyToMany/hibernate.cfg.xml")
				.openSession();
		session.beginTransaction();
		Stock stock = new Stock();
		stock.setStockCode("101");
		stock.setStockName("Google");
		Category category1 = new Category("TECHNOLOGY", "INNOVATION COMPANY");
		Category category2 = new Category("EDUCATION", "E-LEARNING COMPANY");
		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);
		stock.setCategories(categories);
		session.save(stock);
		session.getTransaction().commit();
		session.close();

		// Extracting the Record-Set
		session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/manyToMany/hibernate.cfg.xml").openSession();
		session.beginTransaction();
		// The difference between both methods (session.get & session.load) lies in
		// return value "if the identifier does not exist in database”. In case of get()
		// method you will get return value as NULL if identifier is absent; But in case
		// of load() method, you will get a runtime exception something like :
		// Exception in thread "main" org.hibernate.ObjectNotFoundException
		Stock stockData = session.get(Stock.class, new Integer(1));
		System.out.println("Stock Information  : " + stockData);
		session.close();
	}

	private static void openServerModeInBrowser() throws SQLException {
		Server server = Server.createTcpServer().start();
		System.out.println("H2 Server started and connection is open at URL - ");
		System.out.println("jdbc:h2:" + server.getURL() + "/mem:testDB");
	}

}
