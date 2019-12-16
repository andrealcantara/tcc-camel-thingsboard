package br.edu.ifpe.tcc.andre.tcc.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.IExceptionResponse;
import br.edu.ifpe.tcc.andre.tcc.exception.ResponseModelException;

@Component
public class ProcessException implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception e = getException(exchange);
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		String message = "Temporarily Unavailable. Try again later.";
		if (e instanceof IExceptionResponse) {
			status = ((IExceptionResponse) e).getStatus();
			message = ((IExceptionResponse) e).localMessage();
		}
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, status.toString());
		exchange.getOut().setBody(new ResponseModelException(status.toString(), message));
	}

	private Exception getException(Exchange exchange) {
		Exception cause = exchange.getException();
		if (cause == null) {
			cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		}
		return cause;
	}

}
