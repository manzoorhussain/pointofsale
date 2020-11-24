package com.pos.pos.handler;

import java.util.ArrayList;
import java.util.List;

import com.pos.pos.dto.CategoryDTO;
import com.pos.pos.model.CategoryModel;

public class CategoryHandler {
	
	public static List<CategoryDTO> getCategoryDTOList(Iterable<CategoryModel> catList){
		
		List<CategoryDTO> categoryDTOList=new ArrayList<CategoryDTO>();
		
		
		for(CategoryModel catModel:catList) {
			CategoryDTO categoryDTO=new CategoryDTO(catModel.getId(),catModel.getName(),catModel.getDesc(),catModel.getStatus());
			categoryDTOList.add(categoryDTO);
		}
		
		return categoryDTOList;
	}

	
	
	
}
