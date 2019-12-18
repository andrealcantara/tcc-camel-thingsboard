package br.edu.ifpe.tcc.andre.tcc.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpe.tcc.andre.tcc.validation.model.ValidationMessages;

public class ValidationException extends RuntimeException implements IExceptionResponse {

	private static final long serialVersionUID = -8960705315292099012L;

	private final HttpStatus status;
	
	private final ValidationMessages messages;
	
	public ValidationException(String message, ValidationMessages messages) {
		super(message);
		this.messages = messages;
		this.status = HttpStatus.BAD_REQUEST;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String localMessage() {
		return this.getMessage();
	}
	
	@Override
	public Object localDetail() {
		return this.messages.getMessage();
	}
}
