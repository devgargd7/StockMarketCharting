package com.stockmarketcharting.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarketcharting.model.Sector;

public interface sectorDao extends JpaRepository<Sector, Long>{
	
	public Optional<Sector> getBySectorName(String sectorName);
//	public List<Sector> findBySectorName(String sectorName);
}
