package br.edu.ifpe.tcc.andre.tcc.model;

public class DefaultResponse<T> {

	final T data;

	public T getData() {
		return data;
	}

	public DefaultResponse(T data) {
		super();
		this.data = data;
	}
}
