package com.pos.pos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.pos.pos.data.CategoryRepo;
import com.pos.pos.data.ProductRepo;

import com.pos.pos.dto.ProductDTO;

import com.pos.pos.handler.ProductHandler;
import com.pos.pos.model.CategoryModel;
import com.pos.pos.model.ProductModel;
import com.pos.pos.model.WsResponse;
import com.pos.pos.pk.ProductPK;

@Service
public class ProductService {
	@Autowired
	ProductRepo prodRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	public WsResponse getAllProducts() {
		
		
		Iterable<ProductModel> prodList=prodRepo.findAll();
		
		List<ProductDTO> prodDTOList=ProductHandler.getProductDTOList(prodList);
		
		WsResponse wsResponse=null;
		if(prodDTOList.size()==0) {
			wsResponse=new WsResponse("01", "Record Not Found", prodDTOList);
		}else {
			wsResponse=new WsResponse("00", "Record fetch Successfully", prodDTOList);
		}
		
		return wsResponse;
		
	}
	
	
	public WsResponse addProduct(ProductModel productModel) {
		WsResponse wsResponse=null;
		
		if(productModel.getProductPK().getCategoryId().isEmpty() ||productModel.getProductPK().getCategoryId().equals("-1") || productModel.getProductPK().getCategoryId().length()==0 ) {
			wsResponse=new WsResponse("03", "Category ID can not be empty",null);
		}
		else if(productModel.getProductPK().getProductId().isEmpty() || productModel.getProductPK().getProductId().length()==0) {
			wsResponse=new WsResponse("03", "Product ID can not be empty",null);
		}
		else if(productModel.getPrice().isEmpty() || productModel.getPrice().length()==0) {
			wsResponse=new WsResponse("03", "Product Price can not be empty",null);
		}
		else if(productModel.getName().isEmpty() || productModel.getName().length()==0) {
			wsResponse=new WsResponse("03", "Product Name can not be empty",null);
		}
		
		else {
		ProductModel prodModel=prodRepo.save(productModel);
		System.out.println("prodModel--"+prodModel);
		
		if(prodModel==null) {
			wsResponse=new WsResponse("01", "Record Not Save", prodModel);
		}else {
			wsResponse=new WsResponse("00", "Record  save Successfully", prodModel);
		}
		
		}
		
		
		
		
		return wsResponse;
		
	}
	
	
	public WsResponse deleteProduct(ProductPK productPK) {
		prodRepo.deleteById(productPK);
		
		WsResponse wsResponse=null;
		try {
			wsResponse=new WsResponse("00", "Record delete Successfully", productPK);
		
		}catch(Exception e) {
			wsResponse=new WsResponse("01", "Record not delete", productPK);
		}
		
		
	return wsResponse;
		
	}
	
	
	public WsResponse getProductById(ProductPK productPK) {
		Optional<ProductModel> prodModel=prodRepo.findById(productPK);
		
		
		WsResponse wsResponse=null;
		
		
		if(prodModel.isPresent()) {
			wsResponse=new WsResponse("00", "Record  fetch Successfully", prodModel);
		}else {
			wsResponse=new WsResponse("01", "Record  not found", prodModel);
		}
		
		return wsResponse;
		
	
	}
	
	
	public WsResponse getAllProductsWithCategory() {
		
		
		Iterable<ProductModel> prodList=prodRepo.findAll();
		
		
		Iterable<CategoryModel> catList=categoryRepo.findAll();
		
		Map<String, String> catMap=new HashMap<String, String>();
		
		for(CategoryModel catModel:catList) {
			catMap.put(catModel.getId(), catModel.getName());
		}
		
		List<ProductDTO> productDTOList=ProductHandler.getProductDTOListWithCategory(prodList,catMap);
		
		WsResponse wsResponse=null;
	
		if(productDTOList.size()==0) {
			wsResponse=new WsResponse("01", "Record Not Found", productDTOList);
		}else {
			wsResponse=new WsResponse("00", "Record fetch Successfully", productDTOList);
		}
		
		return wsResponse;
		
		
	}
	
public WsResponse getAllProductsForBills() {
		
		
		Iterable<ProductModel> prodList=prodRepo.findAll();
		
		
		List<List<ProductDTO>> productDTOMap=ProductHandler.getProductForBill(prodList);
		
		WsResponse wsResponse=null;
	
		if(productDTOMap.size()==0) {
			wsResponse=new WsResponse("01", "Record Not Found", productDTOMap);
		}else {
			wsResponse=new WsResponse("00", "Record fetch Successfully", productDTOMap);
		}
		
		
		return wsResponse;
		
		
	}
public List<ProductDTO> getAllProductPagination(Integer pageNo, Integer pageSize, String sortBy)
{
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<ProductModel> pagedResult = prodRepo.findAll(paging);
     
    if(pagedResult.hasContent()) {
    	
    	
    	
    	Iterable<CategoryModel> catList=categoryRepo.findAll();
		
		Map<String, String> catMap=new HashMap<String, String>();
		
		for(CategoryModel catModel:catList) {
			catMap.put(catModel.getId(), catModel.getName());
		}
    	
		
		Iterable<ProductModel> prodList=pagedResult.getContent();
    	List<ProductDTO> prodDTOList=ProductHandler.getProductDTOListWithCategory(prodList,catMap);
    	
        return prodDTOList;
    } else {
        return new ArrayList<ProductDTO>();
    }
}

}
