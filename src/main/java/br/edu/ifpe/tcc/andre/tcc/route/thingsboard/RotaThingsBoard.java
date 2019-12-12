package br.edu.ifpe.tcc.andre.tcc.route.thingsboard;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.process.ProcessBodyThingsboard;

@Component
public class RotaThingsBoard  extends RouteBuilder{

	private static final String ID_ROTA = "ID-ROTA-THINGSBOARD";
	public static final String NOME_ROTA = "direct:rota-thingsboard";
	
	@Value("${thingsboard.base.path}")
	private String baseThingsboardPath;

	@Autowired
	private ProcessBodyThingsboard processBodyThingsboard;
	
	@Override
	public void configure() throws Exception {
		
		
		
		
		from(NOME_ROTA).id(ID_ROTA)
		.setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
		.setHeader(HttpHeaders.ACCEPT, constant(MediaType.APPLICATION_JSON))
		.process(processBodyThingsboard)
		.marshal().json(JsonLibrary.Jackson)
		.to("http4:"+ baseThingsboardPath + "/${property.chave}/telemetry")
		.endRest();
		
		
		
	}

	
}
