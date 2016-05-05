package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysagrega.controller.Qualificadores.QualificadorProfissional;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.Enums.TiposContaBancaria;
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

	private Estado estado;

	private List<Estado> estados;

	private List<Cidade> cidades;
	
	private List<Banco> bancos;
	
	private List<String> tiposConta;
	
	@PostConstruct
	public void inicializar() {
		
		//this.profissional = new Profissional();
		//this.profissional.setEndereco(new Endereco());
		//this.profissional.setDadosBancarios(new DadosBancarios());
		
		estados = new ArrayList<>();
		cidades = new ArrayList<>();
		bancos = new ArrayList<>();
		tiposConta = new ArrayList<>();

		// Carregando lista de estados
		estados = estadoService.getAllEstados();
		
		//Carrega lista de bancos
		bancos = bancoService.getAllBancos();
		
		//Carrega Tipos de conta (Enum)
		for (TiposContaBancaria tipos : TiposContaBancaria.values()) {
			
			tiposConta.add(tipos.getDescricao());
			
		}
		//Controle de renderização de cidades (exibe conforme escolha do estado)
		//isDisableCidades();

		limparObjeto();

	}

	private void limparObjeto() {

		this.profissional = new Profissional();
		this.profissional.setEndereco(new Endereco());
		this.profissional.setDadosBancarios(new DadosBancarios());

	}

	public void carregarCidadesPorEstado() {

		System.out.println("@@@Chamou Método@@@");
		cidades = cidadeService.getCidadesByEstadoId(profissional.getEndereco().getEstado().getId());
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
	
	public Boolean isDisableCidades() {
		
		if(profissional.getEndereco().getEstado() == null || profissional.getEndereco().getEstado().getId() == null) {
			
			return true;
			
		}
		
		return false;
		
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
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
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
	 * @param bancos the bancos to set
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
	 * @param tiposConta the tiposConta to set
	 */
	public void setTiposConta(List<String> tiposConta) {
		this.tiposConta = tiposConta;
	}
}