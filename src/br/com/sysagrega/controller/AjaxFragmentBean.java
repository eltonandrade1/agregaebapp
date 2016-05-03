package br.com.sysagrega.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class AjaxFragmentBean {

	private String nome;
	
	private String nome2;
	
	public void acaoBtnSinc() {
		System.out.println("p:commandButton sem ajax ");
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		System.out.println("Nome: " + nome);
		this.nome = nome;
	}

	public String getNome2() {
		return nome2;
	}

	public void setNome2(String nome2) {
		System.out.println("Nome2: " + nome2);
		this.nome2 = nome2;
	}

}
