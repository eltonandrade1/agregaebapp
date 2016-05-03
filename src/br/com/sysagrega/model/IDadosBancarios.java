package br.com.sysagrega.model;

import java.io.Serializable;

public interface IDadosBancarios extends Serializable{

	Long getNrAgencia();

	void setNrAgencia(Long nrAgencia);

	Integer getDigAgencia();

	void setDigAgencia(Integer digAgencia);

	Long getNrConta();

	void setNrConta(Long nrConta);

	Integer getDigConta();

	void setDigConta(Integer digConta);

	String getTipoConta();

	void setTipoConta(String tipoConta);

	void setIdBanco(IBanco idBanco);

	IBanco getIdBanco();

	void setId(Long id);

	Long getId();

}