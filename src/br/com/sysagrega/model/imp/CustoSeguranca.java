package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoSeguranca;

@Entity
@Table(name = "TB_CUSTO_SEGURANCA")
public class CustoSeguranca extends CustoBase implements ICustoSeguranca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
