package br.com.sysagrega.service.imp;

import javax.inject.Inject;

import br.com.sysagrega.model.IProfissional;
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
	public void salvar(IProfissional profissional) {
		
		//Validação para campos obrigatórios
		if (profissional != null) {
			
			validaCamposObrigatorios(profissional);
			
		} else {
			
			throw new NegocioException("Favor informar um profissional!");
			
		}
		
		//Varifica se o profissional já é cadastrado no sistema
		Profissional profissionalExistente = this.profissionalRepository.getProfissionalByCpf(profissional.getCpf());
		
		if(profissionalExistente != null) {
			throw new NegocioException("Profissional já cadastrado no banco de dados!");
		}
		
		this.profissionalRepository.salvar(profissional);
		
	}
	
	/**
	 * Realiza a validação se os campos obrigatórios foram preenchidos
	 * Nome, CPF, RG, Data Nascimento
	 * 
	 * @param profissional
	 * @return true (Se os campos obrigatórios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(IProfissional profissional) {
		
		if(profissional.getNome().isEmpty()  || 
			profissional.getNome() == null   ||
			profissional.getCpf().isEmpty()  ||
			profissional.getCpf() == null    ||
			profissional.getRg().isEmpty()   ||
			profissional.getRg() == null     ||
			profissional.getDataNascimento() == null) {
			
			throw new NegocioException("Campos obrigatórios não informados!");
			
		}
	}

}
