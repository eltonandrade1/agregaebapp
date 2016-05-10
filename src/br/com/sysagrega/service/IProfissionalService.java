package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProfissional;

public interface IProfissionalService extends Serializable {

	void salvar(IProfissional profissional);

	List<IProfissional> getAllProfissionals();

	IProfissional atualizarProfissional(IProfissional profissional);

	void excluirProfissional(IProfissional profissional);

}