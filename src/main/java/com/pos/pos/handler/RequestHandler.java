package com.pos.pos.handler;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;


import com.pos.pos.model.WsResponse;



 





public class RequestHandler {
	
	public static WsResponse postMethodWithOutHeader(final String uri,Object object) {
	
	 RestTemplate restTemplate = new RestTemplate();
	 //LoginUserModel loginUser=new LoginUserModel("test1", "test1");
	    
	   // Data attached to the request
	 HttpEntity<Object> requestBody = new HttpEntity<>(object);
	 ResponseEntity<String> result = restTemplate.postForEntity(uri, requestBody, String.class);
	   
	 Gson gson=new Gson();
	 WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
	    
	
        
	 return response;
		
	}
	
	
	public static WsResponse postMethodCall(final String uri,String token,Map<String, Object> requestBody) {
		
		 RestTemplate restTemplate = new RestTemplate();
		
		 
		 HttpHeaders headers = new HttpHeaders();
		  headers.add("token", token);
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 
		 
		    
		 // Data attached to the request
		    
		 HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
		 ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		   
		 Gson gson=new Gson();
		 WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
		    
		
	        
		 return response;
			
		}
	
	
	
	public static WsResponse postMethodCallWithImage(final String uri,String token,Map<String, Object> requestBody,@RequestParam("productImage") MultipartFile productImage ) {
		
		System.out.println("Request Handler--"+productImage);
		
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		 
		 HttpHeaders headers = new HttpHeaders();
		  headers.add("token", token);
		  //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		 
		
		    
		 MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		 form.add("categoryId", requestBody.get("categoryId"));
		 form.add("productId", requestBody.get("productId"));
		 form.add("name", requestBody.get("name"));
		 form.add("desc", requestBody.get("desc"));
		 form.add("price", requestBody.get("price"));
		 form.add("status", requestBody.get("status"));
		 
		 
			
		
			ByteArrayResource contentsAsResource = null;
	        try {
	            contentsAsResource = new ByteArrayResource(productImage.getBytes()) {
	                @Override
	                public String getFilename() {
	                    return productImage.getOriginalFilename();
	                }
	            };
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	        form.add("productImage",contentsAsResource);
		
		 HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);

		 

		 ResponseEntity<String> result=restTemplate.postForEntity(uri, requestEntity, String.class);
		 
		 Gson gson=new Gson();
		 WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
		    
	      return response;
			
		 
		
		
		}
	public static WsResponse getMethodWithHeader(final String uri,String token) {
		RestTemplate restTemplate = new RestTemplate();
	    	
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("token", token);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	   
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	   
	    Gson gson=new Gson();
	    WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
	 
	   return response;
	}
	
	public static WsResponse getHandlerByParamter(final String uri,String userToken,Map < String, String > params) {
		
	
	String token=userToken;
	
	HttpHeaders headers = new HttpHeaders();
	headers.add("token", token);
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    
	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		   
		    
	RestTemplate restTemplate = new RestTemplate();
	HttpEntity<String> result  = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, params);

	        
	Gson gson=new Gson();
	WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
		 
	
	return response;
		}
	
	
	public static WsResponse deleteMethodCall(String uri, Map<String, String> params) {

		System.out.println("deleteMethodCall");
		
		
		RestTemplate restTemplate = new RestTemplate();
		  restTemplate.delete(uri,params);
		 
		 
		  return null;
		
		
		
		 
	
		}
	
	
	public static WsResponse deleteHandlerByParamter(final String uri,String userToken,Map < String, String > params) {
		
		String token=userToken;
	
		        
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", token);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			    
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			   
			    
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> result  = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class, params);

		        
		Gson gson=new Gson();
		WsResponse response= gson.fromJson(result.getBody(), WsResponse.class);
			 
		System.out.println(response);
		return response;
			}	
	
	
	


}
