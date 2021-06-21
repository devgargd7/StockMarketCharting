package com.stockmarketcharting.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.CompanyStockExchangeMap;
import com.stockmarketcharting.model.StockExchange;

public interface companyStockExchangeDao extends JpaRepository<CompanyStockExchangeMap, Long>{
	
	public CompanyStockExchangeMap getByCompanyCodeAndStockExchange(String companyCode, StockExchange stockExchange);
}
