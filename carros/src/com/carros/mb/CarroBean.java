package com.carros.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.carros.mock.CarrosMock;
import com.carros.model.Carro;
import com.carros.model.Marca;
import com.carros.model.Modelo;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Carro</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instÃ¢ncias dessa classe são controladas pelo <code>JSF</code>. Um objeto é criado ao carregar alguma página do cadastro (Lista / Novo / Editar). Enquanto a página permanecer aberta no browser, o objeto <code>CarroMB</code> permanece no servidor.</p>
 * 
 * <p>Esse componente resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 * 
 */
@ManagedBean
@RequestScoped
public class CarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idSelecionado;
	
	private CarrosMock service; 
	/**
	 * Lista com o(s) <code>Carro</code>(s) apresentandas no <code>Datatable</code>.
	 */
	private List<Carro> carros;
	
	private List<Marca> marcas;
	private List<Modelo> modelos;
	
	/**
	 * Referência para o carro utiliza na inclusão (nova) ou edição.
	 */
	private Carro carro;
	
	
	public CarroBean() {
		service = new CarrosMock();
		marcas = new ArrayList<Marca>();
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Carro getCarro() {
		if(carros == null){
			carros = service.getCarros();
		}
		return carro;
	}
	
	public void incluir(){
		carro = new Carro();
		//log.debug("Pronto pra incluir");
	}
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
//		carro = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Carro> getCarros() {
		if (carros == null) {
			carros = new ArrayList<Carro>();
		}
		return carros;
	}

	
	public String salvar() {
		try {
//			service.save(carro);
		} catch(Exception ex) {
			//log.error("Erro ao salvar carro.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.carro"), ex.getMessage());
			return "";
		}
		//log.debug("Salvour carro "+carro.getId());
		return "listacarros";
	}
	
	public String remover() {
		try {
//			service.remove(carro);
		} catch(Exception ex) {
			//log.error("Erro ao remover carro.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.carro"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu carro "+carro.getId());
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
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	
}
