package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.IEstado;
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
	
	private Double valorTotalDaProposta;
	
	private String contato;
	
	//S - Sim e N - Não
	private Character statusContratada;
	
	//A - Aprovada e C - Candelada
	private Character status;
	
	private String numeroProposta;
	
//	@OneToOne(targetEntity = Precificacao.class, cascade = CascadeType.ALL)
//	private IPrecificacao precificacao;
	
	@ManyToOne(targetEntity = Cidade.class)
	@JoinColumn(name = "ID_CIDADE")
	private ICidade cidade;
	
	@ManyToOne(targetEntity = Estado.class)
	@JoinColumn(name = "ID_UF")
	private IEstado estado;
	
	private static final double ISS = 5;
	private static final double COFINS = 3;
	private static final double PIS = 0.65;
	private static final double CSLL = 2.88;
	private static final double IR = 4.8;
	private static final double IMPOSTOS = 0.1633;

	private double valorTotalImpostos;

	private double valorTotalPrecificacao;

	private double valorTotalCustosExecucao;

	private double valorTotalCustosDesclocamento;

	private double valorTotalCustosOperacionais;

	private double valorTotalCustosAdministrativos;

	private double valorTotalCustosBdiComissoes;

	private double valorTotalCustosSeguranca;

	private double valorTotalComBdiComissao;

	private double valorTotalSemBdiComissao;


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

