package com.stockmarketcharting.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;

import com.stockmarketcharting.dao.companyDao;
import com.stockmarketcharting.dao.ipoDao;
import com.stockmarketcharting.dao.stockExchangeDao;
import com.stockmarketcharting.dto.addCompanyDto;
import com.stockmarketcharting.dto.companyDto;
import com.stockmarketcharting.dto.ipoDto;
import com.stockmarketcharting.dto.stockCodeDto;
import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.CompanyStockExchangeMap;
import com.stockmarketcharting.model.IPO;
import com.stockmarketcharting.model.StockExchange;

@RestController
public class IpoController {

	@Autowired
	companyDao companyDao;
	
	@Autowired
	stockExchangeDao stockExchangeDao;
	
	@Autowired
	ipoDao ipoDao;
	
	Logger logger = LoggerFactory.getLogger(IpoController.class);
	
	@GetMapping("/admin/ipos")
	ResponseEntity<List<?>> getAllIpos(){
//		System.out.println(ipoDao.findAll());
		return ResponseEntity.ok(ipoDao.findAll());
	}
	@GetMapping("/user/ipos")
	ResponseEntity<List<?>> getAllIposUser(){
//		System.out.println(ipoDao.findAll());
		return ResponseEntity.ok(ipoDao.findAllByOrderByOpenDateTimeDesc());
	}
	
	@DeleteMapping("/admin/ipos/detele/{id}")
	ResponseEntity<?> deleteIpo(@PathVariable Long id){

		ipoDao.deleteById(id);
		return ResponseEntity.ok("deleted");
	}
 	
	@PutMapping("/admin/ipos/update")
	ResponseEntity<?> updateIpo(@RequestBody String CompanyName){
//		companyDao.getByCompanyName(CompanyName).ifPresent(companyDao::save);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/admin/ipos/add")
	@Transactional
	ResponseEntity<?> addIpo(@Validated @RequestBody ipoDto ipoDto) throws  Exception {
		String response="";
		logger.info(ipoDto.toString());
		
		//Check for ipo name before saving
		try {
			IPO ipo = new IPO();
			ipo.setCompany(companyDao.getByCompanyName(ipoDto.getCompanyName()));
			ipo.setStockExchanges(getStockExchanges(ipoDto.getStockExchanges()));
			ipo.setPricePerShare(ipoDto.getPricePerShare());
			ipo.setTotalNumberOfShare(ipoDto.getTotalNumberOfShares());
			ipo.setOpenDateTime(LocalDateTime.parse(ipoDto.getOpenDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			ipo.setRemarks(ipoDto.getRemarks());
			logger.info(ipo.toString());
			response = ipo.toString();
			ipoDao.save(ipo);
		}catch(Exception ex) {
			ResponseEntity.badRequest().body(ex);
			}
			
	    return ResponseEntity.ok(response);
	}
	
	List<StockExchange> getStockExchanges(String se){
		List<StockExchange> listStockExchanges=new ArrayList<>();
		if(se==null) return listStockExchanges;
		for (String seName : se.split("[,]", 0)) 
			stockExchangeDao.getByStockExchangeName(seName).ifPresent(listStockExchanges::add);
		logger.info(listStockExchanges.toString());
		return listStockExchanges;
	}
	
}
