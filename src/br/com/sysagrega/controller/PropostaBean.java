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
import br.com.sysagrega.model.IPrecificacao;
import br.com.sysagrega.model.Enums.TipoCustoAdm;
import br.com.sysagrega.model.Enums.TipoCustoBdiComissao;
import br.com.sysagrega.model.Enums.TipoCustoImpostos;
import br.com.sysagrega.model.Enums.TipoDeslocamento;
import br.com.sysagrega.model.Enums.TipoDespesaOperacional;
import br.com.sysagrega.model.Enums.TipoDespesaSeguranca;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.Enums.TipoProposta;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Precificacao;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PropostaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IPropostaService propostaService;

	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;

	@Produces
	@QualificadorCliente
	private Proposta proposta;

	private boolean viewProposta;

	private boolean disableCity;

	private Long filtroNumeroProposta;

	private Long filtroNumeroPrecificacao;

	private List<Estado> estados;

	private List<Cidade> cidades;

	private List<Proposta> propostas;

	// Enums
	private List<String> custosAdm;

	private List<String> deslocamentos;

	private List<String> despesasOperacional;

	private List<String> despesasSeguranca;

	private List<String> tiposPropostas;

	private List<String> listaImpostos;

	private List<String> bdiComissoes;

	// Obejtos Custos
	private CustoExecucao custoExecucao;

	private CustoAdministrativo custoAdministrativo;

	private CustoBdiComissao custoBdiComissao;

	private CustoSeguranca custoSeguranca;

	private CustoDeslocamento custoDeslocamento;

	private CustoOperacional custoOperacional;

	//Listas dos objetos custos
	private List<CustoExecucao> listaCustos;
	
	private List<CustoDeslocamento> listaCustosDeslocamentos;
	
	private List<CustoOperacional> listaCustosOperacionais;
	
	private List<CustoSeguranca> listaCustosSeguranca;
	
	private List<CustoAdministrativo> listaCustosAdm;
	
	private List<CustoBdiComissao> listaCustosBdiComissao;
	
	
	
	

	private IPrecificacao precificacao;

	@PostConstruct
	public void inicializar() {

		estados = new ArrayList<>();
		cidades = new ArrayList<>();

		// Carrega lista de estados
		estados = estadoService.getAllEstados();

		// Carrega Enums do Objeto Cliente
		carregarEnumsProposta();

		// Carregando lista de profissionais
		if (FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_PROPOSTA)) {

			carregarTodasPropostas();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.EDIT_PROPOSTA)) {

			this.proposta = FacesUtil.getPropostaSession();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.NOVA_PROPOSTA)) {

			limparObjeto();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_PROPOSTA)) {
			this.proposta = FacesUtil.getPropostaSession();
			carregarCidadesPorEstado();
			viewProposta = true;
		}
	}

	private void limparObjeto() {

		this.proposta = new Proposta();
		this.custoExecucao = new CustoExecucao();
		this.custoAdministrativo = new CustoAdministrativo();
		this.custoBdiComissao = new CustoBdiComissao();
		this.custoSeguranca = new CustoSeguranca();
		this.custoDeslocamento = new CustoDeslocamento();
		this.custoOperacional = new CustoOperacional();

		//Listas dos objetos de custo
		this.listaCustos = new ArrayList<>();
		this.listaCustosDeslocamentos = new ArrayList<>();
		this.listaCustosOperacionais = new ArrayList<>();
		this.listaCustosSeguranca = new ArrayList<>();
		this.listaCustosAdm = new ArrayList<>();
		this.listaCustosBdiComissao = new ArrayList<>();
		
		
		this.precificacao = new Precificacao();
		this.precificacao.setCustos(this.listaCustos);
		this.precificacao.setDespesasDeslocamentos(this.listaCustosDeslocamentos);
		this.precificacao.setDespesasOperacionais(this.listaCustosOperacionais);
		this.precificacao.setDespesasSeguranca(this.listaCustosSeguranca);
		this.precificacao.setDespesasAdministrativas(this.listaCustosAdm);
		this.precificacao.setDespesasBdiComissao(this.listaCustosBdiComissao);

		this.proposta.setPrecificacao(precificacao);
		this.proposta.setEstado(new Estado());
		this.proposta.setCidade(new Cidade());

		disableCity = true;

	}

	public void somarValorCustosExecucao() {

		double totalPorItem = 0;

		if (this.custoExecucao.getQuantidade() > 0) {

			totalPorItem += this.custoExecucao.getValorUnitario() * this.custoExecucao.getQuantidade();

		} 

		this.custoExecucao.setValorTotal(totalPorItem);

		calcularValorTotalCustosExecucao();

		custoExecucao = new CustoExecucao();

	}

	public void somarValorCustosDeslocamento() {

		double totalPorItem = 0;

		if (this.custoDeslocamento.getQuantidade() > 0) {

			totalPorItem += this.custoDeslocamento.getValorUnitario() * this.custoDeslocamento.getQuantidade();

		} 

		this.custoDeslocamento.setValorTotal(totalPorItem);

		calcularValorTotalCustosDeslocamento();

		custoDeslocamento = new CustoDeslocamento();

	}

	public void somarValorCustosOperacionais() {

		double totalPorItem = 0;

		if (this.custoOperacional.getQuantidade() > 0) {

			totalPorItem += this.custoOperacional.getValorUnitario() * this.custoOperacional.getQuantidade();

		} 

		this.custoOperacional.setValorTotal(totalPorItem);

		calcularValorTotalCustosOperacionais();

		custoOperacional = new CustoOperacional();

	}

	public void somarValorCustosSeguranca() {

		double totalPorItem = 0;

		if (this.custoSeguranca.getQuantidade() > 0) {

			totalPorItem += this.custoSeguranca.getValorUnitario() * this.custoSeguranca.getQuantidade();

		} 

		this.custoSeguranca.setValorTotal(totalPorItem);

		calcularValorTotalCustosSeguranca();

		custoSeguranca = new CustoSeguranca();

	}

	public void somarValorCustosAdministrativo() {

		double totalPorItem = 0;

		if(this.custoAdministrativo.getDescricao().equals(TipoCustoAdm.ADMINISTRACAO.getDescricao())) {
			
			if(this.custoAdministrativo.getQuantidade() > 0) {
				
				totalPorItem += (this.proposta.getPrecificacao().getValorTotalCustosExecucao() * this.custoAdministrativo.getValorUnitario())/100;
				
			}
			
			
		} else if (this.custoAdministrativo.getQuantidade() > 0) {

			totalPorItem += this.custoAdministrativo.getValorUnitario() * this.custoAdministrativo.getQuantidade();

		} 
		
		this.custoAdministrativo.setValorTotal(totalPorItem);

		calcularValorTotalCustosAdministrativos();

		custoAdministrativo = new CustoAdministrativo();

	}

	public void calcularValorTotalCustosAdministrativos() {

		this.proposta.getPrecificacao()
				.setValorTotalCustosAdministrativos(this.proposta.getPrecificacao().getCalculoValorTotalCustosAdministraticos());

	}

	public void calcularValorTotalCustosSeguranca() {

		this.proposta.getPrecificacao()
				.setValorTotalCustosSeguranca(this.proposta.getPrecificacao().getCalculoValorTotalCustosSeguranca());

	}

	public void calcularValorTotalCustosDeslocamento() {

		this.proposta.getPrecificacao()
				.setValorTotalCustosDesclocamento(this.proposta.getPrecificacao().getCalculoValorTotalCustosDeslocamento());

	}

	public void calcularValorTotalCustosOperacionais() {

		this.proposta.getPrecificacao()
				.setValorTotalCustosOperacionais(this.proposta.getPrecificacao().getCalculoValorTotalCustosOperacionais());

	}

	public void calcularValorTotalCustosExecucao() {

		this.proposta.getPrecificacao()
				.setValorTotalCustosExecucao(this.proposta.getPrecificacao().getCalculoValorTotalCustosExecucao());

	}

	public void carregarCidadesPorEstado() {

		cidades = cidadeService.getCidadesByEstadoId(this.proposta.getEstado().getId());
		disableCity = false;

	}

	public void salvarProposta() {

		try {

			this.propostaService.salvar(this.proposta);
			limparObjeto();
			FacesUtil.addInfoMessage("Proposta salva com sucesso.");

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	public void atualizarProposta() {

		try {

			this.propostaService.atualizarProposta(this.proposta);
			limparObjeto();
			FacesUtil.addInfoMessage("Proposta atualizada com sucesso.");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	public void excluirProposta() {

		try {

			this.propostaService.excluirProposta(this.proposta);
			carregarTodasPropostas();
			FacesUtil.addInfoMessage("Proposta excluida com sucesso!");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
	}

	private void carregarTodasPropostas() {

		propostas = new ArrayList<>();
		propostas = propostaService.getAllPropostas();

	}

	/**
	 * Método redireciona para a tela de edição do objeto proposta passando o
	 * mesmo na sessão
	 * 
	 * @return editar_proposta
	 * @author Elton
	 */
	public String redirectEditProposta() {
		if (this.proposta != null) {

			FacesUtil.addParamSession(TipoPagina.EDIT_PROPOSTA);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("proposta", this.proposta);

			isEditProposta();

		} else {

			FacesUtil.addErrorMessage("Favor selecionar uma proposta!");
			return null;

		}

		return "editar_proposta";

	}

	public String getFormatedCurrentDate() {
		return DateUtil.getFormatedCurrentDate();
	}

	/**
	 * Método redireciona para tela de visualização da proposta
	 * 
	 * @return editar_proposta
	 * @author Elton
	 */
	public String visualizarCliente() {

		if (this.proposta != null) {

			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_PROPOSTA);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("proposta", this.proposta);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar uma proposta!");
			return null;

		}

		return "editar_proposta";

	}

	public Boolean isEditProposta() {

		return this.proposta.isExistente() && !viewProposta;

	}

	public Boolean isNewProposta() {

		return this.proposta.isNovo();

	}

	public void filtrarPropostas() {

		propostas = new ArrayList<>();
		propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroNumeroPrecificacao);

	}

	/**
	 * Método carrega os enums para o objeto proposta
	 */
	private void carregarEnumsProposta() {

		custosAdm = new ArrayList<>();
		deslocamentos = new ArrayList<>();
		despesasOperacional = new ArrayList<>();
		despesasSeguranca = new ArrayList<>();
		tiposPropostas = new ArrayList<>();
		listaImpostos = new ArrayList<>();
		bdiComissoes = new ArrayList<>();

		// Carrega tipos de custos adm
		for (TipoCustoAdm custos : TipoCustoAdm.values()) {

			this.custosAdm.add(custos.getDescricao());

		}

		// Carrega tipo deslocamento
		for (TipoDeslocamento des : TipoDeslocamento.values()) {

			this.deslocamentos.add(des.getDescricao());

		}

		// Carrrega tipos depesas operacionais
		for (TipoDespesaOperacional despOp : TipoDespesaOperacional.values()) {

			this.despesasOperacional.add(despOp.getDescricao());

		}

		// Carrega tipos despesas segurança
		for (TipoDespesaSeguranca seg : TipoDespesaSeguranca.values()) {

			this.despesasSeguranca.add(seg.getDescricao());

		}

		// Carrega tipos de prposta
		for (TipoProposta props : TipoProposta.values()) {

			this.tiposPropostas.add(props.getDescricao());

		}

		// Tipos BDI e Comissões
		for (TipoCustoBdiComissao bdiComissao : TipoCustoBdiComissao.values()) {

			this.bdiComissoes.add(bdiComissao.getDescricao());

		}

		// Tipos custos impostos
		for (TipoCustoImpostos impostos : TipoCustoImpostos.values()) {

			this.listaImpostos.add(impostos.getDescricao());

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
	 * @param disableCity
	 *            the disableCity to set
	 */
	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	/**
	 * @return the proposta
	 */
	public Proposta getProposta() {
		return proposta;
	}

	/**
	 * @param proposta
	 *            the proposta to set
	 */
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	/**
	 * @return the viewProposta
	 */
	public boolean isViewProposta() {
		return viewProposta;
	}

	/**
	 * @param viewProposta
	 *            the viewProposta to set
	 */
	public void setViewProposta(boolean viewProposta) {
		this.viewProposta = viewProposta;
	}

	/**
	 * @return the filtroNumeroProposta
	 */
	public Long getFiltroNumeroProposta() {
		return filtroNumeroProposta;
	}

	/**
	 * @param filtroNumeroProposta
	 *            the filtroNumeroProposta to set
	 */
	public void setFiltroNumeroProposta(Long filtroNumeroProposta) {
		this.filtroNumeroProposta = filtroNumeroProposta;
	}

	/**
	 * @return the filtroNumeroPrecificacao
	 */
	public Long getFiltroNumeroPrecificacao() {
		return filtroNumeroPrecificacao;
	}

	/**
	 * @param filtroNumeroPrecificacao
	 *            the filtroNumeroPrecificacao to set
	 */
	public void setFiltroNumeroPrecificacao(Long filtroNumeroPrecificacao) {
		this.filtroNumeroPrecificacao = filtroNumeroPrecificacao;
	}

	/**
	 * @return the propostas
	 */
	public List<Proposta> getPropostas() {
		return propostas;
	}

	/**
	 * @param propostas
	 *            the propostas to set
	 */
	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	/**
	 * @return the custosAdm
	 */
	public List<String> getCustosAdm() {
		return custosAdm;
	}

	/**
	 * @param custosAdm
	 *            the custosAdm to set
	 */
	public void setCustosAdm(List<String> custosAdm) {
		this.custosAdm = custosAdm;
	}

	/**
	 * @return the deslocamentos
	 */
	public List<String> getDeslocamentos() {
		return deslocamentos;
	}

	/**
	 * @param deslocamentos
	 *            the deslocamentos to set
	 */
	public void setDeslocamentos(List<String> deslocamentos) {
		this.deslocamentos = deslocamentos;
	}

	/**
	 * @return the despesasOperacional
	 */
	public List<String> getDespesasOperacional() {
		return despesasOperacional;
	}

	/**
	 * @param despesasOperacional
	 *            the despesasOperacional to set
	 */
	public void setDespesasOperacional(List<String> despesasOperacional) {
		this.despesasOperacional = despesasOperacional;
	}

	/**
	 * @return the despesasSeguranca
	 */
	public List<String> getDespesasSeguranca() {
		return despesasSeguranca;
	}

	/**
	 * @param despesasSeguranca
	 *            the despesasSeguranca to set
	 */
	public void setDespesasSeguranca(List<String> despesasSeguranca) {
		this.despesasSeguranca = despesasSeguranca;
	}

	/**
	 * @return the tiposPropostas
	 */
	public List<String> getTiposPropostas() {
		return tiposPropostas;
	}

	/**
	 * @param tiposPropostas
	 *            the tiposPropostas to set
	 */
	public void setTiposPropostas(List<String> tiposPropostas) {
		this.tiposPropostas = tiposPropostas;
	}

	/**
	 * @return the custoPrecificacao
	 */
	public CustoExecucao getCustoExecucao() {
		return custoExecucao;
	}

	/**
	 * @param custoPrecificacao
	 *            the custoPrecificacao to set
	 */
	public void setCustoExecucao(CustoExecucao custoExecucao) {
		this.custoExecucao = custoExecucao;
	}

	/**
	 * @return the listaCustos
	 */
	public List<CustoExecucao> getListaCustos() {
		return listaCustos;
	}

	/**
	 * @param listaCustos
	 *            the listaCustos to set
	 */
	public void setListaCustos(List<CustoExecucao> listaCustos) {
		this.listaCustos = listaCustos;
	}

	/**
	 * @return the precificacao
	 */
	public IPrecificacao getPrecificacao() {
		return precificacao;
	}

	/**
	 * @param precificacao
	 *            the precificacao to set
	 */
	public void setPrecificacao(IPrecificacao precificacao) {
		this.precificacao = precificacao;
	}

	/**
	 * @return the listaImpostos
	 */
	public List<String> getListaImpostos() {
		return listaImpostos;
	}

	/**
	 * @param listaImpostos
	 *            the listaImpostos to set
	 */
	public void setListaImpostos(List<String> listaImpostos) {
		this.listaImpostos = listaImpostos;
	}

	/**
	 * @return the bdiComissoes
	 */
	public List<String> getBdiComissoes() {
		return bdiComissoes;
	}

	/**
	 * @param bdiComissoes
	 *            the bdiComissoes to set
	 */
	public void setBdiComissoes(List<String> bdiComissoes) {
		this.bdiComissoes = bdiComissoes;
	}

	/**
	 * @return the custoAdministrativo
	 */
	public CustoAdministrativo getCustoAdministrativo() {
		return custoAdministrativo;
	}

	/**
	 * @param custoAdministrativo
	 *            the custoAdministrativo to set
	 */
	public void setCustoAdministrativo(CustoAdministrativo custoAdministrativo) {
		this.custoAdministrativo = custoAdministrativo;
	}

	/**
	 * @return the custoBdiComissao
	 */
	public CustoBdiComissao getCustoBdiComissao() {
		return custoBdiComissao;
	}

	/**
	 * @param custoBdiComissao
	 *            the custoBdiComissao to set
	 */
	public void setCustoBdiComissao(CustoBdiComissao custoBdiComissao) {
		this.custoBdiComissao = custoBdiComissao;
	}

	/**
	 * @return the custoSeguranca
	 */
	public CustoSeguranca getCustoSeguranca() {
		return custoSeguranca;
	}

	/**
	 * @param custoSeguranca
	 *            the custoSeguranca to set
	 */
	public void setCustoSeguranca(CustoSeguranca custoSeguranca) {
		this.custoSeguranca = custoSeguranca;
	}

	/**
	 * @return the custoDeslocamento
	 */
	public CustoDeslocamento getCustoDeslocamento() {
		return custoDeslocamento;
	}

	/**
	 * @param custoDeslocamento
	 *            the custoDeslocamento to set
	 */
	public void setCustoDeslocamento(CustoDeslocamento custoDeslocamento) {
		this.custoDeslocamento = custoDeslocamento;
	}

	/**
	 * @return the custoOperacional
	 */
	public CustoOperacional getCustoOperacional() {
		return custoOperacional;
	}

	/**
	 * @param custoOperacional
	 *            the custoOperacional to set
	 */
	public void setCustoOperacional(CustoOperacional custoOperacional) {
		this.custoOperacional = custoOperacional;
	}

	/**
	 * @return the listaCustosDeslocamentos
	 */
	public List<CustoDeslocamento> getListaCustosDeslocamentos() {
		return listaCustosDeslocamentos;
	}

	/**
	 * @param listaCustosDeslocamentos the listaCustosDeslocamentos to set
	 */
	public void setListaCustosDeslocamentos(List<CustoDeslocamento> listaCustosDeslocamentos) {
		this.listaCustosDeslocamentos = listaCustosDeslocamentos;
	}

	/**
	 * @return the listaCustosOperacionais
	 */
	public List<CustoOperacional> getListaCustosOperacionais() {
		return listaCustosOperacionais;
	}

	/**
	 * @param listaCustosOperacionais the listaCustosOperacionais to set
	 */
	public void setListaCustosOperacionais(List<CustoOperacional> listaCustosOperacionais) {
		this.listaCustosOperacionais = listaCustosOperacionais;
	}

	/**
	 * @return the listaCustosSeguranca
	 */
	public List<CustoSeguranca> getListaCustosSeguranca() {
		return listaCustosSeguranca;
	}

	/**
	 * @param listaCustosSeguranca the listaCustosSeguranca to set
	 */
	public void setListaCustosSeguranca(List<CustoSeguranca> listaCustosSeguranca) {
		this.listaCustosSeguranca = listaCustosSeguranca;
	}

	/**
	 * @return the listaCustosAdm
	 */
	public List<CustoAdministrativo> getListaCustosAdm() {
		return listaCustosAdm;
	}

	/**
	 * @param listaCustosAdm the listaCustosAdm to set
	 */
	public void setListaCustosAdm(List<CustoAdministrativo> listaCustosAdm) {
		this.listaCustosAdm = listaCustosAdm;
	}

	/**
	 * @return the listaCustosBdiComissao
	 */
	public List<CustoBdiComissao> getListaCustosBdiComissao() {
		return listaCustosBdiComissao;
	}

	/**
	 * @param listaCustosBdiComissao the listaCustosBdiComissao to set
	 */
	public void setListaCustosBdiComissao(List<CustoBdiComissao> listaCustosBdiComissao) {
		this.listaCustosBdiComissao = listaCustosBdiComissao;
	}
	
}
