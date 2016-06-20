package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoExecucao;

@Entity
@Table(name = "TB_CUSTO_EXECUCAO")
public class CustoExecucao extends CustoBase implements ICustoExecucao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
