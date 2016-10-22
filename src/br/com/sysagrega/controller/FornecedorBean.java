package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.model.Enums.TipoContaBancaria;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.service.IBancoService;

import br.com.sysagrega.controller.Qualificadores.QualificadorFornecedor;
import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IFornecedorService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IFornecedorService fornecedorService;
	
	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;
	
	@Inject
	private IBancoService bancoService;
	
	@Produces
	@QualificadorFornecedor
	private IFornecedor fornecedor;

	private boolean viewFornecedor;
	
	private boolean disableCity;
	
	private String filtroCnpj;
	
	private String filtroNomeFantasia;
	
	private List<Estado> estados;

	private List<Cidade> cidades;
	
	private List<Banco> bancos;
	
	private List<String> tiposConta;

	private List<Fornecedor> fornecedores;
	
	@PostConstruct
	public void inicializar() {
		
		estados = new ArrayList<>();
		cidades = new ArrayList<>();
		bancos = new ArrayList<>();
		tiposConta = new ArrayList<>();
		
		//Carrega lista de estados
		estados = estadoService.getAllEstados();
		
		// Carrega lista de bancos
		bancos = bancoService.getAllBancos();
		
		// Carrega Tipos de conta (Enum)
		for (TipoContaBancaria tipos : TipoContaBancaria.values()) {

		tiposConta.add(tipos.getDescricao());

		}
		// Carregando lista de fornecedores
		if(FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_FORNEC)) {
			
			carregarTodosFornecedores();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.EDIT_FORNEC)) {
			
			this.fornecedor = FacesUtil.getFornecedorSession();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.NOVO_FORNEC)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_FORNEC)) {
			this.fornecedor = FacesUtil.getFornecedorSession();
			carregarCidadesPorEstado();
			viewFornecedor = true;
		}
	}

	private void limparObjeto() {
		
		this.fornecedor = new Fornecedor();
		this.fornecedor.setEndereco(new Endereco());
		this.fornecedor.setDadosBancarios(new DadosBancarios());
		disableCity = true;
		
	}
	
	public void carregarCidadesPorEstado() {

		cidades = cidadeService.getCidadesByEstadoId(this.fornecedor.getEndereco().getEstado().getId());
		disableCity = false;

	}
	
	/**
	 * Metodo realiza a persistência de um objeto Fornecedor
	 * 
	 * @param Fornecedor
	 * @author Paulo
	 */
	public void salvarFornecedor() {

		try {

			this.fornecedorService.salvar(this.fornecedor);
			limparObjeto();
			FacesUtil.addInfoMessage("Fornecedor salvo com sucesso.");

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	/**
	 * Metodo realiza atualização de um objeto Fornecedor
	 * 
	 * @param Fornecedor
	 * @return Fornecedor
	 * @author Paulo
	 */
	public void atualizarFornecedor() {
		
		try {
			
			this.fornecedorService.atualizarFornecedor(this.fornecedor);
			limparObjeto();
			FacesUtil.addInfoMessage("Fornecedor atualizado com sucesso.");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	
	public void excluirFornecedor() {
		
		try {
			
			this.fornecedorService.excluirFornecedor(this.fornecedor);
			carregarTodosFornecedores();
			FacesUtil.addInfoMessage("Fornecedor excluido com sucesso.");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}

	/**
	 * Método controla a renderização do combo cidades, bloqueando o mesmo,
	 * caso não tenha sido informado um estado.
	 * 
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean isDisableCidades() {

		if (fornecedor.getEndereco().getEstado() == null || fornecedor.getEndereco().getEstado().getId() == null) {

			return true;

		}

		return false;

	}

	/**
	 * Carregar todos os fornecedores cadastrados no sistema para tela de
	 * consulta
	 * @author Paulo
	 * 
	 */
	private void carregarTodosFornecedores() {

		fornecedores = new ArrayList<>();
		fornecedores = fornecedorService.getAllFornecedor();

	}
	
	public String redirectEditFornecedor() {
		if(this.fornecedor != null) {
			
			FacesUtil.addParamSession(TipoPagina.EDIT_FORNEC);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("fornecedor", this.fornecedor);
			
			isEditFornecedor();
			
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um fornecedor!");
			return null;
			
		}
		
		return "editar_fornecedor";
		
	}
	
	public String visualizarFornecedor() {
		if(this.fornecedor != null) {
			
			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_FORNEC);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("fornecedor", this.fornecedor);
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um fornecedor!");
			return null;
			
		}
		
		return "editar_fornecedor";
		
	}
	
	/**
	 * Método valida se é uma edição do objeto Fornecedor
	 * @param Fornecedor
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean isEditFornecedor() {
		
		return this.fornecedor.isExistente() && !viewFornecedor;
		
	}
	
	/**
	 * Método valida se é um novo objeto Fornecedor
	 * @param Fornecedor
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean isNewFornecedor() {
		
		return this.fornecedor.isNovo();
		
	}
	
	public void filtrarFornecedores() {
		
		fornecedores = new ArrayList<>();
		fornecedores = this.fornecedorService.getFornecedorByFilter(this.filtroCnpj, this.filtroNomeFantasia);
		
	}
	

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {

		return estados;

	}

	/**
	 * @param estados 
	 * the estados to set
	 */
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	/**
	 * @return the cidades
	 */
	public List<Cidade> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades
	 * the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the Fornecedor
	 */
	public IFornecedor getFornecedor() {
		return fornecedor;
	}

	/**
	 * @param Fornecedor
	 *  the Fornecedor to set
	 */
	public void setFornecedor(IFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos
	 * the bancos to set
	 */
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}
	
	/**
	 * @return the tiposConta
	 */
	public List<String> getTiposConta() {
		return tiposConta;
	}

	/**
	 * @param tiposConta
	 * the tiposConta to set
	 */
	public void setTiposConta(List<String> tiposConta) {
		this.tiposConta = tiposConta;
	}
	
	/**
	 * @return the fornecedores
	 */
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	/**
	 * @param profissionais
	 * the fornecedores to set
	 */
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	/**
	 * @return the viewFornecedor
	 */
	public boolean getViewFornecedor() {
		return viewFornecedor;
	}

	/**
	 * @param viewFornecedor the viewFornecedor to set
	 */
	public void setViewFornecedor(boolean viewFornecedor) {
		this.viewFornecedor = viewFornecedor;
	}

	/**
	 * @return the disableCity
	 */
	public boolean isDisableCity() {
		return disableCity;
	}

	/**
	 * @param disableCity the disableCity to set
	 */
	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	/**
	 * @return the filtroCnpj
	 */
	public String getFiltroCnpj() {
		return filtroCnpj;
	}

	/**
	 * @param filtroCnpj the filtroCnpj to set
	 */
	public void setFiltroCnpj(String filtroCnpj) {
		this.filtroCnpj = filtroCnpj;
	}

	/**
	 * @return the filtroNomeFantasia
	 */
	public String getFiltroNomeFantasia() {
		return filtroNomeFantasia;
	}

	/**
	 * @param the filtroNomeFantasia
	 */
	public void setFiltroNomeFantasia(String filtroNomeFantasia) {
		this.filtroNomeFantasia = filtroNomeFantasia;
	}
}
