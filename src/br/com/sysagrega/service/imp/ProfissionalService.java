package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.repository.IProfissionalRepository;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.util.cdi.Transactional;
import br.com.sysagrega.util.jsf.FacesUtil;

public class ProfissionalService implements IProfissionalService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IProfissionalRepository profissionalRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IProfissionalService#salvar(br.com.sysagrega
	 * .model.imp.Profissional)
	 */
	@Override
	@Transactional
	public void salvar(IProfissional profissional) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(profissional);

		// Varifica se o profissional j� est� cadastrado no sistema
		IProfissional profissionalExistente = this.profissionalRepository.getProfissionalByCpf(profissional.getCpf());

		if (profissionalExistente != null) {
			throw new NegocioException("Profissional j� cadastrado no sistema!");
		}

		this.profissionalRepository.saveOrMerge(profissional);

	}

	/**
	 * Realiza a valida��o se os campos obrigat�rios foram preenchidos Nome,
	 * CPF, RG, Data Nascimento
	 * 
	 * @param profissional
	 * @return true (Se os campos obrigatórios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(IProfissional profissional) {

		if (profissional == null) {

			throw new NegocioException("Favor informar um profissional!");

		} else if (profissional.getNome().isEmpty() || profissional.getNome() == null || profissional.getCpf().isEmpty()
				|| profissional.getCpf() == null || profissional.getRg().isEmpty() || profissional.getRg() == null
				|| profissional.getDataNascimento() == null) {

			throw new NegocioException("Campos obrigat�rios n�o informados!");

		}
	}

	/**
	 * Retorna a lista de todos os profissionais do sistema para a tela de
	 * consulta
	 * 
	 * @return List<Profissional>
	 * @author Elton
	 */
	@Override
	public List<IProfissional> getAllProfissionals() {

		return profissionalRepository.getAllProfissionals();

	}

	/**
	 * M�todo atualizar o objeto profissional e retorna o mesmo atualizado
	 * 
	 * @param profissional
	 * @return profissional
	 * @author Elton
	 */
	@Override
	@Transactional
	public IProfissional atualizarProfissional(IProfissional profissional) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(profissional);

		IProfissional profissionalAtual = this.profissionalRepository.saveOrMerge(profissional);

		return profissionalAtual;

	}

	/**
	 * M�todo exclui um objeto profissional
	 * 
	 * @param profissional
	 * @author Elton
	 */
	@Override
	@Transactional
	public void excluirProfissional(IProfissional profissional) {

		if (profissional != null) {

			this.profissionalRepository.remover(profissional);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um profissional!");

		}

	}

}
