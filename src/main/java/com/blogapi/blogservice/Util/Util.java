package com.blogapi.blogservice.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static String hashString(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(input.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				hexString.append(String.format("%02x", b));
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isNeitherNullNorEmpty(Object obj) {
		boolean result = true ;
		if(obj == null) {
			return false;
		}
		else {
			if(obj instanceof String) {
				result = ((String) obj).trim().equals("");
				return false;
			}
			else {
				return true;
			}
		}
	}
}
