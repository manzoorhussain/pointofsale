package com.pos.pos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.pos.model.CategoryModel;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel, String> {
	
	

}
