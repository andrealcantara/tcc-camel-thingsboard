package br.edu.ifpe.tcc.andre.tcc.process;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.CrudException;
import br.edu.ifpe.tcc.andre.tcc.model.DefaultResponse;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.repositorio.RepositorioThingsBoardDevice;

@Component
public class ProcessDevice {

	@Autowired
	private RepositorioThingsBoardDevice repositoryDevice;

	public Processor processAdd() {
		return exchange -> {
			DeviceThingsboard device = exchange.getIn().getBody(DeviceThingsboard.class);
			if (repositoryDevice.contains(device)) {
				throw new CrudException("Item has exist.");
			}
			repositoryDevice.save(device);
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.CREATED.value());
			exchange.getIn().setBody("");
		};
	}

	public Processor processUpdate() {
		return exchange -> {
			DeviceThingsboard device = exchange.getIn().getBody(DeviceThingsboard.class);
			repositoryDevice.update(device);

			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
			exchange.getIn().setBody("");
		};
	}

	public Processor processRemove() {
		return exchange -> {
			String name = exchange.getIn().getHeader("device", String.class);
			repositoryDevice.removeByName(name);
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
			exchange.getIn().setBody("");
		};
	}

	public Processor processIsExist() {
		return exchange -> {
			String label = exchange.getIn().getHeader("device", String.class);
			repositoryDevice.contains(new DeviceThingsboard(label, null, null));
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
			exchange.getIn().setBody("");
		};
	}

	public Processor processAllDevices() {
		return exchange -> {
			List<String> devicesName = repositoryDevice.getAllDevices()
													   .stream()
													   .map(DeviceThingsboard::getLabel)
													   .collect(Collectors.toList());
			
			exchange.getIn().setBody(new DefaultResponse<>(devicesName));
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
		};
	}

}
