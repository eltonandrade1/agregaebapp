package br.com.sysagrega.model.Enums;

public enum TiposContaBancaria {
	
	CORRENTE("Corrente"),
	POUPANCA("Poupan�a"),
	SALARIO("Sal�rio");
	
	private String descricao;

	private TiposContaBancaria(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
