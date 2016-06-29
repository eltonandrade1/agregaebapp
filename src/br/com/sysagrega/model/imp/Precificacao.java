//package br.com.sysagrega.model.imp;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import br.com.sysagrega.model.IPrecificacao;
//
////@Entity
////@Table(name = "TB_PRECIFICACAO")
//public class Precificacao implements IPrecificacao {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@OneToMany(targetEntity = CustoExecucao.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "execucaoId")
//	private List<CustoExecucao> custos;
//
//	@OneToMany(targetEntity = CustoDeslocamento.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "deslocamentoId")
//	private List<CustoDeslocamento> despesasDeslocamentos;
//
//	@OneToMany(targetEntity = CustoOperacional.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "operacionalId")
//	private List<CustoOperacional> despesasOperacionais;
//
//	@OneToMany(targetEntity = CustoSeguranca.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "segurancaId")
//	private List<CustoSeguranca> despesasSeguranca;
//
//	@OneToMany(targetEntity = CustoAdministrativo.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "administrativoId")
//	private List<CustoAdministrativo> despesasAdministrativas;
//
//	@OneToMany(targetEntity = CustoBdiComissao.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "bdiComissaoId")
//	private List<CustoBdiComissao> despesasBdiComissao;
//
//	@OneToOne(mappedBy = "precificacao", targetEntity = Proposta.class)
////	private Proposta proposta;
//
//	private static final double ISS = 5;
//	private static final double COFINS = 3;
//	private static final double PIS = 0.65;
//	private static final double CSLL = 2.88;
//	private static final double IR = 4.8;
//	private static final double IMPOSTOS = 0.1633;
//
//	private double valorTotalImpostos;
//
//	private double valorTotalPrecificacao;
//
//	private double valorTotalCustosExecucao;
//
//	private double valorTotalCustosDesclocamento;
//
//	private double valorTotalCustosOperacionais;
//
//	private double valorTotalCustosAdministrativos;
//
//	private double valorTotalCustosBdiComissoes;
//
//	private double valorTotalCustosSeguranca;
//
//	private double valorTotalComBdiComissao;
//
//	private double valorTotalSemBdiComissao;
//
//	// Inicio dos metodo de calculos
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosExecucao() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getCustos().isEmpty()) {
//
//			for (CustoExecucao list : getCustos()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosDeslocamento() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getDespesasDeslocamentos().isEmpty()) {
//
//			for (CustoDeslocamento list : getDespesasDeslocamentos()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosOperacionais() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getDespesasOperacionais().isEmpty()) {
//
//			for (CustoOperacional list : getDespesasOperacionais()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosSeguranca() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getDespesasSeguranca().isEmpty()) {
//
//			for (CustoSeguranca list : getDespesasSeguranca()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosAdministraticos() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getDespesasAdministrativas().isEmpty()) {
//
//			for (CustoAdministrativo list : getDespesasAdministrativas()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//	
//	@Transient
//	@Override
//	public double getCalculoValorTotalCustosBdiComissao() {
//
//		double totalDosCustos = 0;
//		// Realiza a soma de todos os custos de execução
//		if (!getDespesasBdiComissao().isEmpty()) {
//
//			for (CustoBdiComissao list : getDespesasBdiComissao()) {
//
//				totalDosCustos += list.getValorTotal();
//
//			}
//		}
//
//		return totalDosCustos;
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see br.com.sysagrega.model.imp.IPrecificacao#getId()
//	 */
//	@Override
//	public Long getId() {
//		return id;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see br.com.sysagrega.model.imp.IPrecificacao#setId(java.lang.Long)
//	 */
//	@Override
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see br.com.sysagrega.model.imp.IPrecificacao#getCustos()
//	 */
//	@Override
//	public List<CustoExecucao> getCustos() {
//		return custos;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see br.com.sysagrega.model.imp.IPrecificacao#setCustos(java.util.List)
//	 */
//	@Override
//	public void setCustos(List<CustoExecucao> custos) {
//		this.custos = custos;
//	}
//
//	/**
//	 * @return the proposta
//	 */
//	@Override
//	public Proposta getProposta() {
//		return proposta;
//	}
//
//	/**
//	 * @param proposta
//	 *            the proposta to set
//	 */
//	@Override
//	public void setProposta(Proposta proposta) {
//		this.proposta = proposta;
//	}
//
//	/**
//	 * @return the valorTotalPrecificacao
//	 */
//	@Override
//	public double getValorTotalPrecificacao() {
//		return valorTotalPrecificacao;
//	}
//
//	/**
//	 * @param valorTotalPrecificacao
//	 *            the valorTotalPrecificacao to set
//	 */
//	@Override
//	public void setValorTotalPrecificacao(double valorTotalPrecificacao) {
//		this.valorTotalPrecificacao = valorTotalPrecificacao;
//	}
//
//	/**
//	 * @return the iSS
//	 */
//	@Override
//	public double getISS() {
//		return ISS;
//	}
//
//	/**
//	 * @return the cOFINS
//	 */
//	@Override
//	public double getCOFINS() {
//		return COFINS;
//	}
//
//	/**
//	 * @return the pIS
//	 */
//	@Override
//	public double getPIS() {
//		return PIS;
//	}
//
//	/**
//	 * @return the cSLL
//	 */
//	@Override
//	public double getCSLL() {
//		return CSLL;
//	}
//
//	/**
//	 * @return the iR
//	 */
//	@Override
//	public double getIR() {
//		return IR;
//	}
//
//	/**
//	 * @return the impostos
//	 */
//	@Override
//	public double getImpostos() {
//		return IMPOSTOS;
//	}
//
//	/**
//	 * @return the valorTotalImpostos
//	 */
//	@Override
//	public double getValorTotalImpostos() {
//		return valorTotalImpostos;
//	}
//
//	/**
//	 * @param valorTotalImpostos
//	 *            the valorTotalImpostos to set
//	 */
//	@Override
//	public void setValorTotalImpostos(double valorTotalImpostos) {
//		this.valorTotalImpostos = valorTotalImpostos;
//	}
//
//	/**
//	 * @return the despesasDeslocamentos
//	 */
//	@Override
//	public List<CustoDeslocamento> getDespesasDeslocamentos() {
//		return despesasDeslocamentos;
//	}
//
//	/**
//	 * @param despesasDeslocamentos
//	 *            the despesasDeslocamentos to set
//	 */
//	@Override
//	public void setDespesasDeslocamentos(List<CustoDeslocamento> despesasDeslocamentos) {
//		this.despesasDeslocamentos = despesasDeslocamentos;
//	}
//
//	/**
//	 * @return the despesasOperacionais
//	 */
//	@Override
//	public List<CustoOperacional> getDespesasOperacionais() {
//		return despesasOperacionais;
//	}
//
//	/**
//	 * @param despesasOperacionais
//	 *            the despesasOperacionais to set
//	 */
//	@Override
//	public void setDespesasOperacionais(List<CustoOperacional> despesasOperacionais) {
//		this.despesasOperacionais = despesasOperacionais;
//	}
//
//	/**
//	 * @return the despesasSeguranca
//	 */
//	@Override
//	public List<CustoSeguranca> getDespesasSeguranca() {
//		return despesasSeguranca;
//	}
//
//	/**
//	 * @param despesasSeguranca
//	 *            the despesasSeguranca to set
//	 */
//	@Override
//	public void setDespesasSeguranca(List<CustoSeguranca> despesasSeguranca) {
//		this.despesasSeguranca = despesasSeguranca;
//	}
//
//	/**
//	 * @return the despesasAdministrativas
//	 */
//	@Override
//	public List<CustoAdministrativo> getDespesasAdministrativas() {
//		return despesasAdministrativas;
//	}
//
//	/**
//	 * @param despesasAdministrativas
//	 *            the despesasAdministrativas to set
//	 */
//	@Override
//	public void setDespesasAdministrativas(List<CustoAdministrativo> despesasAdministrativas) {
//		this.despesasAdministrativas = despesasAdministrativas;
//	}
//
//	/**
//	 * @return the despesasBdiComissao
//	 */
//	@Override
//	public List<CustoBdiComissao> getDespesasBdiComissao() {
//		return despesasBdiComissao;
//	}
//
//	/**
//	 * @param despesasBdiComissao
//	 *            the despesasBdiComissao to set
//	 */
//	@Override
//	public void setDespesasBdiComissao(List<CustoBdiComissao> despesasBdiComissao) {
//		this.despesasBdiComissao = despesasBdiComissao;
//	}
//
//	/**
//	 * @return the valorTotalCustosExecucao
//	 */
//	@Override
//	public double getValorTotalCustosExecucao() {
//		return valorTotalCustosExecucao;
//	}
//
//	/**
//	 * @param valorTotalCustosExecucao
//	 *            the valorTotalCustosExecucao to set
//	 */
//	@Override
//	public void setValorTotalCustosExecucao(double valorTotalCustosExecucao) {
//		this.valorTotalCustosExecucao = valorTotalCustosExecucao;
//	}
//
//	/**
//	 * @return the valorTotalCustosDesclocamento
//	 */
//	@Override
//	public double getValorTotalCustosDesclocamento() {
//		return valorTotalCustosDesclocamento;
//	}
//
//	/**
//	 * @param valorTotalCustosDesclocamento
//	 *            the valorTotalCustosDesclocamento to set
//	 */
//	@Override
//	public void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento) {
//		this.valorTotalCustosDesclocamento = valorTotalCustosDesclocamento;
//	}
//
//	/**
//	 * @return the valorTotalCustosOperacionais
//	 */
//	@Override
//	public double getValorTotalCustosOperacionais() {
//		return valorTotalCustosOperacionais;
//	}
//
//	/**
//	 * @param valorTotalCustosOperacionais
//	 *            the valorTotalCustosOperacionais to set
//	 */
//	@Override
//	public void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais) {
//		this.valorTotalCustosOperacionais = valorTotalCustosOperacionais;
//	}
//
//	/**
//	 * @return the valorTotalCustosAdministrativos
//	 */
//	@Override
//	public double getValorTotalCustosAdministrativos() {
//		return valorTotalCustosAdministrativos;
//	}
//
//	/**
//	 * @param valorTotalCustosAdministrativos
//	 *            the valorTotalCustosAdministrativos to set
//	 */
//	@Override
//	public void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos) {
//		this.valorTotalCustosAdministrativos = valorTotalCustosAdministrativos;
//	}
//
//	/**
//	 * @return the valorTotalCustosBdiComissoes
//	 */
//	@Override
//	public double getValorTotalCustosBdiComissoes() {
//		return valorTotalCustosBdiComissoes;
//	}
//
//	/**
//	 * @param valorTotalCustosBdiComissoes
//	 *            the valorTotalCustosBdiComissoes to set
//	 */
//	@Override
//	public void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes) {
//		this.valorTotalCustosBdiComissoes = valorTotalCustosBdiComissoes;
//	}
//
//	/**
//	 * @return the valorTotalCustosSeguranca
//	 */
//	@Override
//	public double getValorTotalCustosSeguranca() {
//		return valorTotalCustosSeguranca;
//	}
//
//	/**
//	 * @param valorTotalCustosSeguranca
//	 *            the valorTotalCustosSeguranca to set
//	 */
//	@Override
//	public void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca) {
//		this.valorTotalCustosSeguranca = valorTotalCustosSeguranca;
//	}
//
//	/**
//	 * @return the valorTotalComBdiComissao
//	 */
//	@Override
//	public double getValorTotalComBdiComissao() {
//		return valorTotalComBdiComissao;
//	}
//
//	/**
//	 * @param valorTotalComBdiComissao
//	 *            the valorTotalComBdiComissao to set
//	 */
//	@Override
//	public void setValorTotalComBdiComissao(double valorTotalComBdiComissao) {
//		this.valorTotalComBdiComissao = valorTotalComBdiComissao;
//	}
//
//	/**
//	 * @return the valorTotalSemBdiComissao
//	 */
//	@Override
//	public double getValorTotalSemBdiComissao() {
//		return valorTotalSemBdiComissao;
//	}
//
//	/**
//	 * @param valorTotalSemBdiComissao
//	 *            the valorTotalSemBdiComissao to set
//	 */
//	@Override
//	public void setValorTotalSemBdiComissao(double valorTotalSemBdiComissao) {
//		this.valorTotalSemBdiComissao = valorTotalSemBdiComissao;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((custos == null) ? 0 : custos.hashCode());
//		result = prime * result + ((despesasAdministrativas == null) ? 0 : despesasAdministrativas.hashCode());
//		result = prime * result + ((despesasBdiComissao == null) ? 0 : despesasBdiComissao.hashCode());
//		result = prime * result + ((despesasDeslocamentos == null) ? 0 : despesasDeslocamentos.hashCode());
//		result = prime * result + ((despesasOperacionais == null) ? 0 : despesasOperacionais.hashCode());
//		result = prime * result + ((despesasSeguranca == null) ? 0 : despesasSeguranca.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
//		long temp;
//		temp = Double.doubleToLongBits(valorTotalComBdiComissao);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosAdministrativos);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosBdiComissoes);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosDesclocamento);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosExecucao);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosOperacionais);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalCustosSeguranca);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalImpostos);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalPrecificacao);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(valorTotalSemBdiComissao);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		return result;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof Precificacao))
//			return false;
//		Precificacao other = (Precificacao) obj;
//		if (custos == null) {
//			if (other.custos != null)
//				return false;
//		} else if (!custos.equals(other.custos))
//			return false;
//		if (despesasAdministrativas == null) {
//			if (other.despesasAdministrativas != null)
//				return false;
//		} else if (!despesasAdministrativas.equals(other.despesasAdministrativas))
//			return false;
//		if (despesasBdiComissao == null) {
//			if (other.despesasBdiComissao != null)
//				return false;
//		} else if (!despesasBdiComissao.equals(other.despesasBdiComissao))
//			return false;
//		if (despesasDeslocamentos == null) {
//			if (other.despesasDeslocamentos != null)
//				return false;
//		} else if (!despesasDeslocamentos.equals(other.despesasDeslocamentos))
//			return false;
//		if (despesasOperacionais == null) {
//			if (other.despesasOperacionais != null)
//				return false;
//		} else if (!despesasOperacionais.equals(other.despesasOperacionais))
//			return false;
//		if (despesasSeguranca == null) {
//			if (other.despesasSeguranca != null)
//				return false;
//		} else if (!despesasSeguranca.equals(other.despesasSeguranca))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (proposta == null) {
//			if (other.proposta != null)
//				return false;
//		} else if (!proposta.equals(other.proposta))
//			return false;
//		if (Double.doubleToLongBits(valorTotalComBdiComissao) != Double
//				.doubleToLongBits(other.valorTotalComBdiComissao))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosAdministrativos) != Double
//				.doubleToLongBits(other.valorTotalCustosAdministrativos))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosBdiComissoes) != Double
//				.doubleToLongBits(other.valorTotalCustosBdiComissoes))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosDesclocamento) != Double
//				.doubleToLongBits(other.valorTotalCustosDesclocamento))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosExecucao) != Double
//				.doubleToLongBits(other.valorTotalCustosExecucao))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosOperacionais) != Double
//				.doubleToLongBits(other.valorTotalCustosOperacionais))
//			return false;
//		if (Double.doubleToLongBits(valorTotalCustosSeguranca) != Double
//				.doubleToLongBits(other.valorTotalCustosSeguranca))
//			return false;
//		if (Double.doubleToLongBits(valorTotalImpostos) != Double.doubleToLongBits(other.valorTotalImpostos))
//			return false;
//		if (Double.doubleToLongBits(valorTotalPrecificacao) != Double.doubleToLongBits(other.valorTotalPrecificacao))
//			return false;
//		if (Double.doubleToLongBits(valorTotalSemBdiComissao) != Double
//				.doubleToLongBits(other.valorTotalSemBdiComissao))
//			return false;
//		return true;
//	}
//}
