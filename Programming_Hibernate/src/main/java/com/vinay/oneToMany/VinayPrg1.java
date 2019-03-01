package com.vinay.oneToMany;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.hibernate.Session;

import com.vinay.util.HibernateUtil;

/**
 * In a One-To-One, bidirectional relationship the parent points to the child
 * and the child points to the parent:
 * 
 * stockDetails.setStock(stock); stock.setStockDetail(stockDetails);
 * 
 * Then we invoke the save on the parent(Employee) object & we are good to go!!
 */
public class VinayPrg1 {

	public static void main(String[] args) throws SQLException {
		openServerModeInBrowser();

		Session session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/oneToMany/hibernate.cfg.xml")
				.openSession();
		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("101");
		stock.setStockName("JavaBooks");

		StockDetails stockDetails1 = new StockDetails();
		stockDetails1.setStock(stock);
		stockDetails1.setCompName("APress");
		stockDetails1.setRemarks("Very Good Books");
		stock.getStockDetails().add(stockDetails1);
		StockDetails stockDetails2 = new StockDetails();
		stockDetails2.setStock(stock);
		stockDetails2.setCompName("Orielly");
		stockDetails2.setRemarks("Awesome Books");
		stock.getStockDetails().add(stockDetails2);

		session.save(stock);
		session.getTransaction().commit();
		session.close();

		// Extracting the Record-Set
		session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/oneToMany/hibernate.cfg.xml").openSession();
		session.beginTransaction();
		// The difference between both methods (session.get & session.load) lies in
		// return value "if the identifier does not exist in database”. In case of get()
		// method you will get return value as NULL if identifier is absent; But in case
		// of load() method, you will get a runtime exception something like :
		// Exception in thread "main" org.hibernate.ObjectNotFoundException
		Stock stockData = session.get(com.vinay.oneToMany.Stock.class, new Integer(1));
		System.out.println("Stock Information  : " + stockData);
		System.out.println("Stock Details Info : " + stockData.getStockDetails());
		session.close();
	}

	private static void openServerModeInBrowser() throws SQLException {
		Server server = Server.createTcpServer().start();
		System.out.println("H2 Server started and connection is open at URL - ");
		System.out.println("jdbc:h2:" + server.getURL() + "/mem:testDB");
	}

}
