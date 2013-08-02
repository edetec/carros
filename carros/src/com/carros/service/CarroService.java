package com.carros.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;

/**
 * Session Bean implementation class CarroService
 */
@Stateful
public class CarroService {

	private Map<String, Carro> carros = new HashMap<String, Carro>();
	private List<Marca> marcas;
	private Map<Integer, List<Modelo>> modelos;

	public CarroService() {
			initMarcas();
			initModelos();
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

	private void initModelos() {
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
		modelo.setDescricao("Escort");
		listaModelos.add(modelo);

		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Fiesta");
		listaModelos.add(modelo);
		modelos.put(marcas.get(1).getId(), listaModelos);

		listaModelos = new ArrayList<Modelo>();
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("911");
		listaModelos.add(modelo);
		
		modelo = new Modelo();
		modelo.setId(++id);
		modelo.setDescricao("Boxster");
		listaModelos.add(modelo);
		modelos.put(marcas.get(2).getId(), listaModelos);

	}

	public List<Carro> listarTodos() {
		System.out.println(this);
		return new ArrayList<Carro>(carros.values());
	}

	public void salvar(Carro carro) throws Exception {
		if(!validar(carro)) throw new Exception();
		
		carro.setPlaca(carro.getPlaca().toUpperCase());
		
		carros.put(carro.getPlaca(), carro);
	}

	private boolean validar(Carro carro) {
		if(carro == null){
			return false;
		}
		
		if(carro.getPlaca() == null || !carro.getPlaca().matches("[a-zA-Z]{3}-?\\d{4}")){
			return false;
		}
		
		if(carro.getMarca() == null){
			return false;
		}
		
		if(carro.getModelo() == null){
			return false;
		}
		
		if(carro.getCor() == null){
			return false;
		}
		
		return true;
	}

	public void remover(Carro carro) throws Exception {
		if(carros.remove(carro.getPlaca()) == null)throw new Exception();
	}

	public Carro buscarCarro(String placa) {
		return carros.get(placa);
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public List<Modelo> buscarModelos(Marca marca) {
		if (marca == null || !modelos.containsKey(marca.getId())) {
			return null;
		}
		return modelos.get(marca.getId());
	}
}
