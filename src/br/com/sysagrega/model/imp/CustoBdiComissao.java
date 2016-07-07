package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoBdiComissao;

@Entity
@Table(name = "TB_CUSTO_BDI_COMISSAO")
public class CustoBdiComissao extends CustoBase implements ICustoBdiComissao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
