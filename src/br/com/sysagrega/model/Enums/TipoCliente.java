package br.com.sysagrega.model.Enums;

public enum TipoCliente {
	
	CARTEIRA("Carteira"),
	POTENCIAL("Potencial");
	
	private String descricao;

	private TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
