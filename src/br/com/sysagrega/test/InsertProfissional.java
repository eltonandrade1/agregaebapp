package br.com.sysagrega.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Profissional;

public class InsertProfissional {
	
	private static Profissional profissional;
	
	private static Endereco endereco;
	
	private static Estado uf;
	
	private static Cidade cidade;

	public static void main(String[] args) {
		
		//Conexão
		EntityManager manager = getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		//Estado
		Estado estado = new Estado();
		estado.setId(5L);
		estado.setUf("BA");
		estado.setDescricaoUf("Bahia");
		estado.setCodigoIbge("29");
		
		//Cidade
		Cidade cidade = new Cidade();
		cidade.setId(616L);
		cidade.setEstado(estado);
		cidade.setNome("Salvador");
		
		//Endereço
		Endereco endereco = new Endereco();
		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		endereco.setRua("rua A");
		endereco.setBairro("caji");
		
		//Banco
		Banco banco = new Banco();
		banco.setId(1L);
		banco.setNome("001 - BANCO DO BRASIL S/A");
		banco.setCodigo(1L);
		
		//Dados Bancarios
		DadosBancarios dadosBancarios = new DadosBancarios();
		dadosBancarios.setIdBanco(banco);
		dadosBancarios.setTipoConta("Corrente");
		
		
		profissional = new Profissional();
		profissional.setNome("Primeiro Teste Nome@@");
		profissional.setProfissao("Analista Peão!!!");
		profissional.setContratoTemporario('N');
		profissional.setAtuacaoEspecializacao("Arquiteto de Software 4");
		profissional.setCpf("00642748535");
		profissional.setCelular("71987232636");
		profissional.setEndereco(endereco);
		profissional.setDadosBancarios(dadosBancarios);
		
		try {
			
			manager.persist(profissional);
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
	

	/**
	 * @return the profissional
	 */
	public static Profissional getProfissional() {
		return profissional;
	}

	/**
	 * @param profissional the profissional to set
	 */
	public static void setProfissional(Profissional profissional) {
		InsertProfissional.profissional = profissional;
	}

	/**
	 * @return the endereco
	 */
	public static Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public static void setEndereco(Endereco endereco) {
		InsertProfissional.endereco = endereco;
	}

	/**
	 * @return the uf
	 */
	public static Estado getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public static void setUf(Estado uf) {
		InsertProfissional.uf = uf;
	}

	/**
	 * @return the cidade
	 */
	public static Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public static void setCidade(Cidade cidade) {
		InsertProfissional.cidade = cidade;
	}
	
}
