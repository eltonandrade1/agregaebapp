package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;


@Entity
@Table(name  = "TB_PROPOSTA_HISTORICO")
public class PropostaHistorico extends PropostaBase implements IPropostaHistorico{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Proposta.class)
	@JoinColumn(name = "propostaID")
	private IProposta propostaId;
	
	@Temporal(TemporalType.DATE)
	private Date dataRevisao;
	
	private String numeroRevisao;

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaHistorico#getPropostaId()
	 */
	@Override
	public IProposta getPropostaId() {
		return propostaId;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaHistorico#setPropostaId(br.com.sysagrega.model.imp.Proposta)
	 */
	@Override
	public void setPropostaId(IProposta propostaId) {
		this.propostaId = propostaId;
	}

	/**
	 * @return the dataRevisao
	 */
	@Override
	public Date getDataRevisao() {
		return dataRevisao;
	}

	/**
	 * @param dataRevisao the dataRevisao to set
	 */
	@Override
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/**
	 * @return the numeroRevisao
	 */
	@Override
	public String getNumeroRevisao() {
		return numeroRevisao;
	}

	/**
	 * @param numeroRevisao the numeroRevisao to set
	 */
	@Override
	public void setNumeroRevisao(String numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataRevisao == null) ? 0 : dataRevisao.hashCode());
		result = prime * result + ((numeroRevisao == null) ? 0 : numeroRevisao.hashCode());
		result = prime * result + ((propostaId == null) ? 0 : propostaId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PropostaHistorico))
			return false;
		PropostaHistorico other = (PropostaHistorico) obj;
		if (dataRevisao == null) {
			if (other.dataRevisao != null)
				return false;
		} else if (!dataRevisao.equals(other.dataRevisao))
			return false;
		if (numeroRevisao == null) {
			if (other.numeroRevisao != null)
				return false;
		} else if (!numeroRevisao.equals(other.numeroRevisao))
			return false;
		if (propostaId == null) {
			if (other.propostaId != null)
				return false;
		} else if (!propostaId.equals(other.propostaId))
			return false;
		return true;
	}
}
