package br.edu.ifpe.tcc.andre.tcc.process;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.IExceptionResponse;
import br.edu.ifpe.tcc.andre.tcc.exception.ResponseModelException;

@Component
public class ProcessException implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception e = getException(exchange);
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		String message = e.getMessage();
		Object details = null;
		if (e instanceof IExceptionResponse) {
			status = ((IExceptionResponse) e).getStatus();
			message = ((IExceptionResponse) e).localMessage();
			details = Optional.ofNullable(((IExceptionResponse) e).localDetail()).get();
		}
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, status.value());
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		exchange.getOut().setBody(new ResponseModelException(String.valueOf(status.value()), message, details));
	}

	private Exception getException(Exchange exchange) {
		Exception cause = exchange.getException();
		if (cause == null) {
			cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		}
		return cause;
	}

}
