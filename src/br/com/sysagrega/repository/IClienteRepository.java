package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;

public interface IClienteRepository extends Serializable{

	ICliente getClienteById(Long id);

	ICliente getClienteByCnpj(String cnpj);

	List<Cliente> getClienteByFilter(String nome, String cnpj, String situacao, String seguimento,
			String tipoCliente);

	ICliente saveOrMerge(ICliente cliente);

	void remover(ICliente cliente);

	List<Cliente> getAllClientes();

}