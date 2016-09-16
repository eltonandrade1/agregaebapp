package br.com.sysagrega.util.jsf;

import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.Proposta;

public class FacesUtil {

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public static void addParamSession(TipoPagina tipo) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("idView", tipo);

	}

	public static TipoPagina getParamSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (TipoPagina) session.getAttribute("idView");

	}

	public static IProfissional getProfissionalSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (IProfissional) session.getAttribute("profissional");

	}

	public static ICliente getClienteSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (ICliente) session.getAttribute("cliente");

	}

	public static Proposta getPropostaSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (Proposta) session.getAttribute("proposta");

	}
	
	public static Contrato getContratoSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (Contrato) session.getAttribute("contrato");

	}
	
	public static HttpServletResponse getResponseJsf() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	public static InputStream getReportStream(String caminhoJasper) {
		
		return FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(caminhoJasper);
		
	}
	
	public static void responseComplete() {
		
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
	public static void invalidateSession() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
	}
	
	
}