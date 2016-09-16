package br.com.sysagrega.model.imp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.IContrato;

@Entity
@Table(name  = "TB_CONTRATO")
public class Contrato implements IContrato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nrContrato;
	
	private String nomeCliente;
	
	private String descricaoContrato;
	
	@OneToMany(targetEntity = ItensControleContrato.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contratoId")
	private Set<ItensControleContrato> itensControleContratos;
	
	
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

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getNrContrato()
	 */
	@Override
	public String getNrContrato() {
		return nrContrato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setNrContrato(java.lang.String)
	 */
	@Override
	public void setNrContrato(String nrContrato) {
		this.nrContrato = nrContrato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getNomeCliente()
	 */
	@Override
	public String getNomeCliente() {
		return nomeCliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setNomeCliente(java.lang.String)
	 */
	@Override
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getDescricaoContrato()
	 */
	@Override
	public String getDescricaoContrato() {
		return descricaoContrato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setDescricaoContrato(java.lang.String)
	 */
	@Override
	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getItensControleContratos()
	 */
	@Override
	public Set<ItensControleContrato> getItensControleContratos() {
		return itensControleContratos;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setItensControleContratos(java.util.Set)
	 */
	@Override
	public void setItensControleContratos(Set<ItensControleContrato> itensControleContratos) {
		this.itensControleContratos = itensControleContratos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoContrato == null) ? 0 : descricaoContrato.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itensControleContratos == null) ? 0 : itensControleContratos.hashCode());
		result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime * result + ((nrContrato == null) ? 0 : nrContrato.hashCode());
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
		if (!(obj instanceof Contrato))
			return false;
		Contrato other = (Contrato) obj;
		if (descricaoContrato == null) {
			if (other.descricaoContrato != null)
				return false;
		} else if (!descricaoContrato.equals(other.descricaoContrato))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itensControleContratos == null) {
			if (other.itensControleContratos != null)
				return false;
		} else if (!itensControleContratos.equals(other.itensControleContratos))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (nrContrato == null) {
			if (other.nrContrato != null)
				return false;
		} else if (!nrContrato.equals(other.nrContrato))
			return false;
		return true;
	}
}
