package br.edu.ifpe.tcc.andre.tcc.validation.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationMessages extends AbstractValidation<List<AbstractValidation<?>>> {

	private List<AbstractValidation<?>> messages;
	
	public ValidationMessages(String campo) {
		this(campo, new ArrayList<>());
	}
	
	public ValidationMessages(String campo, List<AbstractValidation<?>> messages) {
		super(campo);
		this.messages = messages;
	}

	public void addMensagem(AbstractValidation<?> message) {
		this.messages.add(message);
	}
	
	public boolean hasValidationMessage() {
		return !this.messages.isEmpty();
	}
	
	@Override
	public List<AbstractValidation<?>> getMessage() {
		return messages;
	}

}
