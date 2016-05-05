package br.com.sysagrega.service;

import java.io.Serializable;

import br.com.sysagrega.model.IProfissional;

public interface IProfissionalService extends Serializable {

	void salvar(IProfissional profissional);

}