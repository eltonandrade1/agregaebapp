package br.com.sysagrega.model.Enums;

public enum TipoProposta {
	
	TECNICA("Técnica"),
	FINANCEIRA("Financeira"),
	TECNICA_FINANCEIRA("Técnica Financeira");
	
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
