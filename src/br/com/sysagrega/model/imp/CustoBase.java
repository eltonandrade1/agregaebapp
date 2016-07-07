package br.com.sysagrega.model.imp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.sysagrega.model.ICustoBase;
import br.com.sysagrega.model.IProposta;

@MappedSuperclass
public abstract class CustoBase implements ICustoBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private long quantidade;

	private double valorUnitario;

	private double valorTotal;
	
	private String observacoes;
	
	private Proposta proposta;

	
//	@ManyToOne(targetEntity = Precificacao.class)
//	private IPrecificacao precificacao;

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getDescricao()
	 */
	@Override
	public String getDescricao() {
		return descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setDescricao(java.lang.String)
	 */
	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getQuantidade()
	 */
	@Override
	public long getQuantidade() {
		return quantidade;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setQuantidade(long)
	 */
	@Override
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getValorUnitario()
	 */
	@Override
	public double getValorUnitario() {
		return valorUnitario;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setValorUnitario(double)
	 */
	@Override
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getValorTotal()
	 */
	@Override
	public double getValorTotal() {
		return valorTotal;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setValorTotal(double)
	 */
	@Override
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getPrecificacao()
	 */
//	@Override
//	public IPrecificacao getPrecificacao() {
//		return precificacao;
//	}
//
//	/* (non-Javadoc)
//	 * @see br.com.sysagrega.model.imp.ICustoBase#setPrecificacao(br.com.sysagrega.model.IPrecificacao)
//	 */
//	@Override
//	public void setPrecificacao(IPrecificacao precificacao) {
//		this.precificacao = precificacao;
//	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the observacoes
	 */
	@Override
	public String getObservacoes() {
		return observacoes;
	}

	/**
	 * @param observacoes the observacoes to set
	 */
	@Override
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	/**
	 * @return the proposta
	 */
	@Override
	public IProposta getProposta() {
		return proposta;
	}

	/**
	 * @param proposta the proposta to set
	 */
	@Override
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
		result = prime * result + (int) (quantidade ^ (quantidade >>> 32));
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof CustoBase))
			return false;
		CustoBase other = (CustoBase) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (proposta == null) {
			if (other.proposta != null)
				return false;
		} else if (!proposta.equals(other.proposta))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double.doubleToLongBits(other.valorUnitario))
			return false;
		return true;
	}
	
}
