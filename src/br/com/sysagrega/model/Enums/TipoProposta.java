package br.com.sysagrega.model.Enums;

public enum TipoProposta {
	
	TECNICA("T�cnica"),
	FINANCEIRA("Financeira"),
	TECNICA_FINANCEIRA("T�cnica Financeira");
	
	private String descricao;

	private TipoProposta(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
