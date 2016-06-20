package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.IPrecificacao;
import br.com.sysagrega.model.IPropostaBase;

@MappedSuperclass
public abstract class PropostaBase implements IPropostaBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipoProposta;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataContratacao;
	
	private String nomeProjeto;
	
	private String cliente;
	
	private String objeto;
	
	private Double valor;
	
	private String contato;
	
	private Character statusContratada;
	
	private String numeroProposta;
	
	@OneToOne(targetEntity = Precificacao.class, cascade = CascadeType.ALL)
	private IPrecificacao precificacao;
	
	@ManyToOne(targetEntity = Cidade.class)
	@JoinColumn(name = "ID_CIDADE")
	private ICidade cidade;
	
	@ManyToOne(targetEntity = Estado.class)
	@JoinColumn(name = "ID_UF")
	private IEstado estado;

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getTipoProposta()
	 */
	@Override
	public String getTipoProposta() {
		return tipoProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setTipoProposta(java.lang.String)
	 */
	@Override
	public void setTipoProposta(String tipoProposta) {
		this.tipoProposta = tipoProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getDataInclusao()
	 */
	@Override
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setDataInclusao(java.util.Date)
	 */
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getDataContratacao()
	 */
	@Override
	public Date getDataContratacao() {
		return dataContratacao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setDataContratacao(java.util.Date)
	 */
	@Override
	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getCliente()
	 */
	@Override
	public String getCliente() {
		return cliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setCliente(java.lang.String)
	 */
	@Override
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getObjeto()
	 */
	@Override
	public String getObjeto() {
		return objeto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setObjeto(java.lang.String)
	 */
	@Override
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getValor()
	 */
	@Override
	public Double getValor() {
		return valor;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setValor(java.lang.Double)
	 */
	@Override
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getContato()
	 */
	@Override
	public String getContato() {
		return contato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setContato(java.lang.String)
	 */
	@Override
	public void setContato(String contato) {
		this.contato = contato;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getStatusContratada()
	 */
	@Override
	public Character getStatusContratada() {
		return statusContratada;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setStatusContratada(java.lang.Character)
	 */
	@Override
	public void setStatusContratada(Character statusContratada) {
		this.statusContratada = statusContratada;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getNumeroProposta()
	 */
	@Override
	public String getNumeroProposta() {
		return numeroProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setNumeroProposta(java.lang.String)
	 */
	@Override
	public void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}

	/**
	 * @return the precificacao
	 */
	@Override
	public IPrecificacao getPrecificacao() {
		return precificacao;
	}

	/**
	 * @param precificacao the precificacao to set
	 */
	@Override
	public void setPrecificacao(IPrecificacao precificacao) {
		this.precificacao = precificacao;
	}

	/**
	 * @return the cidade
	 */
	@Override
	public ICidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	@Override
	public void setCidade(ICidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	@Override
	public IEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	@Override
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}

	/**
	 * @return the nomeProjeto
	 */
	@Override
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	/**
	 * @param nomeProjeto the nomeProjeto to set
	 */
	@Override
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((dataContratacao == null) ? 0 : dataContratacao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeProjeto == null) ? 0 : nomeProjeto.hashCode());
		result = prime * result + ((numeroProposta == null) ? 0 : numeroProposta.hashCode());
		result = prime * result + ((objeto == null) ? 0 : objeto.hashCode());
		result = prime * result + ((precificacao == null) ? 0 : precificacao.hashCode());
		result = prime * result + ((statusContratada == null) ? 0 : statusContratada.hashCode());
		result = prime * result + ((tipoProposta == null) ? 0 : tipoProposta.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		if (!(obj instanceof PropostaBase))
			return false;
		PropostaBase other = (PropostaBase) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (dataContratacao == null) {
			if (other.dataContratacao != null)
				return false;
		} else if (!dataContratacao.equals(other.dataContratacao))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
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
		if (nomeProjeto == null) {
			if (other.nomeProjeto != null)
				return false;
		} else if (!nomeProjeto.equals(other.nomeProjeto))
			return false;
		if (numeroProposta == null) {
			if (other.numeroProposta != null)
				return false;
		} else if (!numeroProposta.equals(other.numeroProposta))
			return false;
		if (objeto == null) {
			if (other.objeto != null)
				return false;
		} else if (!objeto.equals(other.objeto))
			return false;
		if (precificacao == null) {
			if (other.precificacao != null)
				return false;
		} else if (!precificacao.equals(other.precificacao))
			return false;
		if (statusContratada == null) {
			if (other.statusContratada != null)
				return false;
		} else if (!statusContratada.equals(other.statusContratada))
			return false;
		if (tipoProposta == null) {
			if (other.tipoProposta != null)
				return false;
		} else if (!tipoProposta.equals(other.tipoProposta))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
