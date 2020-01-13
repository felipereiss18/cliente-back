package br.com.felipe.surittec.cliente.entity;

import java.io.Serializable;

public abstract class AbstractEntity<T> implements Serializable {

	private T id;

	public T getId(){
		return id;
	}

	public AbstractEntity(){}
}
