package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import br.edu.ifpe.tcc.andre.tcc.exception.RepositoryOutOfBound;
import br.edu.ifpe.tcc.andre.tcc.model.DeviceThingsboard;
import br.edu.ifpe.tcc.andre.tcc.util.Utils;

public class RepositorioThingsBoardDeviceSingleton {
	
	public static boolean contains(DeviceThingsboard device) {
		return RepositorioThingsBoardDeviceSingletonHandler.HOLD.contains(device);
	}
	
	public static List<DeviceThingsboard> getList() {
		return ImmutableList.copyOf(RepositorioThingsBoardDeviceSingletonHandler.HOLD);
	};

	public static void save(DeviceThingsboard device) {
		RepositorioThingsBoardDeviceSingletonHandler.HOLD.add(device);
	}
	
	public static boolean remove(DeviceThingsboard device) {
		return RepositorioThingsBoardDeviceSingletonHandler.HOLD.remove(device);
	}
	
	public static void update(DeviceThingsboard device) {
		int index = RepositorioThingsBoardDeviceSingletonHandler.HOLD.indexOf(device);
		if( index == -1) {
			throw new RepositoryOutOfBound(String.format("Element %s not found.", Utils.objectToJson(device)));
		}
		RepositorioThingsBoardDeviceSingletonHandler.HOLD.set(index, device);
	}

	public static DeviceThingsboard getByName(String name) {
		DeviceThingsboard response = null;
		
		Optional<DeviceThingsboard> device = RepositorioThingsBoardDeviceSingletonHandler.HOLD.stream()
				.filter(dev -> dev.getLabel().equals(name)).findFirst();
		
		if(device.isPresent()) {
			response = device.get();
		}
		return response;
	}

	private static class RepositorioThingsBoardDeviceSingletonHandler {
		public static List<DeviceThingsboard> HOLD;
		static {
			HOLD = Collections.synchronizedList(new ArrayList<DeviceThingsboard>());
			HOLD.add(new DeviceThingsboard("Termometro-Lab51", "1qUKsOkyam3VW5o3xWH1", "TERMOMETRO"));
			HOLD.add(new DeviceThingsboard("Corrente-Lab51", "xFpWFn2EMcH22b7uGJdq", "CORRENTE"));
			HOLD.add(new DeviceThingsboard("Porta-Lab51", "x6fsvuLVXsAeSy61AkO0", "PORTA"));
			HOLD.add(new DeviceThingsboard("Lampada-Lab51", "nXyDSR9gTiD9xeCw6yip", "LAMPADA"));
			HOLD.add(new DeviceThingsboard("Tomadas-Lab51", "qPQ2ZtPBsj2CciRhecGZ", "TOMADA"));

		}
	}

}
