package br.edu.ifpe.tcc.andre.tcc.route.exception;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpe.tcc.andre.tcc.process.ProcessException;

public class RouteProcessException extends RouteBuilder{


	private static final String ROUTE_DEFAULT = "process-exception";
	private static final String ID_ROUTE = "ID-ROTA-" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota-" + ROUTE_DEFAULT;


	@Autowired
	private ProcessException processException;
	
	@Override
	public void configure() throws Exception {

		from(ROUTE_NAME).routeId(ID_ROUTE)
		.removeHeader("*")
		.process(processException)
		.marshal().json(JsonLibrary.Jackson)
		.convertBodyTo(String.class)
		.removeProperties("*")
		.end().endRest();
	}

	
}
