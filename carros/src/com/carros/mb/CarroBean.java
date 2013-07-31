package com.carros.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import com.carros.mock.CarrosMock;
import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;
import com.carros.service.CarrosService;

/**
 * 
 * <p>Esse componente resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 * 
 */
@ManagedBean
@RequestScoped
public class CarroBean implements Serializable {

	private String placaSelecionado;
	
	private CarrosService service; 
	/**
	 * Lista com o(s) <code>Carro</code>(s) apresentandas no <code>Datatable</code>.
	 */
	private List<Carro> carros;
//	
//	private List<Marca> marcas;
//	private List<Modelo> modelos;
	
	/**
	 * Referência para o carro utiliza na inclusão (nova) ou edição.
	 */
	private Carro carro;
	
	
	public CarroBean() {
		service = CarrosMock.getInstance();
	}
	
	public void setPlacaSelecionado(String placaSelecionado) {
		this.placaSelecionado = placaSelecionado;
	}
	
	public String getPlacaSelecionado() {
		return placaSelecionado;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void incluir(){
		carro = new Carro();
	}
	
	public void editar() {
		if (placaSelecionado == null) {
			return;
		}
		carro = service.buscarCarro(placaSelecionado);
	}
	
	public List<Carro> getCarros() {
		if(carros == null){
			carros = service.listarTodos();
		}
		return carros;
	}

	
	public String salvar() {
		try {
			service.salvar(carro);
		} catch(Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.carro"), ex.getMessage());
			return "";
		}
		return "listacarros";
	}
	
	public String remover() {
		try {
			service.remover(carro);
		} catch(Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.remover.carro"), ex.getMessage());
			return "";
		}
		return "listacarros";
	}
	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}

	public List<Marca> getMarcas() {
		return service.getMarcas();
	}

	public List<Modelo> getModelos() {
		return service.getModelos(carro.getMarca());
	}
	
	public void selecionaModelo(ValueChangeEvent e){
		carro.setMarca((Marca) e.getNewValue());
	}
}
