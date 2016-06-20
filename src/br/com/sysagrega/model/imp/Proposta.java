package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.IProposta;

@Entity
@Table(name  = "TB_PROPOSTA")
public class Proposta extends PropostaBase implements IProposta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	@Transient
	@Override
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}
}
