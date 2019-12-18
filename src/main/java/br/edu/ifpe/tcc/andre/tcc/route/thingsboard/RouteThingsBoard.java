package br.edu.ifpe.tcc.andre.tcc.route.thingsboard;

import java.util.AbstractMap.SimpleEntry;

import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryItemNotFound;
import br.edu.ifpe.tcc.andre.tcc.model.DefaultResponse;
import br.edu.ifpe.tcc.andre.tcc.process.ProcessBodyThingsboard;
import br.edu.ifpe.tcc.andre.tcc.route.exception.RouteProcessException;
import br.edu.ifpe.tcc.andre.tcc.util.ConstantValue;
import br.edu.ifpe.tcc.andre.tcc.validation.ValidationSendThingsboard;

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
	private ValidationSendThingsboard validation;	
	
	@Override
	public void configure() throws Exception {
		
		onException(RepositoryItemNotFound.class)
			.maximumRedeliveries(3)
		    .handled(true)
		    .to(RouteProcessException.ROUTE_NAME);
		
		onException(Throwable.class)
		    .handled(true)
		    .to(RouteProcessException.ROUTE_NAME);
		
		from(ROUTE_NAME).id(ID_ROUTE)
		.setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
		.setHeader(HttpHeaders.ACCEPT, constant(MediaType.APPLICATION_JSON))
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
	    .removeHeader(Exchange.HTTP_PATH)
		.process(validation)
		.process(processBodyThingsboard)
		.marshal().json(JsonLibrary.Jackson)
		.convertBodyTo(String.class)
		.toD("http4:"+ baseThingsboardPath + "/${property." + ConstantValue.THINGSBOARD_KEY + "}/telemetry?bridgeEndpoint=true").id(ID)
		.process(ex -> {
					ex.getIn().setBody(new DefaultResponse<SimpleEntry<String, String>>(new SimpleEntry<String, String>(
							"response",
							"Enviado com sucesso. Valor [ " + ex.getIn().getHeader("value", String.class) + " ]."
							)));
				}).end().endRest();
		
		
	}

	
}
