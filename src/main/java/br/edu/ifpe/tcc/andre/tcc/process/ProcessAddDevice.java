package br.edu.ifpe.tcc.andre.tcc.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.repositorio.RepositorioThingsBoardDevice;

@Component
public class ProcessAddDevice implements Processor {

	@Autowired
	private RepositorioThingsBoardDevice repositorioThingsBoardDevice;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		DeviceThingsboard device = exchange.getIn().getBody(DeviceThingsboard.class);
		
		repositorioThingsBoardDevice.save(device);
	}

	
}
