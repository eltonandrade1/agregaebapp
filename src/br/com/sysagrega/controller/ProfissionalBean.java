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

import br.com.sysagrega.controller.Qualificadores.QualificadorProfissional;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.Enums.TiposContaBancaria;
import br.com.sysagrega.model.Enums.TiposPaginas;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.service.IBancoService;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProfissionalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IProfissionalService profissionalService;

	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;

	@Inject
	private IBancoService bancoService;

	@Produces
	@QualificadorProfissional
	private IProfissional profissional;

	private boolean viewProfissional;

	private List<Estado> estados;

	private List<Cidade> cidades;

	private List<Banco> bancos;

	private List<String> tiposConta;

	private List<IProfissional> profissionais;

	private List<Profissional> filtroProfissionais;
	
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
		for (TiposContaBancaria tipos : TiposContaBancaria.values()) {

			tiposConta.add(tipos.getDescricao());

		}
		
		// Carregando lista de profissionais
		if(FacesUtil.getParamSession().equals(TiposPaginas.CONSULTA_PROF)) {
			
			carregarTodosProfissionais();
			
		} else if(FacesUtil.getParamSession().equals(TiposPaginas.EDIT_PROFI)) {
			
			this.profissional = FacesUtil.getProfissionalSession();
			carregarCidadesPorEstado();
			
		} else if(FacesUtil.getParamSession().equals(TiposPaginas.NOVO_PROF)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession().equals(TiposPaginas.VISUALIZAR_PROF)) {
			this.profissional = FacesUtil.getProfissionalSession();
			carregarCidadesPorEstado();
			viewProfissional = true;
		}
	}

	private void limparObjeto() {
		
		this.profissional = new Profissional();
		this.profissional.setEndereco(new Endereco());
		this.profissional.setDadosBancarios(new DadosBancarios());
		
	}

	public void carregarCidadesPorEstado() {

		cidades = cidadeService.getCidadesByEstadoId(this.profissional.getEndereco().getEstado().getId());
		isDisableCidades();

	}
	

	/**
	 * Metodo realiza a persistência de um objeto Profissional
	 * 
	 * @param profissional
	 * @author Elton
	 */
	public void salvarProfissional() {

		try {

			this.profissionalService.salvar(this.profissional);
			limparObjeto();
			FacesUtil.addInfoMessage("Profissional salvo com sucesso.");

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	/**
	 * Metodo realiza atualização de um objeto Profissional
	 * 
	 * @param profissional
	 * @return profissional
	 * @author Elton
	 */
	public void atualizarProfissional() {
		
		try {
			
			this.profissionalService.atualizarProfissional(this.profissional);
			limparObjeto();
			FacesUtil.addInfoMessage("Profissional atualizado com sucesso.");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	
	public void excluirProfissional() {
		
		try {
			
			this.profissionalService.excluirProfissional(this.profissional);
			carregarTodosProfissionais();
			FacesUtil.addInfoMessage("Profissional excluido com sucesso.");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}

	/**
	 * Método controla a renderização do combo cidades, bloqueando o mesmo,
	 * caso não tenha sido informado um estado.
	 * 
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isDisableCidades() {

		if (profissional.getEndereco().getEstado() == null || profissional.getEndereco().getEstado().getId() == null) {

			return true;

		}

		return false;

	}

	/**
	 * Carregar todos os profissionais cadastrados no sistema para tela de
	 * consulta
	 * @author Elton
	 * 
	 */
	private void carregarTodosProfissionais() {

		profissionais = new ArrayList<>();
		profissionais = profissionalService.getAllProfissionals();

	}
	
	public String redirectEditProfissional() {
		if(this.profissional != null) {
			
			FacesUtil.addParamSession(TiposPaginas.EDIT_PROFI);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("profissional", this.profissional);
			
			isEditProfissional();
			
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um profissional!");
			return null;
			
		}
		
		return "editar_profissional";
		
	}
	
	public String visualizarProfissional() {
		if(this.profissional != null) {
			
			FacesUtil.addParamSession(TiposPaginas.VISUALIZAR_PROF);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("profissional", this.profissional);
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um profissional!");
			return null;
			
		}
		
		return "editar_profissional";
		
	}
	
	/**
	 * Método valida se é uma edição do objeto profissional
	 * @param profissional
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isEditProfissional() {
		
		return this.profissional.isExistente() && !viewProfissional;
		
	}
	
	/**
	 * Método valida se é um novo objeto profissional
	 * @param profissional
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isNewProfissional() {
		
		return this.profissional.isNovo();
		
	}

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {

		return estados;

	}

	/**
	 * @param estados
	 *            the estados to set
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
	 *            the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the profissional
	 */
	public IProfissional getProfissional() {
		return profissional;
	}

	/**
	 * @param profissional
	 *            the profissional to set
	 */
	public void setProfissional(IProfissional profissional) {
		this.profissional = profissional;
	}

	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos
	 *            the bancos to set
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
	 *            the tiposConta to set
	 */
	public void setTiposConta(List<String> tiposConta) {
		this.tiposConta = tiposConta;
	}

	/**
	 * @return the profissionais
	 */
	public List<IProfissional> getProfissionais() {
		return profissionais;
	}

	/**
	 * @param profissionais
	 *            the profissionais to set
	 */
	public void setProfissionais(List<IProfissional> profissionais) {
		this.profissionais = profissionais;
	}

	/**
	 * @return the filtroProfissionais
	 */
	public List<Profissional> getFiltroProfissionais() {
		return filtroProfissionais;
	}

	/**
	 * @param filtroProfissionais
	 *            the filtroProfissionais to set
	 */
	public void setFiltroProfissionais(List<Profissional> filtroProfissionais) {
		this.filtroProfissionais = filtroProfissionais;
	}

	/**
	 * @return the viewProfissional
	 */
	public boolean getViewProfissional() {
		return viewProfissional;
	}

	/**
	 * @param viewProfissional the viewProfissional to set
	 */
	public void setViewProfissional(boolean viewProfissional) {
		this.viewProfissional = viewProfissional;
	}
}
