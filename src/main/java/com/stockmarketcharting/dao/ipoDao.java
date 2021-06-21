package com.stockmarketcharting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmarketcharting.model.IPO;

@Repository
public interface ipoDao extends JpaRepository<IPO, Long>{
List<IPO> findAllByOrderByOpenDateTimeDesc();
}
