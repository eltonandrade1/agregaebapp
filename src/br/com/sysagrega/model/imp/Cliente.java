package br.com.sysagrega.model.imp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IEndereco;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements ICliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	private String seguimento;

	private String cnae;

	private String tamanho;

	private String tipoCliente;

	private String situacao;

	@OneToOne(targetEntity = Endereco.class, cascade = CascadeType.ALL)
	private IEndereco endereco;

	private String telefone;

	private String celular;

	private String email;
	
	private String fax;
	
	private String cnpj;
	
	private String ie;
	
	private String contato;
	
	private String funcaoDep;
	
	private Character pendenciaAmbiental;
	
	private String processo;
	
	private String pendencia;
	
	
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
	 * @see br.com.sysagrega.model.imp.ICliente#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getNome()
	 */
	@Override
	public String getNome() {
		return nome;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setNome(java.lang.String)
	 */
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getCnae()
	 */
	@Override
	public String getCnae() {
		return cnae;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setCnae(java.lang.String)
	 */
	@Override
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}


	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getEndereco()
	 */
	@Override
	public IEndereco getEndereco() {
		return endereco;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setEndereco(br.com.sysagrega.model.IEndereco)
	 */
	@Override
	public void setEndereco(IEndereco endereco) {
		this.endereco = endereco;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getTelefone()
	 */
	@Override
	public String getTelefone() {
		return telefone;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setTelefone(java.lang.String)
	 */
	@Override
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getCelular()
	 */
	@Override
	public String getCelular() {
		return celular;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setCelular(java.lang.String)
	 */
	@Override
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getFax()
	 */
	@Override
	public String getFax() {
		return fax;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setFax(java.lang.String)
	 */
	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getCnpj()
	 */
	@Override
	public String getCnpj() {
		return cnpj;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setCnpj(java.lang.String)
	 */
	@Override
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getIe()
	 */
	@Override
	public String getIe() {
		return ie;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setIe(java.lang.String)
	 */
	@Override
	public void setIe(String ie) {
		this.ie = ie;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getContato()
	 */
	@Override
	public String getContato() {
		return contato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setContato(java.lang.String)
	 */
	@Override
	public void setContato(String contato) {
		this.contato = contato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getFuncaoDep()
	 */
	@Override
	public String getFuncaoDep() {
		return funcaoDep;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setFuncaoDep(java.lang.String)
	 */
	@Override
	public void setFuncaoDep(String funcaoDep) {
		this.funcaoDep = funcaoDep;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public Character getPendenciaAmbiental() {
		return pendenciaAmbiental;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#setPendenciaAmbiental(java.lang.Character)
	 */
	@Override
	public void setPendenciaAmbiental(Character pendenciaAmbiental) {
		this.pendenciaAmbiental = pendenciaAmbiental;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getSeguimento() {
		return seguimento;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setSeguimento(String seguimento) {
		this.seguimento = seguimento;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getTamanho() {
		return tamanho;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getTipoCliente() {
		return tipoCliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getSituacao() {
		return situacao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getProcesso() {
		return processo;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setProcesso(String processo) {
		this.processo = processo;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public String getPendencia() {
		return pendencia;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICliente#getPendenciaAmbiental()
	 */
	@Override
	public void setPendencia(String pendencia) {
		this.pendencia = pendencia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cnae == null) ? 0 : cnae.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((funcaoDep == null) ? 0 : funcaoDep.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ie == null) ? 0 : ie.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pendencia == null) ? 0 : pendencia.hashCode());
		result = prime * result + ((pendenciaAmbiental == null) ? 0 : pendenciaAmbiental.hashCode());
		result = prime * result + ((processo == null) ? 0 : processo.hashCode());
		result = prime * result + ((seguimento == null) ? 0 : seguimento.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoCliente == null) ? 0 : tipoCliente.hashCode());
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
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cnae == null) {
			if (other.cnae != null)
				return false;
		} else if (!cnae.equals(other.cnae))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (funcaoDep == null) {
			if (other.funcaoDep != null)
				return false;
		} else if (!funcaoDep.equals(other.funcaoDep))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ie == null) {
			if (other.ie != null)
				return false;
		} else if (!ie.equals(other.ie))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pendencia == null) {
			if (other.pendencia != null)
				return false;
		} else if (!pendencia.equals(other.pendencia))
			return false;
		if (pendenciaAmbiental == null) {
			if (other.pendenciaAmbiental != null)
				return false;
		} else if (!pendenciaAmbiental.equals(other.pendenciaAmbiental))
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
			return false;
		if (seguimento == null) {
			if (other.seguimento != null)
				return false;
		} else if (!seguimento.equals(other.seguimento))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoCliente == null) {
			if (other.tipoCliente != null)
				return false;
		} else if (!tipoCliente.equals(other.tipoCliente))
			return false;
		return true;
	}
}
