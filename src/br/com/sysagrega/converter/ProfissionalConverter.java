package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.repository.imp.EstadoRepository;
import br.com.sysagrega.repository.imp.ProfissionalRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

//@FacesConverter(forClass = Profissional.class)
//public class ProfissionalConverter implements Converter {
//
//	//@Inject
//	private ProfissionalRepository profissionalRepository;
//	
//	public ProfissionalConverter() {
//		profissionalRepository = CDIServiceLocator.getBean(ProfissionalRepository.class);
//	}
//	
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Profissional retorno = null;
//		
//		if (value != null) {
//			Long id = new Long(value);
//			retorno = profissionalRepository.getProfissionalById(id);
//		}
//		
//		return retorno;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null) {
//			return ((Profissional) value).getId().toString();
//		}
//		
//		return "";
//	}
//
//}
