package br.edu.ifpe.tcc.andre.tcc.route.devices.add;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryItemNotFound;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessAddDevice;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessBodyThingsboard;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessException;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationAddDevice;

public class RotaDevicesThingsBoard  extends RouteBuilder{


	private static final String ROUTE_DEFAULT = "devices-thingsboard";
	private static final String ID_ROUTE = "ID-ROTA-" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota-" + ROUTE_DEFAULT;
	private static final String ID = "id-path-" + ROUTE_DEFAULT;
	
	@Autowired
	private ProcessAddDevice processAddDevice;

	@Autowired
	private ValidationAddDevice validationAddDevice;

	@Autowired
	private ProcessException processException;
	
	

	@Override
	public void configure() throws Exception {
		onException(RepositoryItemNotFound.class)
			.maximumRedeliveries(3)
		    .handled(true)
		    .removeHeader("*")
			.process(processException)
			.removeProperties("*")
			.endRest();
		
		onException(Throwable.class)
		    .handled(true)
		    .removeHeader("*")
			.process(processException)
			.removeProperties("*")
			.endRest();
		
		from(ROUTE_NAME).routeId(ID_ROUTE)
		.process(validationAddDevice)
		.process(processAddDevice)
		.endRest();
			
	}
}
