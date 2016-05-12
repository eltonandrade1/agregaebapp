package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.repository.IProfissionalRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ProfissionalRepository implements IProfissionalRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ProfissionalRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getProfissionalById(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getProfissionalById(java.lang.Long)
	 */
	@Override
	public IProfissional getProfissionalById(Long id) {
		
		return this.manager.find(Profissional.class, id);
		
	}
	
	@Override
	public IProfissional getProfissionalByCpf(String cpf) {
		
		try {
			
			return manager.createQuery("from Profissional p where p.cpf = :cpf", Profissional.class)
						  .setParameter("cpf", cpf)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Profissional> getProfissionalByFilter(String cpf, String rg) {
		
		EasyCriteria<Profissional> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Profissional.class);
		
		
		if(!cpf.isEmpty() && cpf != null ) {
			easyCriteria.andEquals("cpf", cpf);
		}
		
		if(!rg.isEmpty() && rg != null) {
			easyCriteria.andEquals("rg", rg);
		}
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#atualizar(br.com.sysagrega.model.imp.Profissional)
	 */
	@Override
	public IProfissional saveOrMerge(IProfissional profissional) {

		return this.manager.merge(profissional);

	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#remover(br.com.sysagrega.model.imp.Profissional)
	 */
	
	@Override
	public void remover(IProfissional profissional) {

		try {
			
			IProfissional prof = getProfissionalById(profissional.getId());
			this.manager.remove(prof);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Profissional não pode ser excluído.");
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getAllProfissionals()
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getAllProfissionals()
	 */
	@Override
	public List<Profissional> getAllProfissionals() {
		
		TypedQuery<Profissional> query = manager.createQuery("from Profissional", Profissional.class);
		return query.getResultList();
		
	}
}
