package com.vinay.oneToMany;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "Stock", uniqueConstraints = { @UniqueConstraint(columnNames = { "STOCK_CODE", "STOCK_NAME" }) })
public class Stock implements java.io.Serializable {

	private Integer stockId;
	private String stockCode;
	private String stockName;
	private Set<StockDetails> stockDetails = new HashSet<StockDetails>(0);

	public Stock() {
	}

	public Stock(String stockCode, String stockName) {
		this.stockCode = stockCode;
		this.stockName = stockName;
	}

	/**
	 * Identity sequencing uses special IDENTITY columns in the database to allow
	 * the database to automatically assign an id to the object when its row is
	 * inserted. Identity columns are supported in many databases, such as MySQL,
	 * DB2, SQL Server, Sybase and Postgres. Oracle does not support IDENTITY
	 * columns but they can be simulated through using sequence objects and
	 * triggers.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Column(name = "STOCK_NAME", nullable = false, length = 20)
	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
	public Set<StockDetails> getStockDetails() {
		return this.stockDetails;
	}

	public void setStockDetails(Set<StockDetails> stockDetails) {
		this.stockDetails = stockDetails;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockCode=" + stockCode + ", stockName=" + stockName + "]";
	}

}
