package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoDeslocamento;

@Entity
@Table(name = "TB_CUSTO_DESLOCAMENTO")
public class CustoDeslocamento extends CustoBase implements ICustoDeslocamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
