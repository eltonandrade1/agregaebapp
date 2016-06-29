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

		try {

			// Validação para campos obrigatórios
			validaCamposObrigatorios(proposta);

			// TODO validação de datas (contratação não pode ser menor que
			// inclusão)

			proposta = this.propostaRepository.saveOrMerge(proposta);

			// Gera numero da proposta padrão agrega após gerar id no banco e
			// faz o
			// merge pelo estado do objeto proposta
			proposta.setNumeroProposta("AC." + proposta.getId() + "." + DateUtil.getCurrentMonthAndYear());

			// Salvando histórico
			salvarHistorico(configuraObjetoHistorico(proposta));

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}

	private void validaCamposObrigatorios(IPropostaBase proposta) {

		if (proposta == null) {

			throw new NegocioException("Favor informar uma proposta!");

		} else if (proposta.getTipoProposta().isEmpty() || proposta.getTipoProposta() == null
				|| proposta.getCliente().isEmpty() || proposta.getCliente() == null
				|| proposta.getStatusContratada() == null || proposta.getEstado() == null
				|| proposta.getCidade() == null) {

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
		
		Proposta propostaNew = new Proposta();

		try {

			// Validação para campos obrigatórios
			validaCamposObrigatorios(proposta);

			propostaNew = this.propostaRepository.saveOrMerge(proposta);
			// Salvando histórico
			salvarHistorico(configuraObjetoHistorico(propostaNew));

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

		return propostaNew;

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

			throw new NegocioException("Favor selecionar uma proposta!");

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

		this.propostaRepositoryHistorico.saveHistorico(propostaHistorico);

	}

	private PropostaHistorico configuraObjetoHistorico(Proposta proposta) {

		PropostaHistorico historico = new PropostaHistorico();

		// Ordem
		historico.setPropostaId(proposta);
		historico.setNumeroProposta(proposta.getNumeroProposta());
		historico.setNomeProjeto(proposta.getNomeProjeto());
		historico.setEstado(proposta.getEstado());
		historico.setStatusContratada(proposta.getStatusContratada());
		historico.setObjeto(proposta.getObjeto());
		historico.setDataInclusao(proposta.getDataInclusao());
		historico.setCliente(proposta.getCliente());
		historico.setCidade(proposta.getCidade());
		historico.setStatus(proposta.getStatus());
		historico.setDataContratacao(proposta.getDataContratacao());
		historico.setContato(proposta.getContato());
		historico.setTipoProposta(proposta.getTipoProposta());
		// valores totais
		historico.setValorTotalPrecificacao(proposta.getValorTotalPrecificacao());
		historico.setValorTotalCustosExecucao(proposta.getValorTotalCustosExecucao());
		historico.setValorTotalCustosDesclocamento(proposta.getCalculoValorTotalCustosDeslocamento());
		historico.setValorTotalCustosOperacionais(proposta.getValorTotalCustosOperacionais());
		historico.setValorTotalCustosAdministrativos(proposta.getCalculoValorTotalCustosAdministraticos());
		historico.setValorTotalCustosBdiComissoes(proposta.getCalculoValorTotalCustosBdiComissao());
		historico.setValorTotalCustosSeguranca(proposta.getCalculoValorTotalCustosSeguranca());
		// Total custos com e sem bdi
		historico.setValorTotalComBdiComissao(proposta.getValorTotalComBdiComissao());
		historico.setValorTotalSemBdiComissao(proposta.getValorTotalSemBdiComissao());
		historico.setValorTotalImpostos(proposta.getValorTotalImpostos());

		historico.setDataRevisao(DateUtil.getCurrentDateTime());
		historico.setNumeroRevisao("REV-" + proposta.getNumeroProposta());

		return historico;

	}
}
