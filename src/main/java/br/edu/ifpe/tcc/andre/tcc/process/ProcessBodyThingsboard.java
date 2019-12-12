package br.edu.ifpe.tcc.andre.tcc.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessBodyThingsboard implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String tipo = exchange.getIn().getHeader("device", String.class);
		String value = exchange.getIn().getHeader("value", String.class);
	}
}
