package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysagrega.model.IDadosBancarios;
import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.IProfissional;

@Entity
@Table(name = "TB_PROFISSIONAL")
public class Profissional implements IProfissional{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//TODO adicionar anotações de tamanho para campos e também nomes para as tabelas
	//TODO adicionar as anotações joinnColunm nas entidades

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String profissao;
	
	private String atuacaoEspecializacao;
	
	private Character contratoTemporario;
	
	private String cpf;
	
	private String rg;
	
	private String pisPasep;
	
	private String carteiraTrabalho;
	
	private String numeroConsClasse;
	
	private String tamanhoCamisa;
	
	private String tamanhoCalca;
	
	private Integer numeroSapato;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	@OneToOne(targetEntity = Endereco.class, cascade = CascadeType.ALL)
	private IEndereco endereco;
	
	@OneToOne(targetEntity = DadosBancarios.class, cascade = CascadeType.ALL)
	private IDadosBancarios dadosBancarios;
	
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
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getProfissao() {
		return profissao;
	}
	@Override
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	@Override
	public String getAtuacaoEspecializacao() {
		return atuacaoEspecializacao;
	}
	@Override
	public void setAtuacaoEspecializacao(String atuacaoEspecializacao) {
		this.atuacaoEspecializacao = atuacaoEspecializacao;
	}
	@Override
	public Character getContratoTemporario() {
		return contratoTemporario;
	}
	@Override
	public void setContratoTemporario(Character contratoTemporario) {
		this.contratoTemporario = contratoTemporario;
	}
	@Override
	public String getCpf() {
		return cpf;
	}
	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String getRg() {
		return rg;
	}
	@Override
	public void setRg(String rg) {
		this.rg = rg;
	}
	@Override
	public String getPisPasep() {
		return pisPasep;
	}
	@Override
	public void setPisPasep(String pisPasep) {
		this.pisPasep = pisPasep;
	}
	@Override
	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}
	@Override
	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}
	@Override
	public String getNumeroConsClasse() {
		return numeroConsClasse;
	}
	@Override
	public void setNumeroConsClasse(String numeroConsClasse) {
		this.numeroConsClasse = numeroConsClasse;
	}
	@Override
	public String getTamanhoCamisa() {
		return tamanhoCamisa;
	}
	@Override
	public void setTamanhoCamisa(String tamanhoCamisa) {
		this.tamanhoCamisa = tamanhoCamisa;
	}
	@Override
	public String getTamanhoCalca() {
		return tamanhoCalca;
	}
	@Override
	public void setTamanhoCalca(String tamanhoCalca) {
		this.tamanhoCalca = tamanhoCalca;
	}
	@Override
	public Integer getNumeroSapato() {
		return numeroSapato;
	}
	@Override
	public void setNumeroSapato(Integer numeroSapato) {
		this.numeroSapato = numeroSapato;
	}
	@Override
	public Date getDataNascimento() {
		return dataNascimento;
	}
	@Override
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String getTelefone() {
		return telefone;
	}
	@Override
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String getCelular() {
		return celular;
	}
	@Override
	public void setCelular(String celular) {
		this.celular = celular;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public IEndereco getEndereco() {
		return endereco;
	}
	@Override
	public void setEndereco(IEndereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public IDadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}
	@Override
	public void setDadosBancarios(IDadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atuacaoEspecializacao == null) ? 0 : atuacaoEspecializacao.hashCode());
		result = prime * result + ((carteiraTrabalho == null) ? 0 : carteiraTrabalho.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((contratoTemporario == null) ? 0 : contratoTemporario.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dadosBancarios == null) ? 0 : dadosBancarios.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroConsClasse == null) ? 0 : numeroConsClasse.hashCode());
		result = prime * result + ((numeroSapato == null) ? 0 : numeroSapato.hashCode());
		result = prime * result + ((pisPasep == null) ? 0 : pisPasep.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((tamanhoCalca == null) ? 0 : tamanhoCalca.hashCode());
		result = prime * result + ((tamanhoCamisa == null) ? 0 : tamanhoCamisa.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		if (!(obj instanceof Profissional))
			return false;
		Profissional other = (Profissional) obj;
		if (atuacaoEspecializacao == null) {
			if (other.atuacaoEspecializacao != null)
				return false;
		} else if (!atuacaoEspecializacao.equals(other.atuacaoEspecializacao))
			return false;
		if (carteiraTrabalho == null) {
			if (other.carteiraTrabalho != null)
				return false;
		} else if (!carteiraTrabalho.equals(other.carteiraTrabalho))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (contratoTemporario == null) {
			if (other.contratoTemporario != null)
				return false;
		} else if (!contratoTemporario.equals(other.contratoTemporario))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dadosBancarios == null) {
			if (other.dadosBancarios != null)
				return false;
		} else if (!dadosBancarios.equals(other.dadosBancarios))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
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
		if (numeroConsClasse == null) {
			if (other.numeroConsClasse != null)
				return false;
		} else if (!numeroConsClasse.equals(other.numeroConsClasse))
			return false;
		if (numeroSapato == null) {
			if (other.numeroSapato != null)
				return false;
		} else if (!numeroSapato.equals(other.numeroSapato))
			return false;
		if (pisPasep == null) {
			if (other.pisPasep != null)
				return false;
		} else if (!pisPasep.equals(other.pisPasep))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (tamanhoCalca == null) {
			if (other.tamanhoCalca != null)
				return false;
		} else if (!tamanhoCalca.equals(other.tamanhoCalca))
			return false;
		if (tamanhoCamisa == null) {
			if (other.tamanhoCamisa != null)
				return false;
		} else if (!tamanhoCamisa.equals(other.tamanhoCamisa))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}


