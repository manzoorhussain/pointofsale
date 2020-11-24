package com.pos.pos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.pos.pos.model.ProductModel;
import com.pos.pos.pk.ProductPK;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, ProductPK>,PagingAndSortingRepository<ProductModel, ProductPK> {
	
}
	

