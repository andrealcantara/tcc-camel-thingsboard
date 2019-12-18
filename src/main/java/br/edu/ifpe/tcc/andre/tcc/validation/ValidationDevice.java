package br.edu.ifpe.tcc.andre.tcc.validation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.ValidationException;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.validation.model.ValidationMessage;
import br.edu.ifpe.tcc.andre.tcc.validation.model.ValidationMessages;
import io.hawt.util.Strings;

@Component
public class ValidationDevice implements Processor{

	private ValidationMessages messages;
	
	public ValidationDevice() {
		messages = new ValidationMessages("Body");
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		DeviceThingsboard device = exchange.getIn().getBody(DeviceThingsboard.class);

		if(device == null) {
			messages.addMensagem(new ValidationMessage(null,"Need a body with a fields: Name and Key"));
		} else  {
			if(Strings.isBlank(device.getLabel())) {
				messages.addMensagem(new ValidationMessage("label"));
			}
			if(Strings.isBlank(device.getKey())) {
				messages.addMensagem(new ValidationMessage("key"));
			}
			if(Strings.isBlank(device.getName())) {
				messages.addMensagem(new ValidationMessage("name"));
			}
		}
		if(messages.hasValidationMessage()) {
			throw new ValidationException("Body not valid", messages);
		}
	}

	
	
}
