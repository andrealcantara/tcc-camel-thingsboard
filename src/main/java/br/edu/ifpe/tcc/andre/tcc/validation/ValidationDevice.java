package br.edu.ifpe.tcc.andre.tcc.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.ValidationException;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.util.Utils;
import io.hawt.util.Strings;

@Component
public class ValidationDevice implements Processor{

	private List<ValidationMessage> messages;
	
	public ValidationDevice() {
		messages = new ArrayList<>();
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		DeviceThingsboard device = exchange.getIn().getBody(DeviceThingsboard.class);

		if(device == null) {
			messages.add(new ValidationMessage("body","Need a body with a fields: Name and Key"));
		} else  {
			if(Strings.isBlank(device.getName())) {
				messages.add(new ValidationMessage("name"));
			}
			if(Strings.isBlank(device.getKey())) {
				messages.add(new ValidationMessage("key"));
			}
		}
		if(!messages.isEmpty()) {
			throw new ValidationException("Body not valid", messages);
		}
	}

	
	
}
