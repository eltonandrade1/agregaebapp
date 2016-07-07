package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;

public interface IPropostaService extends Serializable{

	void salvar(Proposta proposta);

	List<Proposta> getAllPropostas();

	IProposta atualizarProposta(Proposta proposta);

	void excluirProposta(Proposta proposta);

	List<Proposta> getPropostaByFilter(String filtroNumeroProposta, String filtroCliente,
			Character filtroStatus, Date filtroDataInicial, Date filtroDataFinal);

	void salvarHistorico(IPropostaHistorico propostaHistorico);

	List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId);

}