package com.stockmarketcharting.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.StockExchange;
import com.stockmarketcharting.model.StockPrice;


public interface stockPriceDao extends JpaRepository<StockPrice, Long> {
	public List<StockPrice> findByCompanyAndStockExchangeAndDateBetween(Company company,StockExchange se,LocalDate date1, LocalDate date2);
}
