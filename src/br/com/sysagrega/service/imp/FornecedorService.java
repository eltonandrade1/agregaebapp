package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.repository.IFornecedorRepository;
import br.com.sysagrega.service.IFornecedorService;
import br.com.sysagrega.util.cdi.Transactional;
import br.com.sysagrega.util.jsf.FacesUtil;

public class FornecedorService implements IFornecedorService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IFornecedorRepository fornecedorRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IFornecedorService#salvar(br.com.sysagrega
	 * .model.imp.Fornecedor)
	 */
	
	@Override
	@Transactional
	public void salvar(IFornecedor fornecedor) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(fornecedor);

		// Varifica se o Fornecedor j� est� cadastrado no sistema
		IFornecedor fornecedorExistente = this.fornecedorRepository.getFornecedorByCnpj(fornecedor.getCnpj());

		if (fornecedorExistente != null) {
			throw new NegocioException("Fornecedor j� cadastrado no sistema!");
		}

		this.fornecedorRepository.saveOrMerge(fornecedor);

	}
	
	/**
	 * Realiza a valida��o se os campos obrigat�rios foram preenchidos Nome,
	 * CPF, RG, Data Nascimento
	 * 
	 * @param Fornecedor
	 * @return true (Se os campos obrigatórios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(IFornecedor fornecedor) {

		if (fornecedor == null) {

			throw new NegocioException("Favor informar um Fornecedor!");

		} else if (fornecedor.getNomeFantasia().isEmpty() || fornecedor.getNomeFantasia() == null || fornecedor.getCnpj().isEmpty()
				|| fornecedor.getCnpj() == null || fornecedor.getRazaoSocial().isEmpty() || fornecedor.getRazaoSocial() == null)
			
				{

			throw new NegocioException("Os Campos obrigat�rios n�o foram informados!");

		}
	}

	/**
	 * Retorna a lista de todos os Fornecedores do sistema para a tela de
	 * consulta
	 * 
	 * @return List<Fornecedor>
	 * @author Paulo
	 */
	@Override
	public List<Fornecedor> getAllFornecedor() {

		return fornecedorRepository.getAllFornecedor();

	}
	
	/**
	 * M�todo atualizar o objeto Fornecedor e retorna o mesmo atualizado
	 * 
	 * @param fornecedor
	 * @return fornecedor
	 * @author Paulo
	 */
	@Override
	@Transactional
	public IFornecedor atualizarFornecedor(IFornecedor fornecedor) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(fornecedor);

		IFornecedor fornecedorAtual = this.fornecedorRepository.saveOrMerge(fornecedor);

		return fornecedorAtual;

	}
	
	/**
	 * M�todo exclui um objeto fornecedor
	 * 
	 * @param fornecedor
	 * @author Paulo
	 */
	@Override
	@Transactional
	public void excluirFornecedor(IFornecedor fornecedor) {

		if (fornecedor != null) {

			this.fornecedorRepository.remover(fornecedor);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um fornecedor!");

		}

	}
	
	/**
	 * M�todo realizar a busca de um objeto fornecedor, 
	 * conforme o filtro informado na tela de consulta.
	 * Se os filtros n�o forem informados o m�todo retorna todos os fornecedor
	 * 
	 * @param cnpj
	 * @param nomeFantasia
	 * @author Paulo
	 * 
	 */
	
	@Override
	public List<Fornecedor> getFornecedorByFilter(String cnpj, String nomeFantasia) {
		
		return this.fornecedorRepository.getFornecedorByFilter(cnpj, nomeFantasia);
		
	}



	

}
