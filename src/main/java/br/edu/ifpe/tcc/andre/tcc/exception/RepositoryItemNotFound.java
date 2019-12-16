package br.edu.ifpe.tcc.andre.tcc.exception;

import org.springframework.http.HttpStatus;

public class RepositoryItemNotFound extends RuntimeException implements IExceptionResponse {

	private static final long serialVersionUID = 5102797987140786876L;
	
	private final HttpStatus status;

	public RepositoryItemNotFound(String message) {
		super(message);
		status = HttpStatus.NOT_FOUND;
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
