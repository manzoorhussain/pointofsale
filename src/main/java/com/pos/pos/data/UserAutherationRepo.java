package com.pos.pos.data;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pos.pos.model.UserAutherationModel;

@Repository
public interface UserAutherationRepo extends JpaRepository<UserAutherationModel,Long> {
	
	@Transactional
    @Modifying  
    @Query(value = "DELETE FROM UserAutherationModel e WHERE e.userCode = :userCode")       // it will delete all the record with specific name
    int deleteByUserCode(@Param("userCode") String userCode);

}
