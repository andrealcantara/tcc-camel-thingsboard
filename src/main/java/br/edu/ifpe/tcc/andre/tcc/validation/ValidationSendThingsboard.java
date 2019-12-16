package br.edu.ifpe.tcc.andre.tcc.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.edu.ifpe.tcc.andre.tcc.exception.ValidationException;

public class ValidationSendThingsboard implements Processor {

	private List<ValidationMessage> messages;
	
	public ValidationSendThingsboard() {
		messages = new ArrayList<>();
	}
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String deviceName = exchange.getIn().getHeader("device", String.class);
		String value = exchange.getIn().getHeader("value", String.class);
		
		if(deviceName == null) {
			messages.add(new ValidationMessage(null, "Device not found"));
		}
		
		if(value == null) {
			messages.add(new ValidationMessage(null, "Value not found"));
		}
		
		if(!messages.isEmpty()) {
			throw new ValidationException("Problem with path.", messages);
		}
	}
}
