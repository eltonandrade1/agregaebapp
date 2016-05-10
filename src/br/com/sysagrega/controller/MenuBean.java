package br.com.sysagrega.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sysagrega.model.Enums.TiposPaginas;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String novoProfissional() {
		FacesUtil.addParamSession(TiposPaginas.NOVO_PROF);
		return "cadastro_profissional";

	}
	
	public String consultarProfissioanl() {
		
		//Set parametro na sessão
		FacesUtil.addParamSession(TiposPaginas.CONSULTA_PROF);
		return "consultar_profissional";
	}
	
	public String telaTeste() {
		
		return "novoTeste";

	}
}
