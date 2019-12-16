package br.edu.ifpe.tcc.andre.tcc.route.thingsboard;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryItemNotFound;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessBodyThingsboard;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessException;

@Component
public class RouteThingsBoard  extends RouteBuilder{

	private static final String ROUTE_DEFAULT = "thingsboard";
	private static final String ID_ROUTE = "ID_ROTA_" + ROUTE_DEFAULT;
	public static final String ROUTE_NAME = "direct:rota_" + ROUTE_DEFAULT;
	private static final String ID = "id_path_" + ROUTE_DEFAULT;
	
	@Value("${thingsboard.base.path}")
	private String baseThingsboardPath;

	@Autowired
	private ProcessBodyThingsboard processBodyThingsboard;

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
			.end();
		
		onException(Throwable.class)
		    .handled(true)
		    .removeHeader("*")
			.process(processException)
			.removeProperties("*")
			.end();
		
		from(ROUTE_NAME).id(ID_ROUTE)
		.setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
		.setHeader(HttpHeaders.ACCEPT, constant(MediaType.APPLICATION_JSON))
		.process(processBodyThingsboard)
		.marshal().json(JsonLibrary.Jackson)
		.to("http4:"+ baseThingsboardPath + "/${property.chave}/telemetry").id(ID)
		.end();
		
		
		
	}

	
}
