package br.com.sysagrega.service.imp;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.repository.IProfissionalRepository;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.util.cdi.Transactional;

public class ProfissionalService implements IProfissionalService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IProfissionalRepository profissionalRepository;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IProfissionalService#salvar(br.com.sysagrega.model.imp.Profissional)
	 */
	@Override
	@Transactional
	public void salvar(Profissional profissional) {
		
		Profissional profissionalExistente = this.profissionalRepository.getProfissionalByCpf(profissional.getCpf());
		
		if(profissionalExistente != null) {
			throw new NegocioException("Profissional ja cadastrado no banco de dados!");
		}
		
		this.profissionalRepository.salvar(profissional);
		
	}

}
