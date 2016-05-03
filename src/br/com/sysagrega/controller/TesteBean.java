package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TesteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String autoComplete;
	private String nomeKeyup;
	
	private List<String> lista  = new ArrayList<>();

	@PostConstruct
	private void init() {

		lista.add("Elton");
		lista.add("Ramon");
		lista.add("Marcus");
		lista.add("Eliene");
		lista.add("Taiane");
		lista.add("Lyon");
		lista.add("Aquiles");
		System.out.println("carregou aqui");

	}

	public List<String> getListaNomes(String nomeInformado) {

		List<String> listaFinal = new ArrayList<>();
		for (String nome : lista) {
			if (nome.contains(nomeInformado)) {
				listaFinal.add(nome);
			}
		}
		return listaFinal;
	}

	public String getNomeReverso() {
		if (nomeKeyup != null) {
			return new StringBuilder(nomeKeyup).reverse().toString();
		} else {
			return null;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String btEnviar() {
		// addMessage(getNome());
		return "redirect_1";
	}

	public void addMessage(String nome) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, nome, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getAutoComplete() {
		return autoComplete;
	}

	public void setAutoComplete(String autoComplete) {
		this.autoComplete = autoComplete;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public String getNomeKeyup() {
		return nomeKeyup;
	}

	public void setNomeKeyup(String nomeKeyup) {
		System.out.println("@@@TesteBean");
		this.nomeKeyup = nomeKeyup;
	}
}
