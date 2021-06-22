package com.stockmarketcharting.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.Appuser;

@Repository
public interface UserDao extends JpaRepository<Appuser,Long>{

	@Query
	public Appuser findByUsername(String username);
	
	@Query
	public Appuser findByEmail(String email);
	
}
