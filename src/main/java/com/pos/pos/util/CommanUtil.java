package com.pos.pos.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class CommanUtil {
	
	public static String getDisplayImage(byte[] image) {

	    byte[] encodeBase64 = Base64.getEncoder().encode(image);
	     String base64Encoded=null;;
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}              
	 
		return base64Encoded;
	}

	
	public static String saveDisplayImage(byte[] image) {

	    byte[] encodeBase64 = Base64.getDecoder().decode(image);
	     String base64Encoded=null;;
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}              
	 
		return base64Encoded;
	}
}
