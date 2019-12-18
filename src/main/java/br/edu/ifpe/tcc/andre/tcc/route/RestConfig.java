package br.edu.ifpe.tcc.andre.tcc.route;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.route.devices.add.RouteAddDevicesThingsBoard;
import br.edu.ifpe.tcc.andre.tcc.route.devices.list.RouteListDevicesThingsBoard;
import br.edu.ifpe.tcc.andre.tcc.route.devices.remove.RouteRemoveDevicesThingsBoard;
import br.edu.ifpe.tcc.andre.tcc.route.devices.update.RouteUpdateDevicesThingsBoard;
import br.edu.ifpe.tcc.andre.tcc.route.thingsboard.RouteThingsBoard;

@Component
public class RestConfig  extends RouteBuilder {

	private static final String THINGSBOARD_ROUTE_PRINCIPAL = "thingsboard-rota-principal";
	private static final String PATH_DEFAULT = "/api/v1/thingsboard";
	private static final String THINGSBOARD_ROUTE_ADD_DEVICES = THINGSBOARD_ROUTE_PRINCIPAL+"add-device";
	private static final String THINGSBOARD_ROUTE_UPDATE_DEVICES = THINGSBOARD_ROUTE_PRINCIPAL+"update-device";
	private static final String THINGSBOARD_ROUTE_REMOVE_DEVICES = THINGSBOARD_ROUTE_PRINCIPAL+"remove-device";
	private static final String THINGSBOARD_ROUTE_LIST_DEVICES = THINGSBOARD_ROUTE_PRINCIPAL+"list-device";

	@Override
	public void configure() throws Exception {
		  restConfiguration() 
          .bindingMode(RestBindingMode.json) 
          .apiContextPath("/api-doc") 
          .apiProperty("api.title", "Projeto de TCC")
		  .apiProperty("api.description", "Projeto de TCC - O camel consumir o thingsboard via api")
		  .apiProperty("api.contact.name", "Andre Alcantara")
		  .apiProperty("api.contact.email", "andrealcant@gmail.com");
		  
		  rest(PATH_DEFAULT)
			  .description("Api para consumir o Thingboards")
	          .consumes(APPLICATION_JSON.toString())
	          .produces(APPLICATION_JSON.toString())
	          .enableCORS(true)
	          
	          .post("/{device}/{value}").id(THINGSBOARD_ROUTE_PRINCIPAL).description("Envia um Valor ao Thingsboard")

              .param().name("device").description("O campo label do device.").endParam()
              .param().name("value").description("O valor que sera enviado para o device.").endParam()

              .responseMessage().code(200).message("Envia um valor para a apí do thingsboard.").endResponseMessage()
              .responseMessage().code(500).message("Problema 5xx normalmente de comunicacao.").endResponseMessage()
              .responseMessage().code(400).message("Problema 400 são os parametros [device, value] invalidos.").endResponseMessage()
	          .to(RouteThingsBoard.ROUTE_NAME)

		  	  .post("/devices").id(THINGSBOARD_ROUTE_ADD_DEVICES).description("Adiciona um Device no camel")
          	  .type(DeviceThingsboard.class)
              .responseMessage().code(200).message("Adiciona mais um device do thingsboard ao camel.").endResponseMessage()
              .responseMessage().code(500).message("Problema 5xx normalmente de comunicacao.").endResponseMessage()
              .responseMessage().code(400).message("Problema 400 é o body invalido.").endResponseMessage()
		  	  .to(RouteAddDevicesThingsBoard.ROUTE_NAME)

          	  .patch("/devices").id(THINGSBOARD_ROUTE_UPDATE_DEVICES).description("Atualiza um Device no camel")
              .responseMessage().code(200).message("Atualiza um device do thingsboard no camel.").endResponseMessage()
              .responseMessage().code(500).message("Problema 5xx normalmente de comunicacao.").endResponseMessage()
              .responseMessage().code(400).message("Problema 400 é o body invalido.").endResponseMessage()
          	  .type(DeviceThingsboard.class)
          	  .to(RouteUpdateDevicesThingsBoard.ROUTE_NAME)

		  	  .post("/devices/{device}").id(THINGSBOARD_ROUTE_REMOVE_DEVICES).description("Remove um Device no camel")
			  .responseMessage().code(200).message("Remove um valor de thinsboards device do camel.").endResponseMessage()
			  .responseMessage().code(500).message("Problema 5xx normalmente de comunicacao.").endResponseMessage()
			  .responseMessage().code(400).message("Problema 400 são os parametros device invalidos.").endResponseMessage()
	          .to(RouteRemoveDevicesThingsBoard.ROUTE_NAME)

	      	  .get("/devices").id(THINGSBOARD_ROUTE_LIST_DEVICES).description("Lista todos os Device no camel")
	      	  .responseMessage().code(200).message("Lista todos os thinsboards device cadastrados no camel.").endResponseMessage()
	      	  .to(RouteListDevicesThingsBoard.ROUTE_NAME);
	}
	

}
