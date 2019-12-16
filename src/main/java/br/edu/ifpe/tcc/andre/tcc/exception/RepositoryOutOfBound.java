package br.edu.ifpe.tcc.andre.tcc.exception;

import org.springframework.http.HttpStatus;

public class RepositoryOutOfBound extends RuntimeException implements IExceptionResponse {

	private static final long serialVersionUID = -577497996438756030L;

	HttpStatus status = HttpStatus.BAD_REQUEST;
	
	public RepositoryOutOfBound(String message) {
		super(message);
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
