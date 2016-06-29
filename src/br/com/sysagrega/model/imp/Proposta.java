package br.com.sysagrega.model.imp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.IProposta;

@Entity
@Table(name = "TB_PROPOSTA")
public class Proposta extends PropostaBase implements IProposta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(targetEntity = CustoExecucao.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostaId")
	private Set<CustoExecucao> custos;

	@OneToMany(targetEntity = CustoDeslocamento.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostaId")
	private Set<CustoDeslocamento> despesasDeslocamentos;

	@OneToMany(targetEntity = CustoOperacional.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostalId")
	private Set<CustoOperacional> despesasOperacionais;

	@OneToMany(targetEntity = CustoSeguranca.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostaId")
	private Set<CustoSeguranca> despesasSeguranca;

	@OneToMany(targetEntity = CustoAdministrativo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostaId")
	private Set<CustoAdministrativo> despesasAdministrativas;

	@OneToMany(targetEntity = CustoBdiComissao.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "propostaId")
	private Set<CustoBdiComissao> despesasBdiComissao;

	@Transient
	@Override
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}

	// Inicio dos metodo de calculos
	@Transient
	@Override
	public double getCalculoValorTotalCustosExecucao() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getCustos().isEmpty()) {

			for (CustoExecucao list : getCustos()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosDeslocamento() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getDespesasDeslocamentos().isEmpty()) {

			for (CustoDeslocamento list : getDespesasDeslocamentos()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosOperacionais() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getDespesasOperacionais().isEmpty()) {

			for (CustoOperacional list : getDespesasOperacionais()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosSeguranca() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getDespesasSeguranca().isEmpty()) {

			for (CustoSeguranca list : getDespesasSeguranca()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosAdministraticos() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getDespesasAdministrativas().isEmpty()) {

			for (CustoAdministrativo list : getDespesasAdministrativas()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosBdiComissao() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getDespesasBdiComissao().isEmpty()) {

			for (CustoBdiComissao list : getDespesasBdiComissao()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	/**
	 * @return the custos
	 */
	@Override
	public Set<CustoExecucao> getCustos() {
		return custos;
	}

	/**
	 * @param custos
	 *            the custos to set
	 */
	@Override
	public void setCustos(Set<CustoExecucao> custos) {
		this.custos = custos;
	}

	/**
	 * @return the despesasDeslocamentos
	 */
	@Override
	public Set<CustoDeslocamento> getDespesasDeslocamentos() {
		return despesasDeslocamentos;
	}

	/**
	 * @param despesasDeslocamentos
	 *            the despesasDeslocamentos to set
	 */
	@Override
	public void setDespesasDeslocamentos(Set<CustoDeslocamento> despesasDeslocamentos) {
		this.despesasDeslocamentos = despesasDeslocamentos;
	}

	/**
	 * @return the despesasOperacionais
	 */
	@Override
	public Set<CustoOperacional> getDespesasOperacionais() {
		return despesasOperacionais;
	}

	/**
	 * @param despesasOperacionais
	 *            the despesasOperacionais to set
	 */
	@Override
	public void setDespesasOperacionais(Set<CustoOperacional> despesasOperacionais) {
		this.despesasOperacionais = despesasOperacionais;
	}

	/**
	 * @return the despesasSeguranca
	 */
	@Override
	public Set<CustoSeguranca> getDespesasSeguranca() {
		return despesasSeguranca;
	}

	/**
	 * @param despesasSeguranca
	 *            the despesasSeguranca to set
	 */
	@Override
	public void setDespesasSeguranca(Set<CustoSeguranca> despesasSeguranca) {
		this.despesasSeguranca = despesasSeguranca;
	}

	/**
	 * @return the despesasAdministrativas
	 */
	@Override
	public Set<CustoAdministrativo> getDespesasAdministrativas() {
		return despesasAdministrativas;
	}

	/**
	 * @param despesasAdministrativas
	 *            the despesasAdministrativas to set
	 */
	@Override
	public void setDespesasAdministrativas(Set<CustoAdministrativo> despesasAdministrativas) {
		this.despesasAdministrativas = despesasAdministrativas;
	}

	/**
	 * @return the despesasBdiComissao
	 */
	@Override
	public Set<CustoBdiComissao> getDespesasBdiComissao() {
		return despesasBdiComissao;
	}

	/**
	 * @param despesasBdiComissao
	 *            the despesasBdiComissao to set
	 */
	@Override
	public void setDespesasBdiComissao(Set<CustoBdiComissao> despesasBdiComissao) {
		this.despesasBdiComissao = despesasBdiComissao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custos == null) ? 0 : custos.hashCode());
		result = prime * result + ((despesasAdministrativas == null) ? 0 : despesasAdministrativas.hashCode());
		result = prime * result + ((despesasBdiComissao == null) ? 0 : despesasBdiComissao.hashCode());
		result = prime * result + ((despesasDeslocamentos == null) ? 0 : despesasDeslocamentos.hashCode());
		result = prime * result + ((despesasOperacionais == null) ? 0 : despesasOperacionais.hashCode());
		result = prime * result + ((despesasSeguranca == null) ? 0 : despesasSeguranca.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Proposta))
			return false;
		Proposta other = (Proposta) obj;
		if (custos == null) {
			if (other.custos != null)
				return false;
		} else if (!custos.equals(other.custos))
			return false;
		if (despesasAdministrativas == null) {
			if (other.despesasAdministrativas != null)
				return false;
		} else if (!despesasAdministrativas.equals(other.despesasAdministrativas))
			return false;
		if (despesasBdiComissao == null) {
			if (other.despesasBdiComissao != null)
				return false;
		} else if (!despesasBdiComissao.equals(other.despesasBdiComissao))
			return false;
		if (despesasDeslocamentos == null) {
			if (other.despesasDeslocamentos != null)
				return false;
		} else if (!despesasDeslocamentos.equals(other.despesasDeslocamentos))
			return false;
		if (despesasOperacionais == null) {
			if (other.despesasOperacionais != null)
				return false;
		} else if (!despesasOperacionais.equals(other.despesasOperacionais))
			return false;
		if (despesasSeguranca == null) {
			if (other.despesasSeguranca != null)
				return false;
		} else if (!despesasSeguranca.equals(other.despesasSeguranca))
			return false;
		return true;
	}
}
