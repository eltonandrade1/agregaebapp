package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.repository.IClienteRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ClienteRepository implements IClienteRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ClienteRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getClienteById(java.lang.Long)
	 */
	@Override
	public ICliente getClienteById(Long id) {
		
		return this.manager.find(Cliente.class, id);
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getClienteByCnpj(java.lang.String)
	 */
	@Override
	public ICliente getClienteByCnpj(String cnpj) {
		
		try {
			
			return manager.createQuery("from Cliente c where c.cnpj = :cnpj", Cliente.class)
						  .setParameter("cnpj", cnpj)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getClienteByFilter(java.lang.String, br.com.sysagrega.model.Enums.TipoSituacaoCliente, br.com.sysagrega.model.Enums.TipoSeguimentoCliente, br.com.sysagrega.model.Enums.TipoCliente)
	 */
	@Override
	public List<Cliente> getClienteByFilter(String nome, String cnpj, String situacao, String seguimento, String tipoCliente) {
		
		EasyCriteria<Cliente> easyCriteria = EasyCriteriaFactory.createQueryCriteria(manager, Cliente.class);
		
		if(!nome.isEmpty() && nome != null) {
			easyCriteria.andEquals("nome", nome);
		}
		
		if(!cnpj.isEmpty() && cnpj != null ) {
			easyCriteria.andEquals("cnpj", cnpj);
		}
		
		if(!situacao.isEmpty() && situacao != null) {
			easyCriteria.andEquals("situacao", situacao);
		}
		
		if(!seguimento.isEmpty() && seguimento != null) {
			easyCriteria.andEquals("situacao", seguimento);
		}
		
		if(!tipoCliente.isEmpty() && tipoCliente != null) {
			easyCriteria.andEquals("situacao", tipoCliente);
		}
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#saveOrMerge(br.com.sysagrega.model.ICliente)
	 */
	@Override
	public ICliente saveOrMerge(ICliente cliente) {

		return this.manager.merge(cliente);

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#remover(br.com.sysagrega.model.ICliente)
	 */
	@Override
	public void remover(ICliente cliente) {

		try {
			
			ICliente cli = getClienteById(cliente.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Cliente não pode ser excluído.");
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getAllClientes()
	 */
	@Override
	public List<Cliente> getAllClientes() {
		
		TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
		return query.getResultList();
		
	}
}
