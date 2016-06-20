package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoAdministrativo;

@Entity
@Table(name = "TB_CUSTO_ADMINISTRATIVO")
public class CustoAdministrativo extends CustoBase implements ICustoAdministrativo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
