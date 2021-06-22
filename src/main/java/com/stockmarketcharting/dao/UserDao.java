package com.stockmarketcharting.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.Authuser;

@Repository
public interface UserDao extends JpaRepository<Authuser,Long>{

	@Query
	public Authuser findByUsername(String username);
	
	@Query
	public Authuser findByEmail(String email);
	
}
