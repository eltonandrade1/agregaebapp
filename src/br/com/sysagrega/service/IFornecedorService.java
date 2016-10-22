package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;


public interface IFornecedorService extends Serializable {
	
	void salvar(IFornecedor fornecedores);

	List<Fornecedor> getAllFornecedor();

	IFornecedor atualizarFornecedor(IFornecedor fornecedor);

	void excluirFornecedor(IFornecedor fornecedor);

	List<Fornecedor> getFornecedorByFilter(String cnpj, String nomeFantasia);


}