//	/**
//	 * @return the precificacao
//	 */
//	@Override
//	public IPrecificacao getPrecificacao() {
//		return precificacao;
//	}
//
//	/**
//	 * @param precificacao the precificacao to set
//	 */
//	@Override
//	public void setPrecificacao(IPrecificacao precificacao) {
//		this.precificacao = precificacao;
//	}

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

	/**
	 * @return the status
	 */
	@Override
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	@Override
	public void setStatus(Character status) {
		this.status = status;
	}


	/**
	 * @return the valorTotalImpostos
	 */
	@Override
	public double getValorTotalImpostos() {
		return valorTotalImpostos;
	}

	/**
	 * @param valorTotalImpostos the valorTotalImpostos to set
	 */
	@Override
	public void setValorTotalImpostos(double valorTotalImpostos) {
		this.valorTotalImpostos = valorTotalImpostos;
	}

	/**
	 * @return the valorTotalPrecificacao
	 */
	@Override
	public double getValorTotalPrecificacao() {
		return valorTotalPrecificacao;
	}

	/**
	 * @param valorTotalPrecificacao the valorTotalPrecificacao to set
	 */
	@Override
	public void setValorTotalPrecificacao(double valorTotalPrecificacao) {
		this.valorTotalPrecificacao = valorTotalPrecificacao;
	}

	/**
	 * @return the valorTotalCustosExecucao
	 */
	@Override
	public double getValorTotalCustosExecucao() {
		return valorTotalCustosExecucao;
	}

	/**
	 * @param valorTotalCustosExecucao the valorTotalCustosExecucao to set
	 */
	@Override
	public void setValorTotalCustosExecucao(double valorTotalCustosExecucao) {
		this.valorTotalCustosExecucao = valorTotalCustosExecucao;
	}

	/**
	 * @return the valorTotalCustosDesclocamento
	 */
	@Override
	public double getValorTotalCustosDesclocamento() {
		return valorTotalCustosDesclocamento;
	}

	/**
	 * @param valorTotalCustosDesclocamento the valorTotalCustosDesclocamento to set
	 */
	@Override
	public void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento) {
		this.valorTotalCustosDesclocamento = valorTotalCustosDesclocamento;
	}

	/**
	 * @return the valorTotalCustosOperacionais
	 */
	@Override
	public double getValorTotalCustosOperacionais() {
		return valorTotalCustosOperacionais;
	}

	/**
	 * @param valorTotalCustosOperacionais the valorTotalCustosOperacionais to set
	 */
	@Override
	public void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais) {
		this.valorTotalCustosOperacionais = valorTotalCustosOperacionais;
	}

	/**
	 * @return the valorTotalCustosAdministrativos
	 */
	@Override
	public double getValorTotalCustosAdministrativos() {
		return valorTotalCustosAdministrativos;
	}

	/**
	 * @param valorTotalCustosAdministrativos the valorTotalCustosAdministrativos to set
	 */
	@Override
	public void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos) {
		this.valorTotalCustosAdministrativos = valorTotalCustosAdministrativos;
	}

	/**
	 * @return the valorTotalCustosBdiComissoes
	 */
	@Override
	public double getValorTotalCustosBdiComissoes() {
		return valorTotalCustosBdiComissoes;
	}

	/**
	 * @param valorTotalCustosBdiComissoes the valorTotalCustosBdiComissoes to set
	 */
	@Override
	public void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes) {
		this.valorTotalCustosBdiComissoes = valorTotalCustosBdiComissoes;
	}

	/**
	 * @return the valorTotalCustosSeguranca
	 */
	@Override
	public double getValorTotalCustosSeguranca() {
		return valorTotalCustosSeguranca;
	}

	/**
	 * @param valorTotalCustosSeguranca the valorTotalCustosSeguranca to set
	 */
	@Override
	public void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca) {
		this.valorTotalCustosSeguranca = valorTotalCustosSeguranca;
	}

	/**
	 * @return the valorTotalComBdiComissao
	 */
	@Override
	public double getValorTotalComBdiComissao() {
		return valorTotalComBdiComissao;
	}

	/**
	 * @param valorTotalComBdiComissao the valorTotalComBdiComissao to set
	 */
	@Override
	public void setValorTotalComBdiComissao(double valorTotalComBdiComissao) {
		this.valorTotalComBdiComissao = valorTotalComBdiComissao;
	}

	/**
	 * @return the valorTotalSemBdiComissao
	 */
	@Override
	public double getValorTotalSemBdiComissao() {
		return valorTotalSemBdiComissao;
	}

	/**
	 * @param valorTotalSemBdiComissao the valorTotalSemBdiComissao to set
	 */
	@Override
	public void setValorTotalSemBdiComissao(double valorTotalSemBdiComissao) {
		this.valorTotalSemBdiComissao = valorTotalSemBdiComissao;
	}

	/**
	 * @return the iss
	 */
	@Override
	public double getIss() {
		return ISS;
	}

	/**
	 * @return the cofins
	 */
	@Override
	public double getCofins() {
		return COFINS;
	}

	/**
	 * @return the pis
	 */
	@Override
	public double getPis() {
		return PIS;
	}

	/**
	 * @return the csll
	 */
	@Override
	public double getCsll() {
		return CSLL;
	}

	/**
	 * @return the ir
	 */
	@Override
	public double getIr() {
		return IR;
	}

	/**
	 * @return the impostos
	 */
	@Override
	public double getImpostos() {
		return IMPOSTOS;
	}

	/**
	 * @return the valorTotalDaProposta
	 */
	@Override
	public Double getValorTotalDaProposta() {
		return valorTotalDaProposta;
	}

	/**
	 * @param valorTotalDaProposta the valorTotalDaProposta to set
	 */
	@Override
	public void setValorTotalDaProposta(Double valorTotalDaProposta) {
		this.valorTotalDaProposta = valorTotalDaProposta;
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
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusContratada == null) ? 0 : statusContratada.hashCode());
		result = prime * result + ((tipoProposta == null) ? 0 : tipoProposta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotalComBdiComissao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosAdministrativos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosBdiComissoes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosDesclocamento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosExecucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosOperacionais);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosSeguranca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valorTotalDaProposta == null) ? 0 : valorTotalDaProposta.hashCode());
		temp = Double.doubleToLongBits(valorTotalImpostos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalPrecificacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalSemBdiComissao);
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		if (Double.doubleToLongBits(valorTotalComBdiComissao) != Double
				.doubleToLongBits(other.valorTotalComBdiComissao))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosAdministrativos) != Double
				.doubleToLongBits(other.valorTotalCustosAdministrativos))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosBdiComissoes) != Double
				.doubleToLongBits(other.valorTotalCustosBdiComissoes))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosDesclocamento) != Double
				.doubleToLongBits(other.valorTotalCustosDesclocamento))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosExecucao) != Double
				.doubleToLongBits(other.valorTotalCustosExecucao))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosOperacionais) != Double
				.doubleToLongBits(other.valorTotalCustosOperacionais))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosSeguranca) != Double
				.doubleToLongBits(other.valorTotalCustosSeguranca))
			return false;
		if (valorTotalDaProposta == null) {
			if (other.valorTotalDaProposta != null)
				return false;
		} else if (!valorTotalDaProposta.equals(other.valorTotalDaProposta))
			return false;
		if (Double.doubleToLongBits(valorTotalImpostos) != Double.doubleToLongBits(other.valorTotalImpostos))
			return false;
		if (Double.doubleToLongBits(valorTotalPrecificacao) != Double.doubleToLongBits(other.valorTotalPrecificacao))
			return false;
		if (Double.doubleToLongBits(valorTotalSemBdiComissao) != Double
				.doubleToLongBits(other.valorTotalSemBdiComissao))
			return false;
		return true;
	}
}
