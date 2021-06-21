package com.stockmarketcharting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stockmarketcharting.dao.sectorDao;
import com.stockmarketcharting.dto.sectorDto;
import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.Sector;

@RestController
public class SectorController {
	
	@Autowired
	sectorDao sectorDao;
	
	@Autowired
	com.stockmarketcharting.dao.companyDao companyDao;
	
	@GetMapping("user/sector/{sectorName}")
	ResponseEntity<?> getCompaniesBySector(@PathVariable String sectorName){
		Sector sector = sectorDao.getBySectorName(sectorName);
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<Sector> request = new HttpEntity<>(sector);
//		List<Company> companyList = new ArrayList<>();
//		ResponseEntity<?> companyList  =  restTemplate
//				  .exchange("http://localhost:8082/admin/companies/sector/"+sector.getId(), HttpMethod.GET, request,List.class);
//		System.out.println(companyList);
		return ResponseEntity.ok(companyDao.findBySectorId(sector.getId()));
	}
	
	@PostMapping("admin/sectors/add")
	ResponseEntity<?> addSector(@RequestBody sectorDto sectorDto){
		Sector sector = new  Sector();
		sector.setSectorName(sectorDto.getSectorName());
		sector.setBrief(sectorDto.getBrief());
		sectorDao.save(sector);
		return ResponseEntity.ok(sector);
	}
	
	@GetMapping("admin/sectors")
	ResponseEntity<?> getAllSectors(){
		return ResponseEntity.ok(sectorDao.findAll());
	}
	
//	@PostMapping("admin/sectors/add")
//	ResponseEntity<?> addSectors(@ModelAttribute("sector_name") String sectorName){ //
//		Sector sector = new Sector();
//		sector.setSectorName(sectorName);
//		logger.info(sectorName);
//		sectorDao.save(sector);
//		return ResponseEntity.ok(sector.toString());
//	}
//	
}
