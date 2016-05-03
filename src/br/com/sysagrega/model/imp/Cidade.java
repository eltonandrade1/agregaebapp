package br.com.sysagrega.model.imp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.IEstado;

@Entity
@Table(name  = "TB_CIDADE")
public class Cidade implements ICidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne(targetEntity = Estado.class, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_UF")
	private IEstado estado;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#getNome()
	 */
	@Override
	public String getNome() {
		return nome;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#setNome(java.lang.String)
	 */
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#getEstado()
	 */
	@Override
	public IEstado getEstado() {
		return estado;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICidade#setEstado(br.com.sysagrega.model.imp.Estado)
	 */
	@Override
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (!(obj instanceof Cidade))
			return false;
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
