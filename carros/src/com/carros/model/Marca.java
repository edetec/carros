package com.carros.model;

import java.io.Serializable;

public class Marca implements Serializable{
	private static final long serialVersionUID = -5194553209002018857L;
	private Integer id;
	private String descricao;

	public Marca(Integer id) {
		this.id = id;
	}

	public Marca() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
