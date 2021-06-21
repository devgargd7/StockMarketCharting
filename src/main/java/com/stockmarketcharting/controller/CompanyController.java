package com.stockmarketcharting.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarketcharting.dao.companyDao;
import com.stockmarketcharting.dao.companyStockExchangeDao;
import com.stockmarketcharting.dao.sectorDao;
import com.stockmarketcharting.dao.stockExchangeDao;
import com.stockmarketcharting.dao.stockPriceDao;
import com.stockmarketcharting.dto.addCompanyDto;
import com.stockmarketcharting.dto.companyDto;
import com.stockmarketcharting.dto.getStockPriceDto;
import com.stockmarketcharting.dto.stockCodeDto;
import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.CompanyStockExchangeMap;
import com.stockmarketcharting.model.Sector;
import com.stockmarketcharting.model.StockExchange;
import com.stockmarketcharting.model.StockPrice;

@RestController
public class CompanyController {
	
	@Autowired
	companyDao companyDao;
	
	@Autowired
	stockExchangeDao stockExchangeDao;
	
	@Autowired
	stockPriceDao stockPriceDao;
	
	@Autowired
	sectorDao sectorDao;
//	
//	@Autowired
//	ModelMapper modelMapper;
	
	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	


	@GetMapping("admin/companies/sector/{sectorId}")
	ResponseEntity<?> getBySector(@PathVariable Long sectorId){
//		System.out.println(companyDao.findBySector(sector));
		return ResponseEntity.ok(companyDao.findBySectorId(sectorId));
	}
	
	@GetMapping("admin/companies")
	ResponseEntity<?> getAllCompanies(){
//		System.out.println(companyDao.findAll());
		return ResponseEntity.ok(companyDao.findAll());
	}
	
	@GetMapping("admin/companies/{pattern}")
	ResponseEntity<?> getAllCompaniesMatching(@PathVariable String pattern){
		System.out.println(companyDao.findByCompanyNameIgnoreCaseContaining(pattern));
		return ResponseEntity.ok(companyDao.findByCompanyNameIgnoreCaseContaining(pattern));
	}
	
	@DeleteMapping("/admin/companies/delete/{id}")
	ResponseEntity<?> deleteCompany(@PathVariable Long id){
		logger.info("delete "+id);
		companyDao.deleteById(id);
		return ResponseEntity.ok("deleted");
	}
 	
	@PutMapping("/admin/companies/update/{id}")
	ResponseEntity<?> updateCompany( @Validated @RequestBody addCompanyDto addCompanyDto, @PathVariable Long id){
//		logger.info(addCompanyDto.toString());
//		logger.info(companyDao.getById(id).toString());
		String response="";
		try{
			Company oldCompany = companyDao.getById(id);
			companyDto companyDto = addCompanyDto.getCompanyDto();
			Company company=new Company();
			company.setId(id);
			company.setSector(sectorDao.getBySectorName(companyDto.getSector()));
			company.setBoardOfDirectors(companyDto.getBoardOfDirectors().split("[,]",0));
			company.setCeo(companyDto.getCeo());
			company.setCompanyName(companyDto.getCompanyName());
			company.setDetails(companyDto.getDetails());
			company.setTurnover(companyDto.getTurnover());
			
			List<CompanyStockExchangeMap> oldStockCodes =  oldCompany.getStockCodes();
			stockCodeDto[] stockCodeDtos = addCompanyDto.getStockCodeDtos();
			List<CompanyStockExchangeMap> stockCodes = new ArrayList<>();
			for(stockCodeDto scDto:stockCodeDtos) {
				CompanyStockExchangeMap csMap= new CompanyStockExchangeMap();
				csMap.setCompanyCode(scDto.getStockCode());
				StockExchange se =  stockExchangeDao.getByStockExchangeName(scDto.getStockExchange());
				for (CompanyStockExchangeMap oldSc : oldStockCodes) {
					if(se == oldSc.getStockExchange()) csMap.setId(oldSc.getId());
				}
				csMap.setStockExchange(se);
				stockCodes.add(csMap);
				logger.info(csMap.toString());
			}
			company.setStockCodes(stockCodes);
			response=company.toString();
			
			company = companyDao.save(company);
			logger.info(company.toString());
		}catch(Exception e) {
			ResponseEntity.badRequest().body(e);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/admin/copmanies/add")
	@Transactional
	ResponseEntity<?> addCompany(@Validated @RequestBody addCompanyDto addCompanyDto) throws  Exception {
		String response="";
		logger.info(addCompanyDto.toString());
		
		//Check for company name before saving
		try {//companyDto to company
			companyDto companyDto = addCompanyDto.getCompanyDto();
			Company company=new Company();
			company.setSector(sectorDao.getBySectorName(companyDto.getSector()));
			company.setBoardOfDirectors(companyDto.getBoardOfDirectors().split("[,]",0));
			company.setCeo(companyDto.getCeo());
			company.setCompanyName(companyDto.getCompanyName());
			company.setDetails(companyDto.getDetails());
			company.setTurnover(companyDto.getTurnover());
			
			stockCodeDto[] stockCodeDtos = addCompanyDto.getStockCodeDtos();
			List<CompanyStockExchangeMap> stockCodes = new ArrayList<>();
			for(stockCodeDto scDto:stockCodeDtos) {
				CompanyStockExchangeMap csMap= new CompanyStockExchangeMap();
				csMap.setCompanyCode(scDto.getStockCode());
				csMap.setStockExchange(stockExchangeDao.getByStockExchangeName(scDto.getStockExchange()));
				stockCodes.add(csMap);
				logger.info(csMap.toString());
			}
			company.setStockCodes(stockCodes);
			response=company.toString();
			
			company = companyDao.save(company);
			logger.info(company.toString());
		}catch(Exception ex) {
			ResponseEntity.badRequest().body(ex);
			}
			
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/user/companies/stockprice")
	ResponseEntity<?> getStockPrice(@RequestBody getStockPriceDto getStockPriceDto){
		logger.info(getStockPriceDto.toString());
//		companyDao.getByCompanyName(getStockPriceDto.getCompany());
		List<StockPrice> stockPriceList = stockPriceDao.findByCompanyAndStockExchangeAndDateBetween(
				companyDao.getByCompanyName(getStockPriceDto.getCompany()),
				getStockPriceDto.getStockExchange(),
				LocalDate.parse(getStockPriceDto.getFromDate()),
				LocalDate.parse(getStockPriceDto.getToDate()));
		System.out.println(stockPriceList);
		return ResponseEntity.ok(stockPriceList);
	}
	
}
