package br.edu.ifpe.tcc.andre.tcc.route.devices.add;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.process.ProcessDevice;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessException;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationDevice;

@Component
public class RouteAddDevicesThingsBoard  extends RouteBuilder{

	private static final String ROUTE_DEFAULT = "devices-thingsboard-add";
	private static final String ID_ROUTE = "ID-ROTA-" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota-" + ROUTE_DEFAULT;
	
	@Autowired
	private ProcessDevice processDevice;

	@Autowired
	private ValidationDevice validationDevice;

	@Autowired
	private ProcessException processException;

	@Override
	public void configure() throws Exception {
		
		onException(Throwable.class)
			.handled(true)
		    .removeHeader("*")
			.process(processException)
			.removeProperties("*")
			.end();
		
		from(ROUTE_NAME).streamCaching().routeId(ID_ROUTE)
		.process(validationDevice)
		.process(processDevice.processAdd()).end();
	}
}
