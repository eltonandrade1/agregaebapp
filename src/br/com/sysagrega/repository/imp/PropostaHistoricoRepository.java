package br.com.sysagrega.repository.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.repository.IPropostaHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class PropostaHistoricoRepository implements IPropostaHistoricoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public PropostaHistoricoRepository(EntityManager manager) {

		this.manager = manager;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.repository.imp.IPropostaHistoricoRepository#
	 * getPropostaHistoricoById(java.lang.Long)
	 */
	@Override
	public IPropostaHistorico getPropostaHistoricoById(Long id) {

		return this.manager.find(PropostaHistorico.class, id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.repository.imp.IPropostaHistoricoRepository#
	 * getPropostaHistoricoByFilter(br.com.sysagrega.model.IProposta)
	 */
	@Override
	public List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId) {

		UaiCriteria<PropostaHistorico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager,
				PropostaHistorico.class);

		if (propostaId != null) {

			easyCriteria.andEquals("propostaId", propostaId);
			easyCriteria.orderByDesc("id");

		}

		try {

			return easyCriteria.getResultList();

		} catch (NoResultException e) {

			return null;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.repository.imp.IPropostaHistoricoRepository#
	 * getHistoricosByPeriodo(java.util.Date, java.util.Date)
	 */
	@Override
	public List<IPropostaHistorico> getHistoricosByPeriodo(Date dataInicial, Date dataFinal) {
		// TODO Implementar
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.repository.imp.IPropostaHistoricoRepository#
	 * saveHistorico(br.com.sysagrega.model.IPropostaHistorico)
	 */
	@Override
	public PropostaHistorico saveHistorico(PropostaHistorico propostaHistorico) {

		 return this.manager.merge(propostaHistorico);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.repository.imp.IPropostaHistoricoRepository#
	 * removerHistorico(br.com.sysagrega.model.IProposta)
	 */
	@Override
	public void removerHistorico(IProposta proposta) {

		try {

			List<PropostaHistorico> list = getPropostaHistoricoByFilter(proposta);

			for (PropostaHistorico propostaHistorico : list) {

				this.manager.remove(propostaHistorico);

			}

			this.manager.flush();

		} catch (PersistenceException e) {

			throw new NegocioException("O Histórico não pode ser excluído.");

		}

	}

}
