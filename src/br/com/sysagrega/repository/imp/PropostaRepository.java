package br.com.sysagrega.repository.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.IPropostaRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class PropostaRepository implements IPropostaRepository  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public PropostaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#getPropostaById(java.lang.Long)
	 */
	@Override
	public Proposta getPropostaById(Long id) {
		
		return this.manager.find(Proposta.class, id);
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#getPropostaByPrecificacao(java.lang.Long)
	 */
	@Override
	public IProposta getPropostaByPrecificacao(Long idPrecificacao) {
		
		try {
			
			return manager.createQuery("from Proposta p where p.precificacao.id = :idPrecificacao", Proposta.class)
						  .setParameter("idPrecificacao", idPrecificacao)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#getPropostaByFilter(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Proposta> getPropostaByFilter(Long numeroProposta, Long numeroPrecificacao) {
		
		UaiCriteria<Proposta> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Proposta.class);
		
		
		if(numeroProposta != null ) {
			easyCriteria.andEquals("id", numeroProposta);
		}
		
		if(numeroPrecificacao != null) {
			easyCriteria.andEquals("precificacao.id", numeroPrecificacao);
		}
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#getPropostasByPeriodo(java.util.Date, java.util.Date)
	 */
	@Override
	public List<IProposta> getPropostasByPeriodo(Date dataInicial, Date dataFinal) {
		//TODO Implementar
		return null;
	}
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#saveOrMerge(br.com.sysagrega.model.IProposta)
	 */
	@Override
	public Proposta saveOrMerge(Proposta proposta) {

		return this.manager.merge(proposta);

	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#remover(br.com.sysagrega.model.IProposta)
	 */
	@Override
	public void remover(Proposta proposta) {

		try {
			
			Proposta prop = getPropostaById(proposta.getId());
			this.manager.remove(prop);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("A Proposta não pode ser excluído.");
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IPropostaRepository#getAllPropostas()
	 */
	@Override
	public List<Proposta> getAllPropostas() {
		
		TypedQuery<Proposta> query = manager.createQuery("from Proposta", Proposta.class);
		return query.getResultList();
		
	}
}
