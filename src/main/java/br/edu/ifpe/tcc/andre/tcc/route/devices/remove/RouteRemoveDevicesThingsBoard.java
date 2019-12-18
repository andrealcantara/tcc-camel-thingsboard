package br.edu.ifpe.tcc.andre.tcc.route.devices.remove;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.process.ProcessDevice;
import br.edu.ifpe.tcc.andre.tcc.route.exception.RouteProcessException;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationDevice;

@Component
public class RouteRemoveDevicesThingsBoard  extends RouteBuilder{

	private static final String ROUTE_DEFAULT = "devices_thingsboard_remove";
	private static final String ID_ROUTE = "ID_ROTA_" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota_" + ROUTE_DEFAULT;
	
	@Autowired
	private ProcessDevice processDevice;

	@Autowired
	private ValidationDevice validationDevice;
	
	@Override
	public void configure() throws Exception {
		
		onException(Throwable.class)
			.handled(true)
			.to(RouteProcessException.ROUTE_NAME);
		
		from(ROUTE_NAME).routeId(ID_ROUTE)
		.process(validationDevice)
		.process(processDevice.processRemove())
		.end().endRest();
	}
}
