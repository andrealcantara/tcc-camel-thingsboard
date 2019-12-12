package br.edu.ifpe.tcc.andre.tcc.model;

public class BaseDeviceThingsboard {
	public DeviceTypeThingsboard type;
	public Object value;

	public BaseDeviceThingsboard(DeviceTypeThingsboard type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	public DeviceTypeThingsboard getType() {
		return type;
	}
	
	public void setType(DeviceTypeThingsboard type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}