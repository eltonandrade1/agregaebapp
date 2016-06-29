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

import br.com.sysagrega.controller.Qualificadores.QualificadorCliente;
import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.Enums.TipoCliente;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.Enums.TipoSeguimentoCliente;
import br.com.sysagrega.model.Enums.TipoSituacaoCliente;
import br.com.sysagrega.model.Enums.TipoTamanhoCliente;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IClienteService clienteService;

	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;

	@Produces
	@QualificadorCliente
	private ICliente cliente;

	private boolean viewCliente;
	
	private boolean disableCity;
	
	private String filtroNome;
	
	private String filtroCnpj;
	
	private String filtroSituacao;
	
	private String filtroSeguimento;
	
	private String filtroTipoCliente;

	private List<Estado> estados;

	private List<Cidade> cidades;
	
	//Enums
	private List<String> seguimentos;
	
	private List<String> tamanhos;
	
	private List<String> tiposClientes;
	
	private List<String> situacoes;

	private List<Cliente> clientes;

	@PostConstruct
	public void inicializar() {
		
		estados = new ArrayList<>();
		cidades = new ArrayList<>();
		
		//Carrega lista de estados
		estados = estadoService.getAllEstados();

		//Carrega Enums do Objeto Cliente
		carregarEnumsClientes();
		
		// Carregando lista de profissionais
		if(FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_CLIENTE)) {
			
			carregarTodosClientes();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.EDIT_CLIENTE)) {
			
			this.cliente = FacesUtil.getClienteSession();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.NOVO_CLIENTE)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_CLIENTE)) {
			this.cliente = FacesUtil.getClienteSession();
			carregarCidadesPorEstado();
			viewCliente = true;
		}
	}

	private void limparObjeto() {
		
		this.cliente = new Cliente();
		this.cliente.setEndereco(new Endereco());
		disableCity = true;
		
	}

	public void carregarCidadesPorEstado() {

		cidades = cidadeService.getCidadesByEstadoId(this.cliente.getEndereco().getEstado().getId());
		disableCity = false;

	}
	

	/**
	 * Metodo realiza a persistência de um objeto cliente
	 * 
	 * @param cliente
	 * @author Elton
	 */
	public void salvarCliente() {

		try {

			this.clienteService.salvar(this.cliente);
			limparObjeto();
			FacesUtil.addInfoMessage("Cliente salvo com sucesso.");

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	/**
	 * Metodo realiza atualização de um objeto cliente
	 * 
	 * @param cliente
	 * @return cliente
	 * @author Elton
	 */
	public void atualizarCliente() {
		
		try {
			
			this.clienteService.atualizarCliente(this.cliente);
			FacesUtil.addInfoMessage("Cliente atualizado com sucesso.");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * Método realiza a exclusão de um objeto cliente
	 * @author Elton
	 */
	public void excluirCliente() {
		
		try {
			
			this.clienteService.excluirCliente(this.cliente);
			carregarTodosClientes();
			FacesUtil.addInfoMessage("Cliente excluido com sucesso!");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}


	/**
	 * Carregar todos os clientes cadastrados no sistema para tela de
	 * consulta
	 * @author Elton
	 * 
	 */
	private void carregarTodosClientes() {

		clientes = new ArrayList<>();
		clientes = clienteService.getAllClientes();

	}
	
	/**
	 * Método redireciona para a tela de edição do objeto cliente
	 * passando o mesmo na sessão
	 * @return editar_cliente
	 * @author Elton
	 */
	public String redirectEditCliente() {
		if(this.cliente != null) {
			
			FacesUtil.addParamSession(TipoPagina.EDIT_CLIENTE);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("cliente", this.cliente);
			
			isEditCliente();
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um cliente!");
			return null;
			
		}
		
		return "editar_cliente";
		
	}
	/**
	 * Método redireciona para tela de visualização do cliente
	 * @return editar_cliente
	 * @author Elton
	 */
	public String visualizarCliente() {
		
		if(this.cliente != null) {
			
			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_CLIENTE);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("cliente", this.cliente);
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um cliente!");
			return null;
			
		}
		
		return "editar_cliente";
		
	}
	
	/**
	 * Método valida se é uma edição do objeto cliente
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isEditCliente() {
		
		return this.cliente.isExistente() && !viewCliente;
		
	}
	
	/**
	 * Método valida se é um novo objeto cliente
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isNewCliente() {
		
		return this.cliente.isNovo();
		
	}
	
	public void filtrarClientes() {
		
		clientes = new ArrayList<>();
		clientes = this.clienteService.getClienteByFilter(this.filtroNome, this.filtroCnpj, this.filtroSituacao, this.filtroSeguimento, this.filtroTipoCliente);
		
	}
	
	/**
	 * Método carrega os enums para o objeto cliente
	 */
	private void carregarEnumsClientes() {
		
		seguimentos = new ArrayList<>();
		tamanhos = new ArrayList<>();
		tiposClientes = new ArrayList<>();
		situacoes = new ArrayList<>();
		
		// Carrega tipos de seguimento (Enum)
		for (TipoSeguimentoCliente seg : TipoSeguimentoCliente.values()) {

			seguimentos.add(seg.getDescricao());

		}
		
		// Carrega tipos tamanho
		for (TipoTamanhoCliente t : TipoTamanhoCliente.values()) {
			
			tamanhos.add(t.getDescricao());
			
		}
		
		//Carrrega tipos cliente
		for (TipoCliente tipo : TipoCliente.values()) {
			
			tiposClientes.add(tipo.getDescricao());
			
		}
		
		//Carrega tipos situações
		for (TipoSituacaoCliente sit : TipoSituacaoCliente.values()) {
			
			situacoes.add(sit.getDescricao());
			
		}
		
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
	 * @return the cliente
	 */
	public ICliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the viewCliente
	 */
	public boolean isViewCliente() {
		return viewCliente;
	}

	/**
	 * @param viewCliente the viewCliente to set
	 */
	public void setViewCliente(boolean viewCliente) {
		this.viewCliente = viewCliente;
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
	 * @return the seguimentos
	 */
	public List<String> getSeguimentos() {
		return seguimentos;
	}

	/**
	 * @param seguimentos the seguimentos to set
	 */
	public void setSeguimentos(List<String> seguimentos) {
		this.seguimentos = seguimentos;
	}

	/**
	 * @return the tamanhos
	 */
	public List<String> getTamanhos() {
		return tamanhos;
	}

	/**
	 * @param tamanhos the tamanhos to set
	 */
	public void setTamanhos(List<String> tamanhos) {
		this.tamanhos = tamanhos;
	}

	/**
	 * @return the tiposClientes
	 */
	public List<String> getTiposClientes() {
		return tiposClientes;
	}

	/**
	 * @param tiposClientes the tiposClientes to set
	 */
	public void setTiposClientes(List<String> tiposClientes) {
		this.tiposClientes = tiposClientes;
	}

	/**
	 * @return the situacoes
	 */
	public List<String> getSituacoes() {
		return situacoes;
	}

	/**
	 * @param situacoes the situacoes to set
	 */
	public void setSituacoes(List<String> situacoes) {
		this.situacoes = situacoes;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the filtroNome
	 */
	public String getFiltroNome() {
		return filtroNome;
	}

	/**
	 * @param filtroNome the filtroNome to set
	 */
	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	/**
	 * @return the filtroSituacao
	 */
	public String getFiltroSituacao() {
		return filtroSituacao;
	}

	/**
	 * @param filtroSituacao the filtroSituacao to set
	 */
	public void setFiltroSituacao(String filtroSituacao) {
		this.filtroSituacao = filtroSituacao;
	}

	/**
	 * @return the filtroSeguimento
	 */
	public String getFiltroSeguimento() {
		return filtroSeguimento;
	}

	/**
	 * @param filtroSeguimento the filtroSeguimento to set
	 */
	public void setFiltroSeguimento(String filtroSeguimento) {
		this.filtroSeguimento = filtroSeguimento;
	}

	/**
	 * @return the filtroTipoCliente
	 */
	public String getFiltroTipoCliente() {
		return filtroTipoCliente;
	}

	/**
	 * @param filtroTipoCliente the filtroTipoCliente to set
	 */
	public void setFiltroTipoCliente(String filtroTipoCliente) {
		this.filtroTipoCliente = filtroTipoCliente;
	}
}
