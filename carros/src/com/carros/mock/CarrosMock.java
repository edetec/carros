package com.carros.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;
import com.carros.service.CarrosService;

public class CarrosMock implements CarrosService {
	private static CarrosMock carrosMock;
	private Map<String, Carro> carros;
	private List<Marca> marcas;
	private Map<Integer, List<Modelo>> modelos;

	private CarrosMock() {
		initMarcas();
		initModelos();
		initCarros();
	}

	public static CarrosService getInstance() {
		if (carrosMock == null) {
			carrosMock = new CarrosMock();
		}
		return carrosMock;
	}

	private void initMarcas() {
		Marca marca;
		marcas = new ArrayList<Marca>();

		marca = new Marca();
		marca.setId(1);
		marca.setDescricao("Chevrolet");
		marcas.add(marca);

		marca = new Marca();
		marca.setId(2);
		marca.setDescricao("Ford");
		marcas.add(marca);

		marca = new Marca();
		marca.setId(3);
		marca.setDescricao("Porsche");
		marcas.add(marca);
	}
	
	private void initModelos(){
		int id = 0;
		Modelo modelo;
		List<Modelo> listaModelos;
		modelos = new HashMap<Integer, List<Modelo>>();
		
		listaModelos = new ArrayList<Modelo>();
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Astra");
		listaModelos.add(modelo);
		
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Celta");
		listaModelos.add(modelo);
		
		modelos.put(marcas.get(0).getId(), listaModelos);
		
		listaModelos = new ArrayList<Modelo>();
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Astra");
		listaModelos.add(modelo);
		
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Celta");
		listaModelos.add(modelo);
		modelos.put(marcas.get(0).getId(), listaModelos);
		
	}

	private void initCarros() {
		Carro carro;
		carros = new HashMap<String, Carro>();

		carro = new Carro();
		carro.setCor("Branco");
		carro.setPlaca("AAA1122");
		carro.setMarca(marcas.get(1));
		carros.put(carro.getPlaca(), carro);

		carro = new Carro();
		carro.setCor("Preto");
		carro.setPlaca("BBB1122");
		carro.setMarca(marcas.get(2));
		carros.put(carro.getPlaca(), carro);

	}

	@Override
	public List<Carro> listarTodos() {
		return new ArrayList(carros.values());
	}

	@Override
	public void salvar(Carro carro) {
		carros.put(carro.getPlaca(), carro);
	}

	@Override
	public void remover(Carro carro) {
		carros.remove(carro);
	}

	@Override
	public Carro buscarCarro(String placa) {
		return carros.get(placa);
	}

	@Override
	public List<Marca> getMarcas() {
		return marcas;
	}

	@Override
	public List<Modelo> getModelos(Marca marca) {
		if(marca == null){
			return new ArrayList<Modelo>();
		}
		return modelos.get(marca.getId());
	}
}
