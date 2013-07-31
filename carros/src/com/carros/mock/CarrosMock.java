package com.carros.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carros.model.Carro;
import com.carros.model.Marca;

public class CarrosMock {
	private static List<Carro> carros;
	private static List<Marca> marcas;
	private static Map<Integer, Marca> modelos;

	public CarrosMock() {
		initCarros();
	}

	public List<Carro> getCarros() {
		return carros;
	}

	private void initCarros() {
		Carro carro;
		carros = new ArrayList<Carro>();

		carro = new Carro();
		carro.setCor("Branco");
		carro.setPlaca("AAA1122");
		carros.add(carro);

	}
}
