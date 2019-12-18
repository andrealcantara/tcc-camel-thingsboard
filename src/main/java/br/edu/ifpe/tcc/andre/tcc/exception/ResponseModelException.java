package br.edu.ifpe.tcc.andre.tcc.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseModelException {
	
	private String code;
	private String message;
	private Object details;
	
	
	public ResponseModelException(String code, String message) {
		this(code,message,null);
	}
	public ResponseModelException(String code, String message, Object details) {
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
}
