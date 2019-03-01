package com.vinay.oneToOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity
@Table(name = "Stock_Dtls")
public class StockDetails implements java.io.Serializable {

	private Integer stockId;
	private Stock stock;
	private String compName;
	private String remarks;

	public StockDetails() {
	}

	public StockDetails(Integer stockId, Stock stock, String compName, String remarks) {
		super();
		this.stockId = stockId;
		this.stock = stock;
		this.compName = compName;
		this.remarks = remarks;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "stock"))
	@GeneratedValue(generator = "generator")
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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

	@Override
	public String toString() {
		return "StockDetails [stockId=" + stockId + ", compName=" + compName + ", remarks=" + remarks + "]";
	}

}