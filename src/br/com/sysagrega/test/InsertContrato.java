package br.com.sysagrega.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.ItensControleContrato;
import br.com.sysagrega.model.imp.Parcela;

public class InsertContrato {

	public static void main(String[] args) {

		// Conexão
		EntityManager manager = getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		// Obajeto Parcela
		Parcela parcela = new Parcela();
		Set<Parcela> parcelas = new HashSet<>();

		for (int i = 0; i < 3; i++) {

			parcela.setDataEmissao(new Date());
			parcela.setDataPgto(new Date());
			parcela.setNfs("66" + i);
			parcela.setValor(300.00);
			parcelas.add(parcela);

		}

		// Objeto Iten do Contrato
		ItensControleContrato itenControleContrato = new ItensControleContrato();
		Set<ItensControleContrato> itens = new HashSet<>();

		for (int i = 0; i < 3; i++) {
			itenControleContrato.setNrEstudo("EA521" + i);
			itenControleContrato.setNomeProjeto("Insert Teste" + i);
			itenControleContrato.setProjeto("X-0743333" + i);
			itenControleContrato.setMunicipio("Lauro de Freitas");
			itenControleContrato.setEpi("");
			itenControleContrato.setOs("0012016");
			itenControleContrato.setParcelas(parcelas);
			itenControleContrato.setObservacoes("teste de insert na modelagem de dados");
			itens.add(itenControleContrato);
		}

		// Objeto Contrato
		IContrato contrato = new Contrato();

		contrato.setNrContrato("0012016");
		contrato.setNomeCliente("Elton");
		contrato.setItensControleContratos(itens);
		contrato.setDescricaoContrato("Contratos Teste Insert");
		
		manager.merge(contrato);
		trx.commit();
		manager.close();

	}

	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sysagregaPU");
		return factory.createEntityManager();

	}

}
