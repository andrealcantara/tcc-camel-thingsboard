package br.edu.ifpe.tcc.andre.tcc.route;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.route.devices.add.RotaDevicesThingsBoard;
import br.edu.ifpe.tcc.andre.tcc.route.thingsboard.RouteThingsBoard;

@Component
public class RestConfig  extends RouteBuilder {

	private static final String THINGSBOARD_ROUTE_PRINCIPAL = "thingsboard-rota-principal";
	private static final String PATH_DEFAULT = "/api/v1/thingsboard";
	private static final String THINGSBOARD_ROUTE_ADD_DEVICES = null;

	@Override
	public void configure() throws Exception {
		  restConfiguration() // <1> Iniciar um bloco de configuração REST
          .bindingMode(RestBindingMode.json) // <2> Vincula os objetos de entrada e saída como JSON
          .apiContextPath("/api-doc") // <3> Definir o caminho para a definição de Swagger do serviço REST
          .apiProperty("api.title",
                  "Tcc");
		  
		  rest(PATH_DEFAULT)
			  .description("Api para consumir o Thingboards")
	          .consumes(APPLICATION_JSON.toString())
	          .produces(APPLICATION_JSON.toString())
	          .enableCORS(true)
	          .post("/{device}/{value}").id(THINGSBOARD_ROUTE_PRINCIPAL).description("Envia um Valor ao Thingsboard")
	          .to(RouteThingsBoard.ROUTE_NAME)
          	  
		  	  .post("/devices").id(THINGSBOARD_ROUTE_ADD_DEVICES).description("Adiciona um Device no camel")
          	  .to(RotaDevicesThingsBoard.ROUTE_NAME);
	}
	

}
