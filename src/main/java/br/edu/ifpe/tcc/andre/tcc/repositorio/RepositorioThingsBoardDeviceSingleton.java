package br.edu.ifpe.tcc.andre.tcc.repositorio;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import br.edu.ifpe.tcc.andre.tcc.model.DeviceTypeThingsboard;

public class RepositorioThingsBoardDeviceSingleton {
	public static List<DeviceTypeThingsboard> getSingleton(){
		return ImmutableList.copyOf(RepositorioThingsBoardDeviceSingletonHandler.HOLD);
	};
	
	public static void add(DeviceTypeThingsboard deviceTypeThingsboard) {
		RepositorioThingsBoardDeviceSingletonHandler.HOLD.add(deviceTypeThingsboard);
	}
	
	
	private static class RepositorioThingsBoardDeviceSingletonHandler {
		public static List<DeviceTypeThingsboard> HOLD;
		static {
			HOLD = new ArrayList<>();
			HOLD.add(new DeviceTypeThingsboard("Termometro-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceTypeThingsboard("Corrente-Lab51", "alkjsklasjd"));
			HOLD.add(new DeviceTypeThingsboard("Porta-Lab51", "alkjsklasjd"));
			
		}
	}
	
	
}
