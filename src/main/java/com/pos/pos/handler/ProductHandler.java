package com.pos.pos.handler;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.pos.pos.dto.ProductDTO;

import com.pos.pos.model.ProductModel;
import com.pos.pos.util.CommanUtil;

public class ProductHandler {

	
		public static List<ProductDTO> getProductDTOList(Iterable<ProductModel> prodList){
		
		List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
		
		
		for(ProductModel prodModel:prodList) {
			
			ProductDTO productDTO=new ProductDTO();
			productDTO.setCategoryId(prodModel.getProductPK().getCategoryId());
			productDTO.setProdId(prodModel.getProductPK().getProductId());
			productDTO.setProdName(prodModel.getName());
			productDTO.setProdDesc(prodModel.getDesc());
			productDTO.setProdStatus(prodModel.getStatus());
			productDTOList.add(productDTO);
			
		}
		
		return productDTOList;
	}

		
		
		public static List<ProductDTO> getProductDTOListWithCategory(Iterable<ProductModel> prodList,Map<String, String> map){
			
			List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
			
			int counter=0;
			for(ProductModel prodModel:prodList) {
				
				String categoryId=prodModel.getProductPK().getCategoryId();
				String categoryDesc=map.get(prodModel.getProductPK().getCategoryId());
				String displayImage = CommanUtil.getDisplayImage(prodModel.getImage());
				
					
				
				ProductDTO productDTO=new ProductDTO(categoryDesc,categoryId,prodModel.getProductPK().getProductId(),prodModel.getName(),prodModel.getDesc(),prodModel.getStatus(),prodModel.getPrice(),displayImage);
				counter+=1;
				productDTO.setSerialNo(String.valueOf(counter));
				productDTOList.add(productDTO);
			}
			
			return productDTOList;
		}
		
		
		public static List<List<ProductDTO>> getProductForBill(Iterable<ProductModel> prodModelList){
	
			
			
			List<List<ProductDTO>> retList=new ArrayList<>();
			List<ProductDTO> convertedList=new ArrayList<ProductDTO>();
			
			

			
			for(ProductModel prodModel:prodModelList) {
				String displayImage = CommanUtil.getDisplayImage(prodModel.getImage());
				String catId=prodModel.getProductPK().getCategoryId();
				String prodId=prodModel.getProductPK().getProductId();
				String prodName=prodModel.getName();
				String prodPrice=prodModel.getPrice();
				
				ProductDTO productDTO=new ProductDTO();
				productDTO.setCategoryId(catId);
				productDTO.setProdId(prodId);
				productDTO.setProdName(prodName);
				productDTO.setProdPrice(prodPrice);
				productDTO.setDisplayImage(displayImage);
				
				
				convertedList.add(productDTO);
			}
			
		
			
			int startIndex=0,endIndex=4;
			for(int j=0;j<convertedList.size();j+=3) {
			
				if(j>0) {
					startIndex=endIndex;
					endIndex+=4;
				
					
					if(endIndex>convertedList.size()) {
						endIndex=convertedList.size();
					}
				}
				
				
		List<ProductDTO> currentList = convertedList.subList(startIndex, endIndex);
		retList.add(currentList);
		
			}
			
			
			
	return retList;
		
			
			
			
		}
}
