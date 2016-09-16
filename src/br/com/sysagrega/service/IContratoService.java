package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;

public interface IContratoService extends Serializable {

	void salvar(Contrato contrato);

	List<Contrato> getAllContratos();

	IContrato atualizarContrato(Contrato contrato);

	void excluirContrato(Contrato contrato);

	List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente);

}