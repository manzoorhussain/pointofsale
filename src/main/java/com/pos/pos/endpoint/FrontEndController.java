package com.pos.pos.endpoint;


import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pos.pos.dto.BillDTO;
import com.pos.pos.dto.UserDTO;
import com.pos.pos.handler.RequestHandler;
import com.pos.pos.model.Bill;
import com.pos.pos.model.LoginUserModel;
import com.pos.pos.model.WsResponse;
import com.pos.pos.util.GeneratePdfReport;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
//@CrossOrigin(origins = "http://springbootappnew-env.eba-hnmh9pyq.us-east-2.elasticbeanstalk.com", maxAge = 3600)
@Controller
public class FrontEndController {
	
	
	@Value("${endpoint-contextpath}")
	 private String endPointContext;
	
	
	@GetMapping("/login")
	 public String login() {
		
		 return "login";
	  }

	@RequestMapping(value="/index",method={RequestMethod.GET, RequestMethod.POST})
	 public ModelAndView  index(@RequestParam String userId,@RequestParam String password) {
		
		String responseJsp="";
		
		String uri=endPointContext+"/api/user/login";
		System.out.println("URL--"+uri);
		LoginUserModel loginUser=new LoginUserModel(userId,password);
		WsResponse wsRespone=RequestHandler.postMethodWithOutHeader(uri, loginUser);
		
		ModelAndView modelAndView=null;
		
		String responseCode=wsRespone.getCode();
		if("00".equals(responseCode)) {
			responseJsp="index";
			modelAndView=new ModelAndView(responseJsp,"response",wsRespone);
		}else {
			responseJsp="login";
			modelAndView=new ModelAndView(responseJsp,"response",wsRespone);
			
		}
		
		
		
		  return modelAndView;
	  }
	
	
	@RequestMapping(value="/logout",method={RequestMethod.GET})
	 public String logOut(@RequestParam String userCode) {
		
		final String uri = endPointContext+"/api/user/delete-usercode/{userCode}";
		 Map<String, String> params=new HashMap<String, String>();
		 params.put("userCode", userCode);
		
		WsResponse wsRespone=RequestHandler.deleteMethodCall(uri,params);
		
		 return "login";
	  }
	
	
	@RequestMapping(value="/product-panel",method={RequestMethod.GET})
	 public String productPanel() {
			return "product-panel";
	  }
	
