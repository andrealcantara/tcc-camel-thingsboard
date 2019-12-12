package br.edu.ifpe.tcc.andre.tcc.route;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.route.thingsboard.RotaThingsBoard;

@Component
public class RestConfig  extends RouteBuilder {

	private static final String PATH_DEFAULT = "/api/v1/thingsboard";

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
	          .post("/{device}/{value}")
	          .id("getTransacao")
	          .description("Retorna uma Transação")
	          .to(RotaThingsBoard.NOME_ROTA);
	}
	

}
