package br.edu.ifpe.tcc.andre.tcc.validation.model;

public class ValidationMessage extends AbstractValidation<String> {

	private String message;
	
	public ValidationMessage(String campo) {
		this(campo, "Field not valid.");
	}

	public ValidationMessage(String campo, String message) {
		super(campo);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}


}
