package br.edu.ifpe.tcc.andre.tcc.util;

public class ConstantValue {
	
	public static final String THINGSBOARD_RESPONSE = "thingsboard-response";
	public static final String THINGSBOARD_VALUE = "thingsboard-value";
	public static final String THINGSBOARD_KEY = "thingsboard-key";


	
	private ConstantValue() {}
	
	public static final String toProperties(String propertyKey) {
		return String.format("${properties.%s}", propertyKey);
	}
}
