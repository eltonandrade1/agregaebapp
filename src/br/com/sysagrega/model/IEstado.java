package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Cidade;

public interface IEstado extends Serializable{

	Long getId();

	void setId(Long id);

	String getUf();

	void setUf(String uf);

	String getDescricaoUf();

	void setDescricaoUf(String descricaoUf);

	String getCodigoIbge();

	void setCodigoIbge(String codigoIbge);

}