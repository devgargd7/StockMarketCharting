package com.stockmarketcharting.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.StockExchange;

@Repository
public interface stockExchangeDao extends JpaRepository<StockExchange, Long>{
//	@Query
	public Optional<StockExchange> getByStockExchangeName(String stockExchangeName);
}
