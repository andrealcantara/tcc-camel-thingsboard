package br.edu.ifpe.tcc.andre.tcc.exception;

import org.springframework.http.HttpStatus;

public class CrudException extends RuntimeException implements IExceptionResponse {

	private static final long serialVersionUID = 1354735365393293703L;

	private final HttpStatus status;	
	
	public CrudException(String message) {
		super(message);
		status = HttpStatus.BAD_REQUEST;
		
	}


	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String localMessage() {
		return this.getMessage();
	}
}
