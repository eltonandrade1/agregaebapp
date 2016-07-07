
package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IPropostaBase extends Serializable {

	/**
	 * @return the tipoProposta
	 */
	String getTipoProposta();

	/**
	 * @param tipoProposta the tipoProposta to set
	 */
	void setTipoProposta(String tipoProposta);

	/**
	 * @return the dataInclusao
	 */
	Date getDataInclusao();

	/**
	 * @param dataInclusao the dataInclusao to set
	 */
	void setDataInclusao(Date dataInclusao);

	/**
	 * @return the dataContratacao
	 */
	Date getDataContratacao();

	/**
	 * @param dataContratacao the dataContratacao to set
	 */
	void setDataContratacao(Date dataContratacao);

	/**
	 * @return the cliente
	 */
	String getCliente();

	/**
	 * @param cliente the cliente to set
	 */
	void setCliente(String cliente);

	/**
	 * @return the objeto
	 */
	String getObjeto();

	/**
	 * @param objeto the objeto to set
	 */
	void setObjeto(String objeto);

	/**
	 * @return the contato
	 */
	String getContato();

	/**
	 * @param contato the contato to set
	 */
	void setContato(String contato);

	/**
	 * @return the statusContratada
	 */
	Character getStatusContratada();

	/**
	 * @param statusContratada the statusContratada to set
	 */
	void setStatusContratada(Character statusContratada);

	/**
	 * @return the numeroProposta
	 */
	String getNumeroProposta();

	/**
	 * @param numeroProposta the numeroProposta to set
	 */
	void setNumeroProposta(String numeroProposta);

	String getNomeProjeto();

	void setNomeProjeto(String nomeProjeto);

	Long getId();

	void setId(Long id);

	void setStatus(Character status);

	Character getStatus();

	ICidade getCidade();

	void setCidade(ICidade cidade);

	IEstado getEstado();

	void setEstado(IEstado estado);

	double getValorTotalImpostos();

	void setValorTotalImpostos(double valorTotalImpostos);

	double getValorTotalPrecificacao();

	void setValorTotalPrecificacao(double valorTotalPrecificacao);

	double getValorTotalCustosExecucao();

	void setValorTotalCustosExecucao(double valorTotalCustosExecucao);

	double getValorTotalCustosDesclocamento();

	void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento);

	double getValorTotalCustosOperacionais();

	double getImpostos();

	double getIr();

	double getCsll();

	double getPis();

	double getCofins();

	double getIss();

	void setValorTotalSemBdiComissao(double valorTotalSemBdiComissao);

	double getValorTotalSemBdiComissao();

	void setValorTotalComBdiComissao(double valorTotalComBdiComissao);

	double getValorTotalComBdiComissao();

	void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca);

	double getValorTotalCustosSeguranca();

	void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes);

	double getValorTotalCustosBdiComissoes();

	void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos);

	double getValorTotalCustosAdministrativos();

	void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais);

	void setValorTotalDaProposta(Double valorTotalDaProposta);

	Double getValorTotalDaProposta();

	Date getDataEnvioAoCliente();

	void setDataEnvioAoCliente(Date dataEnvioAoCliente);

}