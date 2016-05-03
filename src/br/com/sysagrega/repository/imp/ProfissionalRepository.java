package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.repository.IProfissionalRepository;

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
	public Profissional getProfissionalById(Long id) {
		
		return this.manager.find(Profissional.class, id);
		
	}
	
	@Override
	public Profissional getProfissionalByCpf(String cpf) {
		
		try {
			
			return manager.createQuery("from Profissional p where p.cpf = :cpf", Profissional.class)
						  .setParameter("cpf", cpf)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#salvar(br.com.sysagrega.model.imp.Profissional)
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#salvar(br.com.sysagrega.model.imp.Profissional)
	 */
	@Override
	public void salvar(Profissional profissional) {

		this.manager.persist(profissional);

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#atualizar(br.com.sysagrega.model.imp.Profissional)
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#atualizar(br.com.sysagrega.model.imp.Profissional)
	 */
	@Override
	public Profissional atualizar(Profissional profissional) {

		return this.manager.merge(profissional);

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#remover(br.com.sysagrega.model.imp.Profissional)
	 */
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#remover(br.com.sysagrega.model.imp.Profissional)
	 */
	@Override
	public void remover(Profissional profissional) {

		this.manager.remove(profissional);
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
