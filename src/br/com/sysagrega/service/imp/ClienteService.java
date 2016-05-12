package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.repository.IClienteRepository;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.util.cdi.Transactional;
import br.com.sysagrega.util.jsf.FacesUtil;

public class ClienteService implements IClienteService  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IClienteRepository clienteRepository;


	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#salvar(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	public void salvar(ICliente cliente) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(cliente);

		// Varifica se o profissional j� est� cadastrado no sistema
		ICliente clienteExistente = this.clienteRepository.getClienteByCnpj(cliente.getCnpj());

		if (clienteExistente != null) {
			throw new NegocioException("Cliente j� cadastrado no sistema!");
		}

		this.clienteRepository.saveOrMerge(cliente);

	}

	/**
	 * Realiza a valida��o se os campos obrigat�rios foram preenchidos Nome,
	 * Seguimento, TipoCliente, Situacao
	 * 
	 * @param cliente
	 * @return true (Se os campos obrigat�rios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(ICliente cliente) {

		if (cliente == null) {

			throw new NegocioException("Favor informar um cliente!");

		} else if (cliente.getNome().isEmpty()      || 
					cliente.getNome() == null       || 
					cliente.getSeguimento() == null ||
					cliente.getTipoCliente() == null ||
					cliente.getSituacao() == null) {

			throw new NegocioException("Os Campos obrigat�rios n�o foram informados!");

		}
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#getAllClientes()
	 */
	@Override
	public List<Cliente> getAllClientes() {

		return clienteRepository.getAllClientes();

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#atualizarProfissional(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	public ICliente atualizarCliente(ICliente cliente) {

		// Valida��o para campos obrigat�rios
		validaCamposObrigatorios(cliente);

		ICliente clienteAtual = this.clienteRepository.saveOrMerge(cliente);

		return clienteAtual;

	}

	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#excluirProfissional(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	public void excluirCliente(ICliente cliente) {

		if (cliente != null) {

			this.clienteRepository.remover(cliente);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um cliente!");

		}

	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#getProfissionalByFilter(java.lang.String, br.com.sysagrega.model.Enums.TipoSituacaoCliente, br.com.sysagrega.model.Enums.TipoSeguimentoCliente, br.com.sysagrega.model.Enums.TipoCliente)
	 */
	@Override
	public List<Cliente> getClienteByFilter(String nome, String cnpj, String situacao, String seguimento,
			String tipoCliente) {
		
		return this.clienteRepository.getClienteByFilter(nome, cnpj, situacao, seguimento, tipoCliente);
		
	}

}
