package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;
import br.com.sysagrega.model.imp.Proposta;

public interface IPropostaService extends Serializable{

	void salvar(Proposta proposta);

	List<Proposta> getAllPropostas();

	IProposta atualizarProposta(Proposta proposta);

	void excluirProposta(Proposta proposta);

	List<Proposta> getPropostaByFilter(Long numeroProposta, Long numeroPrecificacao);

	void salvarHistorico(IPropostaHistorico propostaHistorico);

}