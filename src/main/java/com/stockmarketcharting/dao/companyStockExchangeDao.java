package com.stockmarketcharting.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.CompanyStockExchangeMap;
import com.stockmarketcharting.model.StockExchange;

public interface companyStockExchangeDao extends JpaRepository<CompanyStockExchangeMap, Long>{
	
//	@Query("update company_stockexchange c set c.company_code = ?1 Where c.company=?2 and c.stockexchange=?3")
//	void saveByCompanyandStockExchange(String companyCode, Company company, StockExchange stockExchange);
}
