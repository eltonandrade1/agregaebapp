package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;

public interface IProfissionalRepository extends Serializable {

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getProfissionalById(java.lang.Long)
	 */
	Profissional getProfissionalById(Long id);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#salvar(br.com.sysagrega.model.imp.Profissional)
	 */
	void salvar(IProfissional profissional);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#atualizar(br.com.sysagrega.model.imp.Profissional)
	 */
	Profissional atualizar(Profissional profissional);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#remover(br.com.sysagrega.model.imp.Profissional)
	 */
	void remover(Profissional profissional);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getAllProfissionals()
	 */
	List<Profissional> getAllProfissionals();

	Profissional getProfissionalByCpf(String cpf);

}