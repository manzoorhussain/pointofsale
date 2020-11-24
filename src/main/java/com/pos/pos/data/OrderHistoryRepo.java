package com.pos.pos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pos.pos.model.OrderHistoryModel;

@Repository
public interface OrderHistoryRepo  extends JpaRepository<OrderHistoryModel, Long>{
	
	
	 
    @Query(value = "SELECT ORD_CODE FROM ORDER_HISTORY WHERE SR_NO=(SELECT MAX(SR_NO) FROM  ORDER_HISTORY)",nativeQuery = true) 
	String getMaxOrderCode();

}
