package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceTypeThingsboard;

@Component
public class RepositorioThingsBoardDevice {

	private List<DeviceTypeThingsboard> devices;

	public RepositorioThingsBoardDevice() {
		this.devices = RepositorioThingsBoardDeviceSingleton.getSingleton();
	}
	
	public void saveType(DeviceTypeThingsboard deviceTypeThingsboard) {
		RepositorioThingsBoardDeviceSingleton.add(deviceTypeThingsboard);
	}

	public List<DeviceTypeThingsboard> getDevices() {
		return devices;
	}
}
