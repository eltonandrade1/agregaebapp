package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Set;

import br.com.sysagrega.model.imp.ItensControleContrato;

public interface IContrato extends Serializable {

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the nrContrato
	 */
	String getNrContrato();

	/**
	 * @param nrContrato the nrContrato to set
	 */
	void setNrContrato(String nrContrato);

	/**
	 * @return the nomeCliente
	 */
	String getNomeCliente();

	/**
	 * @param nomeCliente the nomeCliente to set
	 */
	void setNomeCliente(String nomeCliente);

	/**
	 * @return the descricaoContrato
	 */
	String getDescricaoContrato();

	/**
	 * @param descricaoContrato the descricaoContrato to set
	 */
	void setDescricaoContrato(String descricaoContrato);

	/**
	 * @return the itensControleContratos
	 */
	Set<ItensControleContrato> getItensControleContratos();

	/**
	 * @param itensControleContratos the itensControleContratos to set
	 */
	void setItensControleContratos(Set<ItensControleContrato> itensControleContratos);

	boolean isExistente();

	boolean isNovo();

}