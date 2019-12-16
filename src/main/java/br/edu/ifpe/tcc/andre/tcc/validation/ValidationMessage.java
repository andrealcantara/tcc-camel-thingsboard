package br.edu.ifpe.tcc.andre.tcc.validation;

public class ValidationMessage {

	private String campo;
	private String message;
	
	public ValidationMessage(String campo) {
		this(campo, "Field not valid.");
	}

	public ValidationMessage(String campo, String message) {
		this.campo = campo;
		this.message = message;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
