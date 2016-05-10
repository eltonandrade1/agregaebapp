package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProfissional;

public interface IProfissionalRepository extends Serializable {

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getProfissionalById(java.lang.Long)
	 */
	IProfissional getProfissionalById(Long id);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#atualizar(br.com.sysagrega.model.imp.Profissional)
	 */
	IProfissional saveOrMerge(IProfissional profissional);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#remover(br.com.sysagrega.model.imp.Profissional)
	 */
	void remover(IProfissional profissional);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IProfissionalRepository#getAllProfissionals()
	 */
	List<IProfissional> getAllProfissionals();

	IProfissional getProfissionalByCpf(String cpf);

}