package br.com.sysagrega.model.Enums;

public enum TipoSeguimentoCliente {
	
	PLASTICO("Plástico"),
	ALIMENTOS("Alimentos"),
	CALCADOS("Calçados"),
	TINTAS("Tintas"),
	AGRICULTURA("Agricultura"),
	IND_MINERIOS("Ind. Minerios"),
	VESTIARIO("Vestiário"),
	FARMACOS("Fármacos"),
	MOVEIS("Móveis"),
	COURO("Couro"),
	ENERGIA("Energia"),
	COMERCIO("Comércio"),
	CONST_CIVIL("Const. Civil"),
	TRANSPORTE("Transporte"),
	ARMAZEM("Armazém"),
	CERAMICA("Ceramica"),
	PAPEL("Papel"),
	PERFUMARIA("Perfumaria"),
	QUIM_PETROQ("QuímPetroq");
	
	private String descricao;

	private TipoSeguimentoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
