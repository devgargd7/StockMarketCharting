package com.stockmarketcharting.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.AppUser;

@Repository
public interface UserDao extends JpaRepository<AppUser,Long>{

	@Query
	public AppUser findByUsername(String username);
	
	@Query
	public AppUser findByEmail(String email);
	
}
