package br.com.sysagrega.model.Enums;

public enum TiposContaBancaria {
	
	CORRENTE("Corrente"),
	POUPANCA("Poupança"),
	SALARIO("Salário");
	
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
