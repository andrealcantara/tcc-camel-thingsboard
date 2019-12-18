package br.edu.ifpe.tcc.andre.tcc.validation.model;

public abstract class AbstractValidation<T> {

	private String campo;
	
	public AbstractValidation(String campo) {
		super();
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

	public abstract T getMessage();

}