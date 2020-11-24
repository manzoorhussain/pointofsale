package com.pos.pos.endpoint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.model.WsResponse;
import com.pos.pos.service.OrderHistoryService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	
	@GetMapping("/get-maxorder-no")
	 public WsResponse getMaxOrderNumber() {
		 
		
		 String timeStamp = new SimpleDateFormat("HHmmssSSS").format(Calendar.getInstance().getTime());
		 String dbmaxOrder="ORD"+Calendar.getInstance().get(Calendar.YEAR)+Calendar.getInstance().get(Calendar.MONTH)+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+timeStamp;
		
		WsResponse wsResponse=new WsResponse("00","fetch Succfully",dbmaxOrder);
	
	    return wsResponse;
	  }
	
	

}
