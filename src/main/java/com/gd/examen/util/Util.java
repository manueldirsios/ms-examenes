package com.gd.examen.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	private Util(){}
	  public static String generateHash(String data) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
	        StringBuilder hexString = new StringBuilder();

	        for (byte b : hashBytes) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    }
	  
	  
}
