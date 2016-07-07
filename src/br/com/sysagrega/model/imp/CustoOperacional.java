package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoOperacional;

@Entity
@Table(name = "TB_CUSTO_OPERACIONAL")
public class CustoOperacional extends CustoBase implements ICustoOperacional {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
