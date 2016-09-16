package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.sysagrega.model.IPrecificacao;
import br.com.sysagrega.model.IProposta;
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
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.RelatorioUtil;
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
	@QualificadorProposta
	private Proposta proposta;

	private List<PropostaHistorico> historicos;

	private boolean viewProposta;

	private boolean disableCity;

	private boolean disableVlUnitarioAdm = true;

	// Filtros consultas
	private String filtroNumeroProposta;

	private String filtroCliente;

	private Character filtroStatus;

	// Filtros consultas Periodo
	private Date filtroDataInicial;

	private Date filtroDataFinal;

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

	// Listas dos objetos custos
	private Set<CustoExecucao> listaCustos;

	private Set<CustoDeslocamento> listaCustosDeslocamentos;

	private Set<CustoOperacional> listaCustosOperacionais;

	private Set<CustoSeguranca> listaCustosSeguranca;

	private Set<CustoAdministrativo> listaCustosAdm;

	private Set<CustoBdiComissao> listaCustosBdiComissao;

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
			limparObjetosCustos();
			carregarCidadesPorEstado();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.NOVA_PROPOSTA)) {

			limparObjeto();

		} else if (FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_PROPOSTA)) {
			this.proposta = FacesUtil.getPropostaSession();
			limparObjetosCustos();
			carregarCidadesPorEstado();
			viewProposta = true;
		} else if (FacesUtil.getParamSession().equals(TipoPagina.HISTORICO_PROPOSTA)) {

			this.proposta = FacesUtil.getPropostaSession();
			carregarHistoricoByProposta(this.proposta);

		}
	}

	private void limparObjeto() {

		this.proposta = new Proposta();

		limparObjetosCustos();

		// Listas dos objetos de custo
		this.listaCustos = new HashSet<>();
		this.listaCustosDeslocamentos = new HashSet<>();
		this.listaCustosOperacionais = new HashSet<>();
		this.listaCustosSeguranca = new HashSet<>();
		this.listaCustosAdm = new HashSet<>();
		this.listaCustosBdiComissao = new HashSet<>();

		this.proposta.setCustos(this.listaCustos);
		this.proposta.setDespesasDeslocamentos(this.listaCustosDeslocamentos);
		this.proposta.setDespesasOperacionais(this.listaCustosOperacionais);
		this.proposta.setDespesasSeguranca(this.listaCustosSeguranca);
		this.proposta.setDespesasAdministrativas(this.listaCustosAdm);
		this.proposta.setDespesasBdiComissao(this.listaCustosBdiComissao);

		this.proposta.setEstado(new Estado());
		this.proposta.setCidade(new Cidade());

		disableCity = true;

	}

	private void limparObjetosCustos() {

		this.custoExecucao = new CustoExecucao();
		this.custoAdministrativo = new CustoAdministrativo();
		this.custoBdiComissao = new CustoBdiComissao();
		this.custoSeguranca = new CustoSeguranca();
		this.custoDeslocamento = new CustoDeslocamento();
		this.custoOperacional = new CustoOperacional();

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

		if (this.custoAdministrativo.getDescricao().equals(TipoCustoAdm.ADMINISTRACAO.getDescricao())) {

			if (this.custoAdministrativo.getQuantidade() > 0) {

				totalPorItem += (this.proposta.getValorTotalCustosExecucao() * this.custoAdministrativo.getQuantidade())
						/ 100;

			}

		} else if (this.custoAdministrativo.getQuantidade() > 0) {

			totalPorItem += this.custoAdministrativo.getValorUnitario() * this.custoAdministrativo.getQuantidade();

		}

		this.custoAdministrativo.setValorTotal(totalPorItem);

		calcularValorTotalCustosAdministrativos();

		custoAdministrativo = new CustoAdministrativo();

	}

	public void somarValorCustosBdiComissao() {

		double totalPorItem = 0;

		if (this.custoBdiComissao.getQuantidade() > 0) {

			totalPorItem += (this.proposta.getValorTotalSemBdiComissao() * this.custoBdiComissao.getQuantidade()) / 100;

		}

		this.custoBdiComissao.setValorTotal(totalPorItem);

		calcularValorTotalCustosBdiComissao();

		custoBdiComissao = new CustoBdiComissao();

	}

	public void calcularValorTotalCustosBdiComissao() {

		this.proposta.setValorTotalCustosBdiComissoes(this.proposta.getCalculoValorTotalCustosBdiComissao());

		calcularValorTotalAposBdiComissao();

	}

	private void calcularValorTotalAposBdiComissao() {

		double totalComBdiComissao = 0;

		if (this.proposta.getValorTotalCustosBdiComissoes() > 0) {

			totalComBdiComissao += this.proposta.getValorTotalCustosBdiComissoes()
					+ this.proposta.getValorTotalSemBdiComissao();

		}

		this.proposta.setValorTotalComBdiComissao(totalComBdiComissao);

		calcularValorTotalComImpostos();

	}

	public void calcularValorTotalCustosAdministrativos() {

		this.proposta.setValorTotalCustosAdministrativos(this.proposta.getCalculoValorTotalCustosAdministraticos());

		calcularValorTotalCustosSemBdi();

	}

	public void calcularValorTotalCustosSeguranca() {

		this.proposta.setValorTotalCustosSeguranca(this.proposta.getCalculoValorTotalCustosSeguranca());

		calcularValorTotalCustosSemBdi();

	}

	public void calcularValorTotalCustosDeslocamento() {

		this.proposta.setValorTotalCustosDesclocamento(this.proposta.getCalculoValorTotalCustosDeslocamento());

		calcularValorTotalCustosSemBdi();

	}

	public void calcularValorTotalCustosOperacionais() {

		this.proposta.setValorTotalCustosOperacionais(this.proposta.getCalculoValorTotalCustosOperacionais());

		calcularValorTotalCustosSemBdi();

	}

	public void calcularValorTotalCustosExecucao() {

		this.proposta.setValorTotalCustosExecucao(this.proposta.getCalculoValorTotalCustosExecucao());

		calcularValorTotalCustosSemBdi();

	}

	private void calcularValorTotalCustosSemBdi() {

		double totalSemBdi = 0;

		totalSemBdi += this.proposta.getCalculoValorTotalCustosExecucao()
				+ this.proposta.getCalculoValorTotalCustosDeslocamento()
				+ this.proposta.getCalculoValorTotalCustosOperacionais()
				+ this.proposta.getCalculoValorTotalCustosSeguranca()
				+ this.proposta.getCalculoValorTotalCustosAdministraticos();

		this.proposta.setValorTotalSemBdiComissao(totalSemBdi);
		calcularValorTotalAposBdiComissao();
		calcularValorTotalComImpostos();

	}

	private void calcularValorTotalComImpostos() {

		double totalComImpostos = 0;
		double taxaImposto = 0;

		if (this.proposta.getValorTotalComBdiComissao() > 0) {

			taxaImposto += this.proposta.getValorTotalComBdiComissao() / (1 - this.proposta.getImpostos())
					- this.proposta.getValorTotalComBdiComissao();

			totalComImpostos += taxaImposto + this.proposta.getValorTotalComBdiComissao();

		} else {

			taxaImposto += this.proposta.getValorTotalSemBdiComissao() / (1 - this.proposta.getImpostos())
					- this.proposta.getValorTotalSemBdiComissao();

			totalComImpostos += taxaImposto + this.proposta.getValorTotalSemBdiComissao();

		}

		this.proposta.setValorTotalImpostos(totalComImpostos);
		this.proposta.setValorTotalDaProposta(totalComImpostos);

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

	public void habilitarValorUnitarioAdm() {

		if (custoAdministrativo != null && custoAdministrativo.getDescricao() != null) {

			if (custoAdministrativo.getDescricao().equalsIgnoreCase(TipoCustoAdm.ADMINISTRACAO.getDescricao())) {

				setDisableVlUnitarioAdm(true);

			} else {

				setDisableVlUnitarioAdm(false);

			}
		}
	}

	/**
	 * Método redireciona para tela de visualização da proposta
	 * 
	 * @return editar_proposta
	 * @author Elton
	 */
	public String visualizarProposta() {

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

	public String consultarHistoricoProposta() {

		if (this.proposta != null) {

			FacesUtil.addParamSession(TipoPagina.HISTORICO_PROPOSTA);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("proposta", this.proposta);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar uma proposta!");
			return null;

		}

		return "historico_proposta";

	}

	private void carregarHistoricoByProposta(Proposta propostaId) {

		historicos = new ArrayList<>();

		try {

			historicos = this.propostaService.getPropostaHistoricoByFilter(propostaId);

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

	}

	public Boolean isEditProposta() {

		return this.proposta.isExistente() && !viewProposta;

	}

	public Boolean isNewProposta() {

		return this.proposta.isNovo();

	}

	public void filtrarPropostas() {

		propostas = new ArrayList<>();

		try {

			propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroCliente,
					this.filtroStatus, this.filtroDataInicial, this.filtroDataFinal);

		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
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

	public void gerarPdf() {
		List<Proposta> list = new ArrayList<>();
		if (proposta != null) {
			list.add(proposta);
			RelatorioUtil.geraRelatorio("RelatorioProposta", list);

		} else {
			throw new NegocioException("Favor selecionar uma proposta!");
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
	public String getFiltroNumeroProposta() {
		return filtroNumeroProposta;
	}

	/**
	 * @param filtroNumeroProposta
	 *            the filtroNumeroProposta to set
	 */
	public void setFiltroNumeroProposta(String filtroNumeroProposta) {
		this.filtroNumeroProposta = filtroNumeroProposta;
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
	public Set<CustoExecucao> getListaCustos() {
		return listaCustos;
	}

	/**
	 * @param listaCustos
	 *            the listaCustos to set
	 */
	public void setListaCustos(Set<CustoExecucao> listaCustos) {
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
	public Set<CustoDeslocamento> getListaCustosDeslocamentos() {
		return listaCustosDeslocamentos;
	}

	/**
	 * @param listaCustosDeslocamentos
	 *            the listaCustosDeslocamentos to set
	 */
	public void setListaCustosDeslocamentos(Set<CustoDeslocamento> listaCustosDeslocamentos) {
		this.listaCustosDeslocamentos = listaCustosDeslocamentos;
	}

	/**
	 * @return the listaCustosOperacionais
	 */
	public Set<CustoOperacional> getListaCustosOperacionais() {
		return listaCustosOperacionais;
	}

	/**
	 * @param listaCustosOperacionais
	 *            the listaCustosOperacionais to set
	 */
	public void setListaCustosOperacionais(Set<CustoOperacional> listaCustosOperacionais) {
		this.listaCustosOperacionais = listaCustosOperacionais;
	}

	/**
	 * @return the listaCustosSeguranca
	 */
	public Set<CustoSeguranca> getListaCustosSeguranca() {
		return listaCustosSeguranca;
	}

	/**
	 * @param listaCustosSeguranca
	 *            the listaCustosSeguranca to set
	 */
	public void setListaCustosSeguranca(Set<CustoSeguranca> listaCustosSeguranca) {
		this.listaCustosSeguranca = listaCustosSeguranca;
	}

	/**
	 * @return the listaCustosAdm
	 */
	public Set<CustoAdministrativo> getListaCustosAdm() {
		return listaCustosAdm;
	}

	/**
	 * @param listaCustosAdm
	 *            the listaCustosAdm to set
	 */
	public void setListaCustosAdm(Set<CustoAdministrativo> listaCustosAdm) {
		this.listaCustosAdm = listaCustosAdm;
	}

	/**
	 * @return the listaCustosBdiComissao
	 */
	public Set<CustoBdiComissao> getListaCustosBdiComissao() {
		return listaCustosBdiComissao;
	}

	/**
	 * @param listaCustosBdiComissao
	 *            the listaCustosBdiComissao to set
	 */
	public void setListaCustosBdiComissao(Set<CustoBdiComissao> listaCustosBdiComissao) {
		this.listaCustosBdiComissao = listaCustosBdiComissao;
	}

	/**
	 * @return the disableVlUnitarioAdm
	 */
	public boolean isDisableVlUnitarioAdm() {
		return disableVlUnitarioAdm;
	}

	/**
	 * @param disableVlUnitarioAdm
	 *            the disableVlUnitarioAdm to set
	 */
	public void setDisableVlUnitarioAdm(boolean disableVlUnitarioAdm) {
		this.disableVlUnitarioAdm = disableVlUnitarioAdm;
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
	 * @return the filtroStatus
	 */
	public Character getFiltroStatus() {
		return filtroStatus;
	}

	/**
	 * @param filtroStatus
	 *            the filtroStatus to set
	 */
	public void setFiltroStatus(Character filtroStatus) {
		this.filtroStatus = filtroStatus;
	}

	/**
	 * @return the filtroDataInicial
	 */
	public Date getFiltroDataInicial() {
		return filtroDataInicial;
	}

	/**
	 * @param filtroDataInicial
	 *            the filtroDataInicial to set
	 */
	public void setFiltroDataInicial(Date filtroDataInicial) {
		this.filtroDataInicial = filtroDataInicial;
	}

	/**
	 * @return the filtroDataFinal
	 */
	public Date getFiltroDataFinal() {
		return filtroDataFinal;
	}

	/**
	 * @param filtroDataFinal
	 *            the filtroDataFinal to set
	 */
	public void setFiltroDataFinal(Date filtroDataFinal) {
		this.filtroDataFinal = filtroDataFinal;
	}

	/**
	 * @return the historicos
	 */
	public List<PropostaHistorico> getHistoricos() {
		return historicos;
	}

	/**
	 * @param historicos
	 *            the historicos to set
	 */
	public void setHistoricos(List<PropostaHistorico> historicos) {
		this.historicos = historicos;
	}
}
