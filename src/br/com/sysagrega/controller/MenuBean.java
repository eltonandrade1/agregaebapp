package br.com.sysagrega.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String novoProfissional() {
		FacesUtil.addParamSession(TipoPagina.NOVO_PROF);
		return "cadastro_profissional";

	}

	public String consultarProfissional() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_PROF);
		return "consultar_profissional";
	}

	public String novoCliente() {
		FacesUtil.addParamSession(TipoPagina.NOVO_CLIENTE);
		return "cadastro_cliente";

	}

	public String consultarCliente() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_CLIENTE);
		return "consultar_cliente";
	}

	public String novaProposta() {
		FacesUtil.addParamSession(TipoPagina.NOVA_PROPOSTA);
		return "cadastro_proposta";
	}

	public String consultarProposta() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_PROPOSTA);
		return "consultar_proposta";
	}

	public String novoContrato() {
		FacesUtil.addParamSession(TipoPagina.NOVO_CONTRATO);
		return "cadastro_contrato";
	}

	public String consultarContrato() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_CONTRATO);
		return "consultar_contrato";
	}

	public void reset() {
		RequestContext.getCurrentInstance().reset("formPrincipal:layoutCentro");

	}
}
