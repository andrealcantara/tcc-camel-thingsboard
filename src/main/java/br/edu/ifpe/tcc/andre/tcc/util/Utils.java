package br.edu.ifpe.tcc.andre.tcc.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	private Utils() { }
	
	public static String objectToJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		try {
			return mapper.convertValue(json, clazz);
		} catch (Exception e) {
			return null;
		}
	}
}
