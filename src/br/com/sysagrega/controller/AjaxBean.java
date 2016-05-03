package br.com.sysagrega.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class AjaxBean {

	private String nome;
	
	public void acaoBtnSinc() {
		System.out.println("p:commandButton sem ajax ");
	}
	
	public void acaoBtnAsinc() {
		System.out.println("p:commandButton com ajax ");
	}
	
	public String getNomeUpperCase() {
		if(nome != null) {
			return nome.toUpperCase();
		}
		return null;
	}
	
	public String getNomeLowerCase() {
		if(nome != null) {
			return nome.toLowerCase();
		}
		return null;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
