package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.IParcela;

@Entity
@Table(name  = "TB_PARCELA")
public class Parcela implements IParcela {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nfs;
	
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	
	@Temporal(TemporalType.DATE)
	private Date dataPgto;
	
	private double valor;

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#getNfs()
	 */
	@Override
	public String getNfs() {
		return nfs;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#setNfs(java.lang.String)
	 */
	@Override
	public void setNfs(String nfs) {
		this.nfs = nfs;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#getDataEmissao()
	 */
	@Override
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#setDataEmissao(java.util.Date)
	 */
	@Override
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#getDataPgto()
	 */
	@Override
	public Date getDataPgto() {
		return dataPgto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#setDataPgto(java.util.Date)
	 */
	@Override
	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#getValor()
	 */
	@Override
	public double getValor() {
		return valor;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IParcela#setValor(double)
	 */
	@Override
	public void setValor(double valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEmissao == null) ? 0 : dataEmissao.hashCode());
		result = prime * result + ((dataPgto == null) ? 0 : dataPgto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nfs == null) ? 0 : nfs.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
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
		if (!(obj instanceof Parcela))
			return false;
		Parcela other = (Parcela) obj;
		if (dataEmissao == null) {
			if (other.dataEmissao != null)
				return false;
		} else if (!dataEmissao.equals(other.dataEmissao))
			return false;
		if (dataPgto == null) {
			if (other.dataPgto != null)
				return false;
		} else if (!dataPgto.equals(other.dataPgto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nfs == null) {
			if (other.nfs != null)
				return false;
		} else if (!nfs.equals(other.nfs))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
}
