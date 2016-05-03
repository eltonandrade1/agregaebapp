package br.com.sysagrega.service;

import java.io.Serializable;

import br.com.sysagrega.model.imp.Profissional;

public interface IProfissionalService extends Serializable {

	void salvar(Profissional profissional);

}