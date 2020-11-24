package com.pos.pos.endpoint;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.model.CategoryModel;

import com.pos.pos.model.WsResponse;
import com.pos.pos.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/all-category")
	 public WsResponse getAllCategories() {
	
	    return categoryService.getAllCategories();
	  }
	
	
	
	@PostMapping(path="/add")
	 public WsResponse addCategories(@RequestBody CategoryModel categoryModel) {
		
		String catId=categoryModel.getId();
		String name=categoryModel.getName();
		String desc=categoryModel.getDesc();
		String status=categoryModel.getStatus();
		
	
		CategoryModel category=new CategoryModel(catId,name,desc,status);
		
	    return categoryService.addCategory(category);
	  }


	@DeleteMapping(path="delete/{id}")
    public WsResponse deleteCategories(@PathVariable("id") String id) {
        
        return categoryService.deleteCategory(id);
    }
	
	@GetMapping("/get/{id}")
	 public WsResponse getById(@PathVariable("id") String id){
		
	    return categoryService.getCategoryById(id);
	  }
}
