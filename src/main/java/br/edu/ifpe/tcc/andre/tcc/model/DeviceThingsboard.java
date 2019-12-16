package br.edu.ifpe.tcc.andre.tcc.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class DeviceThingsboard {
	
	private String key;
	private String name;

	public DeviceThingsboard(String name, String type) {
		super();
		this.key = type;
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String type) {
		this.key = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof DeviceThingsboard)) {
			return false;
		}
		
		DeviceThingsboard other = (DeviceThingsboard) obj;
		return Objects.equals(name, other.name);
	}
}