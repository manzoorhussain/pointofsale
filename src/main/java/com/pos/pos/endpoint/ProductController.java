package com.pos.pos.endpoint;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pos.pos.dto.ProductDTO;
import com.pos.pos.model.CategoryModel;
import com.pos.pos.model.ProductModel;
import com.pos.pos.model.WsResponse;
import com.pos.pos.pk.ProductPK;
import com.pos.pos.service.ProductService;
import com.pos.pos.util.CommanUtil;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	 public WsResponse getAllProducts() {
		 return productService.getAllProducts();
	  }
	
	
	@GetMapping("/all-products")
	 public WsResponse getAllProducsAndCategory() {
		 return productService.getAllProductsWithCategory();
	  }
	
	@PostMapping(path="/add")
	 public WsResponse addProduct(@RequestParam Map<String, String> formData ,@RequestParam("productImage") MultipartFile productImage) throws IOException {
		
		String categoryId=formData.get("categoryId");
		String productId=formData.get("productId");
		String name=formData.get("name");
		String desc=formData.get("desc");
		String status=formData.get("status");
		String price=formData.get("price");
	
		
		
	
		ProductPK prodPk=new ProductPK(categoryId,productId);
		ProductModel productModel=new ProductModel(prodPk,name,desc,status,price,productImage.getBytes());
		
		 return productService.addProduct(productModel);
	  }

	@DeleteMapping(path="delete/{categoryId}/{productId}")
    public WsResponse deleteProuduct(@PathVariable String categoryId,@PathVariable String productId) {
		System.out.println("categoryId--"+categoryId+"=="+productId);
		
		ProductPK productPK=new ProductPK(categoryId,productId);
        
        return productService.deleteProduct(productPK);
    }
	
	@GetMapping(path="/get/{categoryId}/{productId}")
	 public WsResponse getById(@PathVariable String categoryId,@PathVariable String productId){
		ProductPK productPK=new ProductPK(categoryId,productId);
        System.out.println("Back End--"+categoryId+"--"+productId);
		
	    return productService.getProductById(productPK);
	  }

	@GetMapping("/all-bill-product")
	 public WsResponse getAllProducsForBills() {
		 return productService.getAllProductsForBills();
	  }
	Integer pageNo=new Integer(0);
	@GetMapping("/product-page")
   // public ResponseEntity<List<ProductModel>> getAllProductPagination(@RequestParam(defaultValue = "0") Integer pageNo,  @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "productPK") String sortBy) 
    public WsResponse getAllProductPagination( @RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "productPK") String sortBy,@RequestParam String extra)
    
	{
		System.out.println("Extra--"+extra);
		if(extra.equals("0")) {
			pageNo=0;
		}
		else if(extra.equals("1")) {
			pageNo+=1;
		}
		else {
			pageNo-=1;
		}
		
		if(pageNo<0) {
			pageNo=0;
		}
		
        List<ProductDTO> list = productService.getAllProductPagination(pageNo, pageSize, sortBy);
        WsResponse wsResponse=new WsResponse(""+pageNo,"fetch succesfully",list);
        return  wsResponse;
    }
}
