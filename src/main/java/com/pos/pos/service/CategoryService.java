package com.pos.pos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.pos.data.CategoryRepo;
import com.pos.pos.dto.CategoryDTO;
import com.pos.pos.handler.CategoryHandler;
import com.pos.pos.model.CategoryModel;
import com.pos.pos.model.WsResponse;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo catRepo;
	
	public WsResponse getAllCategories() {
		
		Iterable<CategoryModel> catList=catRepo.findAll();
		
		List<CategoryDTO> catDTOList=CategoryHandler.getCategoryDTOList(catList);
		
		WsResponse wsResponse=null;
		if(catDTOList.size()==0) {
			wsResponse=new WsResponse("01", "Record Not Found", catDTOList);
		}else {
			wsResponse=new WsResponse("00", "Record fetch Successfully", catDTOList);
		}
		
		return wsResponse;
		
	}
	
	
	public WsResponse addCategory(CategoryModel categoryModel) {
		
WsResponse wsResponse=null;
		
		if(categoryModel.getId().isEmpty() || categoryModel.getId().length()==0) {
			wsResponse=new WsResponse("03", "Category ID cannot be empty", null);
		}
		else if(categoryModel.getName().isEmpty() || categoryModel.getName().length()==0) {
			wsResponse=new WsResponse("03", "Category Name cannot be empty", null);
		}
		else {
			CategoryModel catModel=catRepo.save(categoryModel);
			
			if(catModel==null) {
				wsResponse=new WsResponse("01", "Record Not Save", catModel);
			}else {
				wsResponse=new WsResponse("00", "Record  save Successfully", catModel);
			}
		}
		
		
		
		return wsResponse;
		
	}

	
	public WsResponse deleteCategory(String catId) {
		catRepo.deleteById(catId);
		
		WsResponse wsResponse=null;
		try {
			wsResponse=new WsResponse("00", "Record delete Successfully", catId);
		
		}catch(Exception e) {
			wsResponse=new WsResponse("01", "Record not delete", catId);
		}
		
		
	return wsResponse;
		
	}
	
	
	public WsResponse getCategoryById(String catId) {
		Optional<CategoryModel> catModel=catRepo.findById(catId);
		
		
		WsResponse wsResponse=null;
		
		
		if(catModel.isPresent()) {
			wsResponse=new WsResponse("00", "Record  fetch Successfully", catModel);
		}else {
			wsResponse=new WsResponse("01", "Record  not found", catModel);
		}
		
		return wsResponse;
		
	
	}
}
