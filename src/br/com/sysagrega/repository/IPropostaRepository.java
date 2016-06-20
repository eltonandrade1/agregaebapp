package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;

public interface IPropostaRepository extends Serializable{

	Proposta getPropostaById(Long id);

	IProposta getPropostaByPrecificacao(Long idPrecificacao);

	List<Proposta> getPropostaByFilter(Long numeroProposta, Long numeroPrecificacao);

	List<IProposta> getPropostasByPeriodo(Date dataInicial, Date dataFinal);

	Proposta saveOrMerge(Proposta proposta);

	void remover(Proposta proposta);

	List<Proposta> getAllPropostas();

}