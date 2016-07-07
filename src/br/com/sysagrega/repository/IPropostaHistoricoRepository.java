package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;
import br.com.sysagrega.model.imp.PropostaHistorico;

public interface IPropostaHistoricoRepository extends Serializable{

	IPropostaHistorico getPropostaHistoricoById(Long id);

	List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId);

	List<IPropostaHistorico> getHistoricosByPeriodo(Date dataInicial, Date dataFinal);

	PropostaHistorico saveHistorico(PropostaHistorico propostaHistorico);

	void removerHistorico(IProposta proposta);

}