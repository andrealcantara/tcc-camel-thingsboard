package br.edu.ifpe.tcc.andre.tcc.process;

import static br.edu.ifpe.tcc.andre.tcc.util.ConstantValue.THINGSBOARD_KEY;
import static br.edu.ifpe.tcc.andre.tcc.util.ConstantValue.THINGSBOARD_VALUE;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.repositorio.RepositorioThingsBoardDevice;

@Component
public class ProcessBodyThingsboard implements Processor {

	@Autowired
	private RepositorioThingsBoardDevice devices;

	@Override
	public void process(Exchange exchange) {
		String deviceName = exchange.getIn().getHeader("device", String.class);
		String value = exchange.getIn().getHeader("value", String.class);

		DeviceThingsboard device = devices.getByName(deviceName);

		exchange.setProperty(THINGSBOARD_VALUE, value);
		exchange.setProperty(THINGSBOARD_KEY, device.getKey());
	}
}
