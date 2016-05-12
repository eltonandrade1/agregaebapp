package br.com.sysagrega.model.Enums;

public enum TipoSituacaoCliente {
	
	IMPLANTADA("Implatada"),
	EM_IMPLANTACAO("Em Implantação"),
	DESATIVADA("Desativada");
	
	private String descricao;

	private TipoSituacaoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
