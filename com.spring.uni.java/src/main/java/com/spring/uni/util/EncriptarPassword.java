package com.spring.uni.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

	public static void main(String[] args) {
		var password1 = "123";
		System.out.println("Password: " +password1);
		System.out.println("Password crypto: " + encriptarPassword(password1));
	}
	
	public static String encriptarPassword(String p) {
		var encoder = new BCryptPasswordEncoder();
		return encoder.encode(p);
	}
}
