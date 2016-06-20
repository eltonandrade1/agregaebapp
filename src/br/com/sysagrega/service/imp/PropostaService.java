package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaBase;
import br.com.sysagrega.model.IPropostaHistorico;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.repository.IPropostaHistoricoRepository;
import br.com.sysagrega.repository.IPropostaRepository;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.cdi.Transactional;
import br.com.sysagrega.util.jsf.FacesUtil;

public class PropostaService implements IPropostaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IPropostaRepository propostaRepository;
	@Inject
	private IPropostaHistoricoRepository propostaRepositoryHistorico;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#salvar(br.com.sysagrega.
	 * model.imp.Proposta)
	 */
	@Override
	@Transactional
	public void salvar(Proposta proposta) {

		// Validação para campos obrigatórios
		validaCamposObrigatorios(proposta);

		// Varifica se a proposta já está cadastrada no sistema
		IProposta propostaExistente = this.propostaRepository.getPropostaById(proposta.getId());

		if (propostaExistente != null) {
			throw new NegocioException("Proposta já cadastrada no sistema!");
		}

		// Define a data de inclusão da proposta
		// TODO Exibir essa informação ao editar ou visualizar
		proposta.setDataInclusao(DateUtil.getCurrentDateTime());

		proposta = this.propostaRepository.saveOrMerge(proposta);

		// Gera numero da proposta padrão agrega após gerar id no banco e faz o
		// merge pelo estado do objeto
		proposta.setNumeroProposta("AC." + proposta.getId() + "." + DateUtil.getCurrentMonthAndYear());

		// Salvando histórico
		salvarHistorico(configuraObjetoHistorico(proposta));

	}

	private void validaCamposObrigatorios(IPropostaBase proposta) {

		if (proposta == null) {

			throw new NegocioException("Favor informar uma proposta!");

		} else if (proposta.getTipoProposta().isEmpty() || proposta.getTipoProposta() == null) {

			throw new NegocioException("Os Campos obrigatórios não foram informados!");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.service.imp.IPropostaService#getAllPropostas()
	 */
	@Override
	public List<Proposta> getAllPropostas() {

		return propostaRepository.getAllPropostas();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#atualizarProposta(br.com.
	 * sysagrega.model.imp.Proposta)
	 */
	@Override
	@Transactional
	public IProposta atualizarProposta(Proposta proposta) {

		// Validação para campos obrigatórios
		validaCamposObrigatorios(proposta);

		proposta = this.propostaRepository.saveOrMerge(proposta);
		// Salvando histórico
		salvarHistorico(configuraObjetoHistorico(proposta));

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#excluirProposta(br.com.
	 * sysagrega.model.IProposta)
	 */
	@Override
	@Transactional
	public void excluirProposta(Proposta proposta) {

		if (proposta != null) {

			// Removendo todo histórico da proposta
			this.propostaRepositoryHistorico.removerHistorico(proposta);
			// Removendo a proposta
			this.propostaRepository.remover(proposta);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar uma proposta!");

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#getPropostaByFilter(java.
	 * lang.Long, java.lang.Long)
	 */
	@Override
	public List<Proposta> getPropostaByFilter(Long numeroProposta, Long numeroPrecificacao) {

		return this.propostaRepository.getPropostaByFilter(numeroProposta, numeroPrecificacao);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#salvarHistorico(br.com.
	 * sysagrega.model.IPropostaHistorico)
	 */
	@Override
	public void salvarHistorico(IPropostaHistorico propostaHistorico) {
		// TODO testar o cast
		this.propostaRepositoryHistorico.saveHistorico(propostaHistorico);
	}

	public PropostaHistorico configuraObjetoHistorico(Proposta proposta) {

		PropostaHistorico historico = new PropostaHistorico();

		// Ordem
		historico.setPropostaId(proposta);
		historico.setTipoProposta(proposta.getTipoProposta());
		historico.setDataInclusao(proposta.getDataInclusao());
		historico.setDataContratacao(proposta.getDataContratacao());
		historico.setCliente(proposta.getCliente());
		historico.setObjeto(proposta.getObjeto());
		historico.setValor(proposta.getPrecificacao().getValorTotalPrecificacao());
		historico.setCidade(proposta.getCidade());
		historico.setEstado(proposta.getEstado());
		historico.setContato(proposta.getContato());
		historico.setStatusContratada(proposta.getStatusContratada());
		historico.setNumeroProposta(proposta.getNumeroProposta());
		historico.setPrecificacao(proposta.getPrecificacao());
		historico.setDataRevisao(DateUtil.getCurrentDateTime());

		return historico;

	}
}
