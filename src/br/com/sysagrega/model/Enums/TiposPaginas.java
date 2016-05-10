package br.com.sysagrega.model.Enums;

public enum TiposPaginas {
	
	NOVO_PROF("Novo"),
	CONSULTA_PROF("Consultar"),
	VISUALIZAR_PROF("Visualizar"),
	EDIT_PROFI("Editar");
	
	private String descricao;

	private TiposPaginas(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
