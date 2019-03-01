package com.vinay.oneToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Stock_Dtls")
public class StockDetails implements java.io.Serializable {

	private Integer stockDetailsId;
	private Stock stock;
	private String compName;
	private String remarks;

	public StockDetails() {
	}

	public StockDetails(Integer stockDetailsId, String compName, String remarks) {
		super();
		this.stockDetailsId = stockDetailsId;
		this.compName = compName;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STOCK_DTLS_ID", unique = true, nullable = false)
	public Integer getStockDetailsId() {
		return stockDetailsId;
	}

	public void setStockDetailsId(Integer stockDetailsId) {
		this.stockDetailsId = stockDetailsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Column(name = "COMP_NAME", nullable = false, length = 100)
	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@Column(name = "REMARKS", nullable = false)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}