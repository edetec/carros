package com.carros.service;

import java.util.List;

import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;

public interface CarrosService {
	List<Carro> listarTodos();
	void salvar(Carro carro);
	void remover(Carro carro);
	Carro buscarCarro(String placa);
	List<Marca> getMarcas();
	List<Modelo> getModelos(Marca marca);
}
