package com.stockmarketcharting.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.Company;
import com.stockmarketcharting.model.Sector;


@Repository
public interface companyDao extends JpaRepository<Company,Long>{

//	public Optional<Company> getByStockCode(String companyCode);
	public Optional<Company> getByCompanyName(String companyName);
	public List<Company> findByCompanyNameIgnoreCaseContaining(String companyName);
	public List<Company> findBySector(Sector sector);
	
	@Query(value="SELECT * FROM stockmarket.company c WHERE c.sector_id = 1",  nativeQuery = true)
	public List<Company> findBySectorId(Long sectorId);
}
