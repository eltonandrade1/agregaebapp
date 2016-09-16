package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.controller.Qualificadores.QualificadorProposta;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.ItensControleContrato;
import br.com.sysagrega.model.imp.Parcela;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IContratoService contratoService;

	@Produces
	@QualificadorProposta
	private Contrato contrato;

	private boolean viewContrato;

	// Filtros consultas
	private String nrContrato;

	private String filtroCliente;

	private List<Contrato> contratos;

	// Objetos do Contrato
	private ItensControleContrato itenControleContrato;

	private Parcela parcela;

	// Listas dos objetos custos
	private Set<ItensControleContrato> itens;

	private Set<Parcela> parcelas;
	
	private ItensControleContrato itenSelecionado;
	

	@PostConstruct
	public void inicializar() {

		// Carregando lista de profissionais
		if (FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_CONTRATO)) {

			carregarTodosContratos();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.EDIT_CONTRATO)) {

			this.contrato = FacesUtil.getContratoSession();
			limparObjeto();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.NOVO_CONTRATO)) {

			limparObjeto();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_CONTRATO)) {
			
			this.contrato = FacesUtil.getContratoSession();
			limparObjeto();
			viewContrato = true;
			
		} 
	}

	private void limparObjeto() {

		this.contrato = new Contrato();
		this.itenControleContrato = new ItensControleContrato();
		this.parcela = new Parcela();

		// Listas dos objetos do contrato
		this.itens = new HashSet<>();
		this.parcelas = new HashSet<>();

		this.itenControleContrato.setParcelas(this.parcelas);
		this.contrato.setItensControleContratos(this.itens);
		
	}

	public void salvarContrato() {

		try {

			this.contratoService.salvar(this.contrato);
			limparObjeto();
			FacesUtil.addInfoMessage("Contrato salvo com sucesso.");

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	public void atualizarContrato() {

		try {

			this.contratoService.atualizarContrato(this.contrato);
			FacesUtil.addInfoMessage("Contrato atualizado com sucesso.");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	public void excluirContrato() {

		try {

			this.contratoService.excluirContrato(this.contrato);
			carregarTodosContratos();
			FacesUtil.addInfoMessage("Contrato excluido com sucesso!");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	private void carregarTodosContratos() {

		contratos = new ArrayList<>();
		contratos = contratoService.getAllContratos();

	}

	/**
	 * Método redireciona para a tela de edição do objeto contrato passando o
	 * mesmo na sessão
	 * 
	 * @return editar_contrato
	 * @author Elton
	 */
	public String redirectEditProposta() {
		if (this.contrato != null) {

			FacesUtil.addParamSession(TipoPagina.EDIT_CONTRATO);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("contrato", this.contrato);

			isEditContrato();

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um contrato!");
			return null;

		}

		return "editar_contrato";

	}

	/**
	 * Método redireciona para tela de visualização do contrato
	 * 
	 * @return editar_contrato
	 * @author Elton
	 */
	public String visualizarContrato() {

		if (this.contrato != null) {

			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_CONTRATO);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("contrato", this.contrato);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um contrato!");
			return null;

		}

		return "editar_contrato";

	}

	public Boolean isEditContrato() {

		return this.contrato.isExistente() && !viewContrato;

	}

	public Boolean isNewContrato() {

		return this.contrato.isNovo();

	}

	public void filtrarContratos() {

		contratos = new ArrayList<>();

		try {

			contratos = this.contratoService.getContratoByFilter(this.nrContrato, this.filtroCliente);

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}
	
	public void adicionarItenContrato() {
		
		this.itenControleContrato.setParcelas(new HashSet<>());
		this.itenControleContrato = new ItensControleContrato();
		
	}
	
	public void adicionarParcela() {
		
		this.itenSelecionado.getParcelas();
		this.parcela = new Parcela();
		
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public boolean isViewContrato() {
		return viewContrato;
	}

	public void setViewContrato(boolean viewContrato) {
		this.viewContrato = viewContrato;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	/**
	 * @return the filtroCliente
	 */
	public String getFiltroCliente() {
		return filtroCliente;
	}

	/**
	 * @param filtroCliente
	 *            the filtroCliente to set
	 */
	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	/**
	 * @return the nrContrato
	 */
	public String getNrContrato() {
		return nrContrato;
	}

	/**
	 * @param nrContrato the nrContrato to set
	 */
	public void setNrContrato(String nrContrato) {
		this.nrContrato = nrContrato;
	}

	/**
	 * @return the itenControleContrato
	 */
	public ItensControleContrato getItenControleContrato() {
		return itenControleContrato;
	}

	/**
	 * @param itenControleContrato the itenControleContrato to set
	 */
	public void setItenControleContrato(ItensControleContrato itenControleContrato) {
		this.itenControleContrato = itenControleContrato;
	}

	/**
	 * @return the parcela
	 */
	public Parcela getParcela() {
		return parcela;
	}

	/**
	 * @param parcela the parcela to set
	 */
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	/**
	 * @return the itens
	 */
	public Set<ItensControleContrato> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(Set<ItensControleContrato> itens) {
		this.itens = itens;
	}

	/**
	 * @return the parcelas
	 */
	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	/**
	 * @param parcelas the parcelas to set
	 */
	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	/**
	 * @return the itenSelecionado
	 */
	public ItensControleContrato getItenSelecionado() {
		return itenSelecionado;
	}

	/**
	 * @param itenSelecionado the itenSelecionado to set
	 */
	public void setItenSelecionado(ItensControleContrato itenSelecionado) {
		this.itenSelecionado = itenSelecionado;
	}
	
	
}
