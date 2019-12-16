package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryItemNotFound;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;

@Repository
public class RepositorioThingsBoardDevice {

	public DeviceThingsboard getByName(String name) {
		DeviceThingsboard obj = RepositorioThingsBoardDeviceSingleton.getByName(name);
		if(obj == null) {
			throw new RepositoryItemNotFound(String.format("Device %s not found.", name));
		}
		return obj;
	}
	
	public void save(DeviceThingsboard device) {
		RepositorioThingsBoardDeviceSingleton.save(device);
	}
	
	public void removeByName(String name) {
		DeviceThingsboard device = getByName(name);
		RepositorioThingsBoardDeviceSingleton.remove(device);
	}

	public void update(DeviceThingsboard device) {
		RepositorioThingsBoardDeviceSingleton.update(device);
	}
	
	public List<DeviceThingsboard> getAllDevices() {
		return RepositorioThingsBoardDeviceSingleton.getList();
	}
	
	public boolean contains(DeviceThingsboard device) {
		return RepositorioThingsBoardDeviceSingleton.contains(device);
	}
}
