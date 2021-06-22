package com.stockmarketcharting.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long>{

	@Query
	public UserEntity findByUsername(String username);
	
	@Query
	public UserEntity findByEmail(String email);
	
}
