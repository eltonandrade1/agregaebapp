package br.com.sysagrega.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AjaxPollBean {
	
	private int cont;
	
	private boolean habilitado;
	
	public int getCont() {
		cont = cont + 10;
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public void clicou() {
		System.out.println("pegou");
	}



}
