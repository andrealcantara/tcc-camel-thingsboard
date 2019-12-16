package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryItemNotFound;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;

@Component
public class RepositorioThingsBoardDevice {

	public DeviceThingsboard getByName(String name) {
		DeviceThingsboard obj = RepositorioThingsBoardDeviceSingleton.getByName(name);
		if(obj == null) {
			throw new RepositoryItemNotFound(String.format("Device %s not found.", name));
		}
		return obj;
	}
	
	public void save(DeviceThingsboard deviceTypeThingsboard) {
		RepositorioThingsBoardDeviceSingleton.save(deviceTypeThingsboard);
	}
	
	public void remove(DeviceThingsboard deviceTypeThingsboard) {
		
	}

	public List<DeviceThingsboard> getAllDevices() {
		return RepositorioThingsBoardDeviceSingleton.getList();
	}
}
