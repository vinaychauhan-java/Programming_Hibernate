package com.vinay.oneToOne;

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

		Session session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/oneToOne/hibernate.cfg.xml")
				.openSession();
		session.beginTransaction();
		Stock stock1 = new Stock();
		stock1.setStockCode("101");
		stock1.setStockName("JavaBooks");
		StockDetails stockDetails1 = new StockDetails();
		stockDetails1.setCompName("A-Press");
		stockDetails1.setRemarks("Very Good Books");
		stockDetails1.setStock(stock1);
		stock1.setStockDetails(stockDetails1);
		session.save(stock1);

		Stock stock2 = new Stock();
		stock2.setStockCode("102");
		stock2.setStockName("HibernateBooks");
		StockDetails stockDetails2 = new StockDetails();
		stockDetails2.setCompName("Orielly");
		stockDetails2.setRemarks("Awesome Books");
		stockDetails2.setStock(stock2);
		stock2.setStockDetails(stockDetails2);
		session.save(stock2);

		Stock stock3 = new Stock();
		stock3.setStockCode("103");
		stock3.setStockName("SpringeBooks");
		StockDetails stockDetails3 = new StockDetails();
		stockDetails3.setCompName("Orielly");
		stockDetails3.setRemarks("Awesome Books");
		stockDetails3.setStock(stock3);
		stock3.setStockDetails(stockDetails3);
		session.save(stock3);

		session.getTransaction().commit();
		session.close();

		// Deleting the Record-Set
		session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/oneToOne/hibernate.cfg.xml").openSession();
		session.beginTransaction();
		Stock deleteStock = session.get(Stock.class, new Integer(3));
		// Stock deleteStock = session.load(Stock.class, new Integer(3));
		session.delete(deleteStock);
		session.getTransaction().commit();
		session.close();

		// Extracting the Record-Set
		session = HibernateUtil.getSessionFactoryWithPath("/com/vinay/oneToOne/hibernate.cfg.xml").openSession();
		session.beginTransaction();
		// The difference between both methods (session.get & session.load) lies in
		// return value "if the identifier does not exist in database”. In case of get()
		// method you will get return value as NULL if identifier is absent; But in case
		// of load() method, you will get a runtime exception something like :
		// Exception in thread "main" org.hibernate.ObjectNotFoundException
		Stock stockData = session.get(Stock.class, new Integer(1));
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
