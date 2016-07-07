package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaBase;
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

			// Validação para campos obrigatórios e datas
			validaCamposObrigatorios(proposta);
			
			//Set data de inclusão no sistema
			proposta.setDataInclusao(new Date());

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

		} else if (proposta.getDataEnvioAoCliente() != null && proposta.getDataContratacao() != null) {

			if (DateUtil.compareDates(proposta.getDataEnvioAoCliente(), proposta.getDataContratacao()) > 0) {

				throw new NegocioException(
						"A data de contratação deve ser igual ou superior a data de envio ao cliente");

			}
		} else if (proposta.getTipoProposta().isEmpty() || proposta.getTipoProposta() == null
				|| proposta.getCliente().isEmpty() || proposta.getCliente() == null
				|| proposta.getStatusContratada() == null || proposta.getEstado() == null
				|| proposta.getCidade() == null
				|| proposta.getNomeProjeto().isEmpty() || proposta.getNomeProjeto() == null) {

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
	public List<Proposta> getPropostaByFilter(String filtroNumeroProposta, String filtroCliente, Character filtroStatus,
			Date filtroDataInicial, Date filtroDataFinal) {

		// Validação do periodo
		if (filtroDataInicial != null && filtroDataFinal != null) {

			if (DateUtil.compareDates(filtroDataInicial, filtroDataFinal) > 0) {

				throw new NegocioException("A data final deve ser igual ou superior a data inicial");

			}
		}

		return this.propostaRepository.getPropostaByFilter(filtroNumeroProposta, filtroCliente, filtroStatus,
				filtroDataInicial, filtroDataFinal);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#salvarHistorico(br.com.
	 * sysagrega.model.IPropostaHistorico)
	 */
	@Override
	public void salvarHistorico(PropostaHistorico propostaHistorico) {

		propostaHistorico = this.propostaRepositoryHistorico.saveHistorico(propostaHistorico);
		//Gerando numero da revisão
		propostaHistorico.setNumeroRevisao("REV-" + propostaHistorico.getId() + "." + DateUtil.getCurrentMonthAndYear());

	}

	@Override
	public List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId) {

		return this.propostaRepositoryHistorico.getPropostaHistoricoByFilter(propostaId);

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
		historico.setDataEnvioAoCliente(proposta.getDataEnvioAoCliente());
		// valores totais
		historico.setValorTotalDaProposta(proposta.getValorTotalDaProposta());
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

		historico.setDataRevisao(new Date());
		

		return historico;

	}
}
