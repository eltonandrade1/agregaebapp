package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IFornecedor extends Serializable{
	
	void setId(Long id);
	
	Long getId();

	void setNomeFantasia(String nomeFantasia);
	
	String getNomeFantasia();
	
	void setRazaoSocial(String razaoSocial);
	
	String getRazaoSocial();
	
	void setRamoAtividade(String ramoAtividade);
	
	String getRamoAtividade();
	
	void setPessoaContato(String pessoaContato);
	
	String getPessoaContato();
	
	void setEndereco(IEndereco endereco);

	IEndereco getEndereco();
	
	void setEmail(String email);

	String getEmail();

	void setCelular(String celular);

	String getCelular();

	void setTelefone(String telefone);

	String getTelefone();

	void setDataCadastro(Date dataCadastro);

	Date getDataCadastro();

	void setIscricaoEstadual(String iscricaoEstadual);

	String getIscricaoEstadual();
			
	void setIscricaoMunicipal(String iscricaoMunicipal);

	String getIscricaoMunicipal();

	void setCnpj(String cnpj);

	String getCnpj();
	
	void setDadosBancarios(IDadosBancarios dadosBancarios);

	IDadosBancarios getDadosBancarios();
	
	boolean isExistente();

	boolean isNovo();

}