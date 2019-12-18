package br.edu.ifpe.tcc.andre.tcc.validation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.ValidationException;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.repositorio.RepositorioThingsBoardDevice;
import br.edu.ifpe.tcc.andre.tcc.validation.model.ValidationMessage;
import br.edu.ifpe.tcc.andre.tcc.validation.model.ValidationMessages;
import io.hawt.util.Strings;

@Component
public class ValidationSendThingsboard implements Processor {

	private ValidationMessages messages;
	
	private RepositorioThingsBoardDevice repositorio;
	
	@Autowired
	public ValidationSendThingsboard(RepositorioThingsBoardDevice repositorio) {
		messages = new ValidationMessages("Path");
		this.repositorio = repositorio;
	}
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String deviceName = exchange.getIn().getHeader("device", String.class);
		String value = exchange.getIn().getHeader("value", String.class);
		
		if(Strings.isBlank(deviceName)) {
			messages.addMensagem(new ValidationMessage(null, "Device not null or invalid"));
		}
		
		if(!repositorio.contains(new DeviceThingsboard(deviceName, null, null))) {
			messages.addMensagem(new ValidationMessage(null, "Device not found"));
		}
		
		if(Strings.isBlank(value)) {
			messages.addMensagem(new ValidationMessage(null, "Value not found"));
		}
		
		if(messages.hasValidationMessage()) {
			throw new ValidationException("Problem with path.", messages);
		}
	}
}
