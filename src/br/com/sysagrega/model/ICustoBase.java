package br.com.sysagrega.model;

import java.io.Serializable;

public interface ICustoBase extends Serializable{

	/**
	 * @return the descricao
	 */
	String getDescricao();

	/**
	 * @param descricao the descricao to set
	 */
	void setDescricao(String descricao);

	/**
	 * @return the quantidade
	 */
	long getQuantidade();

	/**
	 * @param quantidade the quantidade to set
	 */
	void setQuantidade(long quantidade);

	/**
	 * @return the valorUnitario
	 */
	double getValorUnitario();

	/**
	 * @param valorUnitario the valorUnitario to set
	 */
	void setValorUnitario(double valorUnitario);

	/**
	 * @return the valorTotal
	 */
	double getValorTotal();

	/**
	 * @param valorTotal the valorTotal to set
	 */
	void setValorTotal(double valorTotal);

	/**
	 * @return the precificacao
	 */
	IPrecificacao getPrecificacao();

	/**
	 * @param precificacao the precificacao to set
	 */
	void setPrecificacao(IPrecificacao precificacao);

}