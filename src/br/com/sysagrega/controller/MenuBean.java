package br.com.sysagrega.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String novoProfissional() {
		
//		return "/paginas/Profissional.xhtml?faces-redirect=true";
		return "cadastro_profissional";

	}
	
	public String telaTeste() {
		
//		return "/paginas/novoTeste.xhtml?faces-redirect=true";
		return "novoTeste";

	}

}
