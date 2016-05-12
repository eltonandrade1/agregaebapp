package br.com.sysagrega.model;

import java.io.Serializable;

public interface ICliente extends Serializable{

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the nome
	 */
	String getNome();

	/**
	 * @param nome the nome to set
	 */
	void setNome(String nome);

	/**
	 * @return the cnae
	 */
	String getCnae();

	/**
	 * @param cnae the cnae to set
	 */
	void setCnae(String cnae);

	/**
	 * @return the endereco
	 */
	IEndereco getEndereco();

	/**
	 * @param endereco the endereco to set
	 */
	void setEndereco(IEndereco endereco);

	/**
	 * @return the telefone
	 */
	String getTelefone();

	/**
	 * @param telefone the telefone to set
	 */
	void setTelefone(String telefone);

	/**
	 * @return the celular
	 */
	String getCelular();

	/**
	 * @param celular the celular to set
	 */
	void setCelular(String celular);

	/**
	 * @return the email
	 */
	String getEmail();

	/**
	 * @param email the email to set
	 */
	void setEmail(String email);

	/**
	 * @return the fax
	 */
	String getFax();

	/**
	 * @param fax the fax to set
	 */
	void setFax(String fax);

	/**
	 * @return the cnpj
	 */
	String getCnpj();

	/**
	 * @param cnpj the cnpj to set
	 */
	void setCnpj(String cnpj);

	/**
	 * @return the ie
	 */
	String getIe();

	/**
	 * @param ie the ie to set
	 */
	void setIe(String ie);

	/**
	 * @return the contato
	 */
	String getContato();

	/**
	 * @param contato the contato to set
	 */
	void setContato(String contato);

	/**
	 * @return the funcaoDep
	 */
	String getFuncaoDep();

	/**
	 * @param funcaoDep the funcaoDep to set
	 */
	void setFuncaoDep(String funcaoDep);

	/**
	 * @return the pendenciaAmbiental
	 */
	Character getPendenciaAmbiental();

	/**
	 * @param pendenciaAmbiental the pendenciaAmbiental to set
	 */
	void setPendenciaAmbiental(Character pendenciaAmbiental);

	boolean isNovo();

	boolean isExistente();

	String getSeguimento();

	void setSeguimento(String seguimento);

	String getTamanho();

	void setTamanho(String tamanho);

	String getTipoCliente();

	void setTipoCliente(String tipoCliente);

	String getSituacao();

	void setSituacao(String situacao);

	void setPendencia(String pendencia);

	String getPendencia();

	void setProcesso(String processo);

	String getProcesso();

}