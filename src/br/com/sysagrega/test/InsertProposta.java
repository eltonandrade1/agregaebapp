package br.com.sysagrega.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sysagrega.model.ICustoExecucao;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.util.DateUtil;

public class InsertProposta {
	
	public static void main(String[] args) {
		long totalPrecificacao = 0;
		
		//Conexão
		EntityManager manager = getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		//Custos da Precificação
		List<CustoExecucao> lista = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			
			CustoExecucao custoPrecificacao = new CustoExecucao();
			custoPrecificacao.setDescricao("Primeiro Teste de lista"+i);
			custoPrecificacao.setQuantidade(10);
			custoPrecificacao.setValorUnitario(300);
			custoPrecificacao.setValorTotal(custoPrecificacao.getValorUnitario() * custoPrecificacao.getQuantidade());
			
			lista.add(custoPrecificacao);
			
		}
		
		//Precificação
//		Precificacao precificacao = new Precificacao();
//		precificacao.setCustos(lista);
		
		for (ICustoExecucao totalCustos : lista) {
			
			//totalPrecificacao += totalCustos.getValorTotal();
		}
		
//		precificacao.setValorTotalPrecificacao(totalPrecificacao);
		
		
		
		//Proposta
		Proposta proposta = new Proposta();
		//proposta.setId(1L);
		//proposta.setCidade("Salvador");
		proposta.setCliente("Elton Andrade");
		proposta.setContato("Andrade");
		proposta.setDataInclusao(DateUtil.getCurrentDateTime());
		proposta.setObjeto("Proposta e precificacao");
		proposta.setDataInclusao(new Date());
//		proposta.setValor(precificacao.getValorTotalPrecificacao());
//		proposta.setPrecificacao(precificacao);
		
		try {
			
			
			proposta = manager.merge(proposta);
			//Defini o numero da proposta
			proposta.setNumeroProposta("AC." + proposta.getId() + "." + DateUtil.getCurrentMonthAndYear());
			
			
			//Gravar Historico
			manager.persist(configuraHistorico(proposta));
			
			trx.commit();
			manager.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			manager.close();
			
		}

	}
	
	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sysagregaPU");
		return factory.createEntityManager();
		
	}
	
	public static PropostaHistorico configuraHistorico(Proposta proposta) {
		
		PropostaHistorico historico = new PropostaHistorico();
		//Ordem
		historico.setPropostaId(proposta);
		historico.setNumeroRevisao("REV." + proposta.getId() + "." + DateUtil.getCurrentDateTime());
		historico.setTipoProposta(proposta.getTipoProposta());
		historico.setDataInclusao(proposta.getDataInclusao());
		historico.setDataContratacao(proposta.getDataContratacao());
		historico.setCliente(proposta.getCliente());
		historico.setObjeto(proposta.getObjeto());
//		historico.setValor(proposta.getPrecificacao().getValorTotalPrecificacao());
		historico.setCidade(proposta.getCidade());
		//historico.setUf(proposta.getUf());
		historico.setContato(proposta.getContato());
		historico.setStatusContratada(proposta.getStatusContratada());
		historico.setNumeroProposta(proposta.getNumeroProposta());
//		historico.setPrecificacao(proposta.getPrecificacao());
		historico.setDataRevisao(DateUtil.getCurrentDateTime());
		
		return historico;
		
	}
}
