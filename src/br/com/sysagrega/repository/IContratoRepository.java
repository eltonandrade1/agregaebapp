package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;

public interface IContratoRepository extends Serializable {

	Contrato getContratoById(Long id);

	IContrato getPropostaByNumero(String nrContrato);

	List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente);

	Contrato saveOrMerge(Contrato contrato);

	void remover(Contrato contrato);

	List<Contrato> getAllContratos();

}