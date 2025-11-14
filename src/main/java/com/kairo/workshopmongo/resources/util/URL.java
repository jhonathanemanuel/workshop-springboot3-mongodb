package com.kairo.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant convertDate(String textDate, Instant defaultValue) {
	    if (textDate == null || textDate.isEmpty()) {
	        return defaultValue;
	    }

	    DateTimeFormatter fmt = DateTimeFormatter
	            .ofPattern("dd-MM-yyyy")
	            .withZone(ZoneId.of("GMT"));  // Aqui sim o timezone funciona

	    try {
	        return fmt.parse(textDate, Instant::from);
	    } catch (Exception e) {
	        return defaultValue;
	    }
	}
}
