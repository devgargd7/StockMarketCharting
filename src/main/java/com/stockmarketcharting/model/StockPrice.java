package com.stockmarketcharting.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stockprice")
public class StockPrice {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(as = StockExchange.class)
	private StockExchange stockExchange;

	private Integer currentPrice;
	
	private LocalDate date;
	private LocalTime time;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public StockExchange getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}
	public Integer getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "StockPrice [company=" + company + ", stockExchange=" + stockExchange + ", currentPrice=" + currentPrice
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
//	public StockPrice(com.example.stockmarketapp.model.Company company, StockExchange stockExchange,
//			Integer currentPrice) {
//		super();
//		this.company = company;
//		this.stockExchange = stockExchange;
//		this.currentPrice = currentPrice;
//		this.Date = LocalDate.now();
//		this.Time = LocalTime.now();
//	}
	
}

//1.4. Stock Price details Excel:
//1. Company Code – to which Company this Stock Price Info belongs to
//2. Stock Exchange – the Stock Price of the Company in this Stock Exchange
//3. Current Price – Stock Price
//4. Date – Date of the Stock Price
//5. Time – Stock Price at this Specific time