package br.com.sysagrega.model.Enums;

public enum TipoSeguimentoCliente {
	
	PLASTICO("Pl�stico"),
	ALIMENTOS("Alimentos"),
	CALCADOS("Cal�ados"),
	TINTAS("Tintas"),
	AGRICULTURA("Agricultura"),
	IND_MINERIOS("Ind. Minerios"),
	VESTIARIO("Vesti�rio"),
	FARMACOS("F�rmacos"),
	MOVEIS("M�veis"),
	COURO("Couro"),
	ENERGIA("Energia"),
	COMERCIO("Com�rcio"),
	CONST_CIVIL("Const. Civil"),
	TRANSPORTE("Transporte"),
	ARMAZEM("Armaz�m"),
	CERAMICA("Ceramica"),
	PAPEL("Papel"),
	PERFUMARIA("Perfumaria"),
	QUIM_PETROQ("Qu�mPetroq");
	
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
