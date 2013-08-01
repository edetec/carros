package com.carros.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;

import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;

/**
 * Session Bean implementation class CarroService
 */
@Stateful
public class CarroService {

	private Map<String, Carro> carros;
	private List<Marca> marcas;
	private Map<Integer, List<Modelo>> modelos;

	public CarroService() {
		initMarcas();
		initModelos();
		initCarros();
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

	public List<Carro> listarTodos() {
		System.out.println(this);
		return new ArrayList<Carro>(carros.values());
	}

	public void salvar(Carro carro) {
		System.out.println(this);
		carros.put(carro.getPlaca(), carro);
	}

	public void remover(Carro carro) {
		carros.remove(carro);
	}

	public Carro buscarCarro(String placa) {
		return carros.get(placa);
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public List<Modelo> buscarModelos(Marca marca) {
		if(marca == null || !modelos.containsKey(marca.getId())){
			return null;
		}
		return modelos.get(marca.getId());
	}
}
