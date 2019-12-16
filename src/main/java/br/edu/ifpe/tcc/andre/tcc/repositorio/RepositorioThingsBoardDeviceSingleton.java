package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;

public class RepositorioThingsBoardDeviceSingleton {
	
	public static List<DeviceThingsboard> getList() {
		return ImmutableList.copyOf(RepositorioThingsBoardDeviceSingletonHandler.HOLD);
	};

	public static void save(DeviceThingsboard deviceTypeThingsboard) {
		RepositorioThingsBoardDeviceSingletonHandler.HOLD.add(deviceTypeThingsboard);
	}

	public static DeviceThingsboard getByName(String name) {
		DeviceThingsboard response = null;
		
		Optional<DeviceThingsboard> device = RepositorioThingsBoardDeviceSingletonHandler.HOLD.stream()
				.filter(dev -> dev.getName().equals(name)).findFirst();
		
		if(device.isPresent()) {
			response = device.get();
		}
		return response;
	}

	private static class RepositorioThingsBoardDeviceSingletonHandler {
		public static List<DeviceThingsboard> HOLD;
		static {
			HOLD = Collections.synchronizedList(new ArrayList<DeviceThingsboard>());
			HOLD.add(new DeviceThingsboard("Termometro-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceThingsboard("Corrente-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceThingsboard("Porta-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceThingsboard("Lampada-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceThingsboard("ArCondicionado-Lab51", "alkjsklasjd"));

		}
	}

}
