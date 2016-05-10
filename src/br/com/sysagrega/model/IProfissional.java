package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IProfissional extends Serializable {

	Long getId();

	void setId(Long id);

	String getNome();
	
	void setNome(String nome);

	String getProfissao();

	void setProfissao(String profissao);

	String getAtuacaoEspecializacao();

	void setAtuacaoEspecializacao(String atuacaoEspecializacao);

	Character getContratoTemporario();

	void setContratoTemporario(Character contratoTemporario);

	void setEndereco(IEndereco endereco);

	IEndereco getEndereco();

	void setEmail(String email);

	String getEmail();

	void setCelular(String celular);

	String getCelular();

	void setTelefone(String telefone);

	String getTelefone();

	void setDataNascimento(Date dataNascimento);

	Date getDataNascimento();

	void setNumeroSapato(Integer numeroSapato);

	Integer getNumeroSapato();

	void setTamanhoCalca(String tamanhoCalca);

	String getTamanhoCalca();

	void setTamanhoCamisa(String tamanhoCamisa);

	String getTamanhoCamisa();

	void setNumeroConsClasse(String numeroConsClasse);

	String getNumeroConsClasse();

	void setCarteiraTrabalho(String carteiraTrabalho);

	String getCarteiraTrabalho();

	void setPisPasep(String pisPasep);

	String getPisPasep();

	void setRg(String rg);

	String getRg();

	void setCpf(String cpf);

	String getCpf();

	void setDadosBancarios(IDadosBancarios dadosBancarios);

	IDadosBancarios getDadosBancarios();

	boolean isExistente();

	boolean isNovo();

}
