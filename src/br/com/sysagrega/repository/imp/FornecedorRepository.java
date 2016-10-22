package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.repository.IFornecedorRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class FornecedorRepository implements IFornecedorRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public FornecedorRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	@Override
	public IFornecedor getFornecedorById(Long id) {
		
		return this.manager.find(Fornecedor.class, id);
	}
	
	@Override
	public IFornecedor getFornecedorByCnpj(String cnpj) {

		try {
			
			return manager.createQuery("from Fornecedor f where f.cnpj = :cnpj", Fornecedor.class)
						  .setParameter("cnpj", cnpj)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Fornecedor> getFornecedorByFilter(String cnpj, String nomeFantasia) {

		UaiCriteria<Fornecedor> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Fornecedor.class);
		
		
		if(!cnpj.isEmpty() && cnpj != null ) {
			easyCriteria.andEquals("cnpj", cnpj);
		}
		
		if(!nomeFantasia.isEmpty() && nomeFantasia != null) {
			easyCriteria.andEquals("nomeFantasia", nomeFantasia);
		}
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public IFornecedor saveOrMerge(IFornecedor fornecedor) {

		return this.manager.merge(fornecedor);
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#remover(br.com.sysagrega.model.imp.Fornecedores)
	 */

	@Override
	public void remover(IFornecedor fornecedor) {
		
		try {
			
			IFornecedor forn = getFornecedorById(fornecedor.getId());
			this.manager.remove(forn);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Fornecedor não pode ser excluído.");
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#getAllFornecedor()
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#getAllFornecedor()
	 */

	@Override
	public List<Fornecedor> getAllFornecedor() {
		
		TypedQuery<Fornecedor> query = manager.createQuery("from Fornecedor", Fornecedor.class);
		return query.getResultList();
		
	}

	

}
