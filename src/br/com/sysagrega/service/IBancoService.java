package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.util.cdi.Transactional;

public interface IBancoService extends Serializable{

	List<Banco> getAllBancos();

	Banco getBancoById(Long id);

}