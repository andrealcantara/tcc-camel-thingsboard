package br.edu.ifpe.tcc.andre.tcc.exception;

import org.springframework.http.HttpStatus;

public interface IExceptionResponse {
	
	public HttpStatus getStatus();
	
	public String localMessage();

}