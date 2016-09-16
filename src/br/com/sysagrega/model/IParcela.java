package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IParcela extends Serializable {

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the nfs
	 */
	String getNfs();

	/**
	 * @param nfs the nfs to set
	 */
	void setNfs(String nfs);

	/**
	 * @return the dataEmissao
	 */
	Date getDataEmissao();

	/**
	 * @param dataEmissao the dataEmissao to set
	 */
	void setDataEmissao(Date dataEmissao);

	/**
	 * @return the dataPgto
	 */
	Date getDataPgto();

	/**
	 * @param dataPgto the dataPgto to set
	 */
	void setDataPgto(Date dataPgto);

	/**
	 * @return the valor
	 */
	double getValor();

	/**
	 * @param valor the valor to set
	 */
	void setValor(double valor);

}