	@RequestMapping(value="/category-panel",method={RequestMethod.GET})
	 public String CategoryPanel() {
			return "product-category";
	  }
	
	
	@RequestMapping(value="/get-all-category",method={RequestMethod.GET})
	 public ModelAndView getAllCategory(String token) {
		
		final String uri = endPointContext+"/api/categories/all-category";
		WsResponse wsResponse=RequestHandler.getMethodWithHeader(uri, token);
		System.out.println("wsResponse--"+wsResponse);
		
		
	    String responseJsp="product-category";
		ModelAndView modelAndView=new ModelAndView(responseJsp,"response",wsResponse.getResponseObject());
		return  modelAndView;
	  }
	
	
	@RequestMapping(value="/add-category",method={RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody WsResponse  addCategory(@RequestParam String id,@RequestParam String name,@RequestParam String desc,@RequestParam String status,String userToken,ModelMap model) {
		
		
		
		final String uri = endPointContext+"/api/categories/add";
		
		
		Map<String, Object> requestBody=new HashMap<String, Object>();
		requestBody.put("id", id);
		requestBody.put("name", name);
		requestBody.put("desc", desc);
		requestBody.put("status", status);
		
		
		String token=userToken;
		WsResponse wsRespone=RequestHandler.postMethodCall(uri, token, requestBody);
		
		  return wsRespone;
	  }
	

	@RequestMapping(value="/edit-category",method=RequestMethod.GET)
	 public @ResponseBody WsResponse getCategoryById(@RequestParam String id,String token) {
		
		final String uri = endPointContext+"/api/categories/get/{id}";
		
		Map < String, String > params=new HashMap<String, String>();
		params.put("id", id);
		
		WsResponse wsResponse=RequestHandler.getHandlerByParamter(uri,token,params);
	
	
		return  wsResponse;
	  }
	
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	 public @ResponseBody WsResponse deleteCategory(@RequestParam String id,String token) {
		
		final String uri = endPointContext+"/api/categories/delete/{id}";
		
		Map < String, String > params=new HashMap<String, String>();
		params.put("id", id);
		
		WsResponse wsResponse=RequestHandler.deleteHandlerByParamter(uri,token,params);
	
		return  wsResponse;
	
	 	
	  }
	
	@RequestMapping(value="/bill",method={RequestMethod.GET})
	 public ModelAndView bill(ModelMap model,String token) {
		
		 String responseJsp="bill-panel";
		    
		 
		 final String uriForList = endPointContext+"/api/product/all-bill-product";
			WsResponse wsResponseForList=RequestHandler.getMethodWithHeader(uriForList, token);
			
		   
	
			Map<String, Object> map=null;
		    JSONObject obj = new JSONObject();
		    obj.put("HELLO", wsResponseForList.getResponseObject());
		    try {
				 map = new ObjectMapper().readValue(obj.toString(), new TypeReference<Map<String, Object>>() {});
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		   
		    
		   
		    Map<String,Object> responseMap=new HashMap<String, Object>();
		   responseMap.put("products",map);
		    
		   

			ModelAndView modelAndView=new ModelAndView(responseJsp,"billResponse",responseMap);
			return  modelAndView;
  }

	
	//====================Product Controller Start==========================================//
	
	
	@RequestMapping(value="/get-all-product-page/{extra}",method={RequestMethod.GET})
	 public ModelAndView productsPage(String token,@PathVariable String extra) {
			
		
		
		final String uri = endPointContext+"/api/categories/all-category";
		WsResponse wsResponse=RequestHandler.getMethodWithHeader(uri, token);
		System.out.println("wsResponse--"+wsResponse.getResponseObject());
		
		
		System.out.println("IN Font End--"+extra);
	
		final String uriForList = endPointContext+"/api/product/product-page?extra="+extra;
		WsResponse wsResponseForList=RequestHandler.getMethodWithHeader(uriForList, token);
		
	    String responseJsp="product-form";
	    
	    Map<String,Object> responseMap=new HashMap<String, Object>();
	    responseMap.put("categories",wsResponse.getResponseObject());
	    responseMap.put("products",wsResponseForList.getResponseObject());
	    
		ModelAndView modelAndView=new ModelAndView(responseJsp,"productResponse",responseMap);
		return  modelAndView;
		
	  }
	
	
	@RequestMapping(value="/get-all-product",method={RequestMethod.GET})
	 public ModelAndView products(String token) {
	
		
		
		final String uri = endPointContext+"/api/categories/all-category";
		WsResponse wsResponse=RequestHandler.getMethodWithHeader(uri, token);
		System.out.println("wsResponse--"+wsResponse.getResponseObject());
		
		
		final String uriForList = endPointContext+"/api/product/all-products";
		WsResponse wsResponseForList=RequestHandler.getMethodWithHeader(uriForList, token);
		
	    String responseJsp="product-form";
	    
	    Map<String,Object> responseMap=new HashMap<String, Object>();
	    responseMap.put("categories",wsResponse.getResponseObject());
	    responseMap.put("products",wsResponseForList.getResponseObject());
	    
		ModelAndView modelAndView=new ModelAndView(responseJsp,"productResponse",responseMap);
		return  modelAndView;
		
	  }
	
	
	
	@RequestMapping(value="/add-product",method={RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody WsResponse  addProduct(@RequestParam String categoryId,@RequestParam String productId,@RequestParam String name,@RequestParam String desc,@RequestParam String status,@RequestParam String price,@RequestParam("productImage") MultipartFile productImage,String userToken) {
		
		final String uri = endPointContext+"/api/product/add";
		
		
		Map<String, Object> requestBody=new HashMap<String, Object>();
		requestBody.put("categoryId", categoryId);
		requestBody.put("productId", productId);
		requestBody.put("name", name);
		requestBody.put("desc", desc);
		requestBody.put("status", status);
		requestBody.put("price", price);
		String token=userToken;
		WsResponse wsRespone=RequestHandler.postMethodCallWithImage(uri, token, requestBody,productImage);
		
		return wsRespone;
		  
	  }
	
	
	
	@RequestMapping(value="/delete-product",method={RequestMethod.GET})
	 public @ResponseBody WsResponse deleteProduct(@RequestParam String categoryId,@RequestParam String productId,String token) {
		
		final String uri = endPointContext+"/api/product/delete/{categoryId}/{productId}";
		System.out.println("front end--"+categoryId+"=="+productId);
		Map < String, String > params=new HashMap<String, String>();
		params.put("categoryId", categoryId);
		params.put("productId", productId);
		
		WsResponse wsResponse=RequestHandler.deleteHandlerByParamter(uri,token,params);
	
		
		return  wsResponse;
	  }
	
	
	@RequestMapping(value="/edit-product",method=RequestMethod.GET)
	 public @ResponseBody WsResponse getProductById(@RequestParam String categoryId,@RequestParam String productId,String token) {
		
		final String uri = endPointContext+"/api/product/get/{categoryId}/{productId}";
		System.out.println("categoryId--"+categoryId+"==productId"+productId);
		Map <String, String > params=new HashMap<String, String>();
		params.put("categoryId", categoryId);
		params.put("productId", productId);
	
		WsResponse wsResponse=RequestHandler.getHandlerByParamter(uri,token,params);
		System.out.println("wsResponse==="+wsResponse.getResponseObject());
		
	  	return  wsResponse;
	  }
	
	//====================Product Controller End==========================================
	
	//======================Bill Controller Start=========================================
	
	
	@RequestMapping(value="/max-orderno",method={RequestMethod.GET})
	 public @ResponseBody WsResponse getMaxOrderNumber(String token) {
	
		
		
		final String uri = endPointContext+"/api//order/get-maxorder-no";
		WsResponse wsResponse=RequestHandler.getMethodWithHeader(uri, token);
		
	    String responseJsp="bill-panel";
	    
		return  wsResponse;
		
	  }
	
	
	//======================Bill Controller End=========================================
	@RequestMapping(value = "/bill-report",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity<String> generateBill(@RequestBody BillDTO  billList) {
		
		System.out.println("Order Number--"+billList.getOrderNumber());
		System.out.println("Total Amount--"+billList.getTotalAmount());
		
		
		
		Map<String, Bill> retMap = new Gson().fromJson(billList.getBill(), new TypeToken<HashMap<String, Bill>>() {}.getType());
		List<Bill> billLists=new ArrayList<Bill>();
		
		for (Map.Entry<String,Bill> entry : retMap.entrySet()) { 
			Bill currentBill=entry.getValue();
			
			billLists.add(currentBill);
		}
		
		String totalAmount="";
		String orderNumber="";
        byte[] bis = GeneratePdfReport.getPDF(billLists,orderNumber,totalAmount);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=bill.pdf");
      

        String encodedString = Base64.getEncoder().encodeToString(bis);
        return ResponseEntity .ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(encodedString);
    }
	
}
