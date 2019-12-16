package br.edu.ifpe.tcc.andre.tcc.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.edu.ifpe.tcc.andre.tcc.util.Utils;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationMessage;

public class ValidationException extends RuntimeException implements IExceptionResponse {

	private static final long serialVersionUID = -8960705315292099012L;

	private final HttpStatus status;
	
	private final List<ValidationMessage> messages;
	
	public ValidationException(String message, List<ValidationMessage> messages) {
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
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMessage());
		sb.append(", \"messages\":");
		sb.append(Utils.objectToJson(messages));
		return sb.toString();
	}
}
