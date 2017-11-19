package br.com.cooperalfa.emprestimo.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Emprestimo extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valor;

	@Column(nullable = false)
	private int quantidadeParcelas;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date primeiraParcela;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataOperacao;

	@Column(nullable = false)
	private String status;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Date getPrimeiraParcela() {
		return primeiraParcela;
	}

	public void setPrimeiraParcela(Date primeiraParcela) {
		this.primeiraParcela = primeiraParcela;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
