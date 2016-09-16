package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.repository.IContratoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ContratoRepository implements IContratoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ContratoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#getContratoById(java.lang.Long)
	 */
	@Override
	public Contrato getContratoById(Long id) {
		
		return this.manager.find(Contrato.class, id);
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#getPropostaByNumero(java.lang.String)
	 */
	@Override
	public IContrato getPropostaByNumero(String nrContrato) {
		
		try {
			
			return manager.createQuery("from Contrato c where c.nrContrato = :nrContrato", Contrato.class)
						  .setParameter("nrContrato", nrContrato)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#getContratoByFilter(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente) {
		
		UaiCriteria<Contrato> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Contrato.class);
		
		
		if(!filtroCliente.isEmpty() && filtroCliente != null) {
			easyCriteria.andStringLike("nomeCliente", "%" + filtroCliente + "%");
		}
		
		if(!nrContrato.isEmpty() && nrContrato != null) {
			easyCriteria.andEquals("nrContrato", nrContrato);
		}
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#saveOrMerge(br.com.sysagrega.model.imp.Contrato)
	 */
	@Override
	public Contrato saveOrMerge(Contrato contrato) {

		return this.manager.merge(contrato);

	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#remover(br.com.sysagrega.model.imp.Contrato)
	 */
	@Override
	public void remover(Contrato contrato) {

		try {
			
			Contrato ctr = getContratoById(contrato.getId());
			this.manager.remove(ctr);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("O contrato não pode ser excluído.");
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IContratoRepository#getAllContratos()
	 */
	@Override
	public List<Contrato> getAllContratos() {
		
		TypedQuery<Contrato> query = manager.createQuery("from Contrato", Contrato.class);
		return query.getResultList();
		
	}
}
