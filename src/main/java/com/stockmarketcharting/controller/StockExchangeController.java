package com.stockmarketcharting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarketcharting.dao.stockExchangeDao;
import com.stockmarketcharting.dto.stockExchangeDto;
import com.stockmarketcharting.model.StockExchange;


@RestController
public class StockExchangeController {

	@Autowired
	stockExchangeDao stockExchangeDao;
	
	Logger logger = LoggerFactory.getLogger(StockExchangeController.class);
	
	@GetMapping("/admin/stockexchanges")
	ResponseEntity<?> getStockExchanges(){
		return ResponseEntity.ok(stockExchangeDao.findAll()); 		
	}
	
	@PutMapping("/admin/stockexchanges/update")
	ResponseEntity<?> updateStockExchange(){
		//yet to implement
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/admin/stockexchanges/delete/{id}")
	ResponseEntity<?> deleteStockExchange(@PathVariable Long id){
		stockExchangeDao.deleteById(id);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/admin/stockexchanges/add")
	@Transactional
	ResponseEntity<?> addStockExchange(@Validated @RequestBody StockExchange se){
		logger.info(se.toString());
		stockExchangeDao.save(se);
		return ResponseEntity.ok(se.toString());
	}
	
	@GetMapping("admin/stockexchanges/{pattern}")
	ResponseEntity<?> getAllStockExchangeMatching(@PathVariable String pattern){
//		System.out.println(stockExchangeDao.findByStockExchangeNameIgnoreCaseContaining(pattern));
		return ResponseEntity.ok(stockExchangeDao.findByStockExchangeNameIgnoreCaseContaining(pattern));
	}
	
	@GetMapping("/admin/stockexchanges/get/{seName}")
	ResponseEntity<?> getByStockExchangeName(@PathVariable String seName){
//		System.out.println(seName);
		return ResponseEntity.ok(stockExchangeDao.getByStockExchangeName(seName)); 		
	}
}
