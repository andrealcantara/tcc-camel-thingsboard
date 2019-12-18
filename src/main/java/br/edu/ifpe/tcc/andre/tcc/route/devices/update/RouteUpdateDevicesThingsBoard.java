package br.edu.ifpe.tcc.andre.tcc.route.devices.update;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.process.ProcessDevice;
import br.edu.ifpe.tcc.andre.tcc.route.exception.RouteProcessException;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationDevice;

@Component
public class RouteUpdateDevicesThingsBoard  extends RouteBuilder{

	private static final String ROUTE_DEFAULT = "devices-thingsboard-remove";
	private static final String ID_ROUTE = "ID-ROTA-" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota-" + ROUTE_DEFAULT;
	
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
		.process(processDevice.processUpdate())
		.end().endRest();
	}
}
