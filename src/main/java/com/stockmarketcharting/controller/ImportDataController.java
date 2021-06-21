package com.stockmarketcharting.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarketcharting.dto.ExcelData;
import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.CompanyStockExchangeMap;
import com.stockmarketcharting.model.StockExchange;
import com.stockmarketcharting.model.StockPrice;


@RestController
public class ImportDataController {
	
	@Autowired
	com.stockmarketcharting.dao.companyDao companyDao;
	
	@Autowired
	com.stockmarketcharting.dao.stockExchangeDao stockExchangeDao;
	
	@Autowired
	com.stockmarketcharting.dao.stockPriceDao stockPriceDao;
	
	@Autowired
	com.stockmarketcharting.dao.companyStockExchangeDao companyStockExchangeDao;
	
	
	Logger logger = LoggerFactory.getLogger(ImportDataController.class);
	
	@PostMapping("/admin/import")
	ResponseEntity<List<Boolean>> ImportData(@Validated @RequestBody ExcelData[] excelData) throws Exception {
        List<Boolean> responseList = new ArrayList<>();
		for(ExcelData e: excelData){
        	try{
			logger.info(e.toString());
        		StockExchange se = stockExchangeDao.getByStockExchangeName(e.getStockExchange());
        		logger.info(se.toString());
        		CompanyStockExchangeMap csMap =companyStockExchangeDao.getByCompanyCodeAndStockExchange(e.getCompanyCode(),
        				se);
        		
        		Company company = csMap.getCompany();
        		StockPrice sPrice = new StockPrice();
        		
        		sPrice.setCompany(company);
        		sPrice.setCurrentPrice(e.getPricePerShare());
        		sPrice.setStockExchange(se);
        		sPrice.setDate(e.getDate());
        		sPrice.setTime(e.getTime());
        		
            	stockPriceDao.save(sPrice);
        		logger.info(sPrice.toString());
        		responseList.add(true);
        	}catch (Exception ex) {
//        		ex.printStackTrace();
        		responseList.add(false);
        		}
        }
        return ResponseEntity.ok(responseList);
    }
	
	
}
