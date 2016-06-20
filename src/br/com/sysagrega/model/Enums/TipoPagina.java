package br.com.sysagrega.model.Enums;

public enum TipoPagina {
	
	//A��es telas de profissional
	NOVO_PROF("Novo"),
	CONSULTA_PROF("Consultar"),
	VISUALIZAR_PROF("Visualizar"),
	EDIT_PROFI("Editar"),
	
	//A��es telas de clientes
	NOVO_CLIENTE("Novo Cliente"),
	CONSULTA_CLIENTE("Consultar Cliente"),
	VISUALIZAR_CLIENTE("Visualizar Cliente"),
	EDIT_CLIENTE("Editar Cliente"),
	
	//A��es telas de proposta
	NOVA_PROPOSTA("Nova Proposta"),
	CONSULTA_PROPOSTA("Consultar Proposta"),
	VISUALIZAR_PROPOSTA("Visualizar Proposta"),
	EDIT_PROPOSTA("Editar Proposta");
	
	private String descricao;

	private TipoPagina(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
