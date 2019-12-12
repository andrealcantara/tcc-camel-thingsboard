package br.edu.ifpe.tcc.andre.tcc.model;

public class DeviceTypeThingsboard {

	private String name;
	private String key;
	public DeviceTypeThingsboard(String name, String key) {
		super();
		this.name = name;
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
