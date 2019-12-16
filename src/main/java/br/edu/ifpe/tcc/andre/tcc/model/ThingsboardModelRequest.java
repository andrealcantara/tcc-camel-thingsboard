package br.edu.ifpe.tcc.andre.tcc.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ThingsboardModelRequest {
	
	@JsonUnwrapped
	private DeviceThingsboard device;

	private String value;
	
	public ThingsboardModelRequest(DeviceThingsboard device, String value) {
		this.device = device;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DeviceThingsboard getDevice() {
		return device;
	}

	public void setDevice(DeviceThingsboard device) {
		this.device = device;
	}
}
