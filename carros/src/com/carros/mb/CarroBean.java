package com.carros.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;
import com.carros.service.CarroService;

/**
 * 
 * <p>
 * Esse componente resolve o fluxo de navegação e liga os componentes visuais
 * com os dados.
 * </p>
 * 
 */
@ManagedBean
@SessionScoped
public class CarroBean implements Serializable {
	private static final long serialVersionUID = 7897003741034575700L;

	private String placaSelecionado;

	@EJB
	private CarroService service;

	/**
	 * Lista com o(s) <code>Carro</code>(s) apresentandas no
	 * <code>Datatable</code>.
	 */
	private List<Carro> carros;

	private List<Modelo> modelos;

	/**
	 * Referência para o carro utiliza na inclusão (nova) ou edição.
	 */
	private Carro carro;

	private Marca marca;

	public CarroBean() {
	}

	public void incluir() {
		if (!FacesContext.getCurrentInstance().getPartialViewContext()
				.isAjaxRequest()) {
			System.out.println("incluir: " + marca);
			carro = new Carro();
			marca = null;
		}
	}

	public void editar() {
		if (placaSelecionado == null) {
			return;
		}
		carro = service.buscarCarro(placaSelecionado);
		marca = carro.getMarca();
		marcaSelecinada();

	}

	public String salvar() {
		try {
			carro.setMarca(marca);
			service.salvar(carro);
			atualizarEtado();
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.carro"),
					ex.getMessage());
			return "";
		}
		return "listacarros";
	}

	public String remover() {
		try {
			service.remover(carro);
			atualizarEtado();
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.remover.carro"),
					ex.getMessage());
			return "";
		}
		return "listacarros";
	}

	private void atualizarEtado() {
		carros = service.listarTodos();
		marca = null;
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

	public List<Carro> getCarros() {
		if (carros == null) {
			carros = service.listarTodos();
		}
		return carros;
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("mensagens",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(summary, summary.concat("<br/>")
						.concat(detail)));
	}

	public List<Marca> getMarcas() {
		return service.getMarcas();
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
		carro.setMarca(marca);
	}

	public void marcaSelecinada() {
		modelos = service.buscarModelos(marca);
	}
}
