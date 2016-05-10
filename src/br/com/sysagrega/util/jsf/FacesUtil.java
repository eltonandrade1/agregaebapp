package br.com.sysagrega.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.Enums.TiposPaginas;

public class FacesUtil {

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addParamSession(TiposPaginas tipo) {
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idView", tipo);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("idView", tipo );
		
	}
	
	public static TiposPaginas getParamSession() {
//		return  (TiposPaginas) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("idView");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (TiposPaginas) session.getAttribute("idView");
		
	}
	
	public static IProfissional getProfissionalSession() {
//		return  (TiposPaginas) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("idView");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return (IProfissional) session.getAttribute("profissional");
		
	}
	
}