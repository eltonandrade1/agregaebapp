package br.com.sysagrega.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sysagrega.model.Enums.TipoCliente;
import br.com.sysagrega.model.Enums.TipoSeguimentoCliente;
import br.com.sysagrega.model.Enums.TipoSituacaoCliente;
import br.com.sysagrega.model.Enums.TipoTamanhoCliente;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Profissional;

public class InsertCliente {
	
	private static Endereco endereco;
	
	private static Estado uf;
	
	private static Cidade cidade;
	
	private static Cliente cliente;

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
		
		//Cliente
		cliente = new Cliente();
		cliente.setNome("Empresa teste");
		cliente.setCnpj("222222222222222");
		cliente.setTamanho(TipoTamanhoCliente.GRANDE.getDescricao());
		cliente.setTipoCliente(TipoCliente.CARTEIRA.getDescricao());
		cliente.setSeguimento(TipoSeguimentoCliente.AGRICULTURA.getDescricao());
		cliente.setSituacao(TipoSituacaoCliente.EM_IMPLANTACAO.getDescricao());
		cliente.setEndereco(endereco);
		
		
		
		try {
			
			manager.persist(cliente);
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
	 * @return the endereco
	 */
	public static Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public static void setEndereco(Endereco endereco) {
		InsertCliente.endereco = endereco;
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
		InsertCliente.uf = uf;
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
		InsertCliente.cidade = cidade;
	}

	/**
	 * @return the cliente
	 */
	public static Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public static void setCliente(Cliente cliente) {
		InsertCliente.cliente = cliente;
	}
	
}
