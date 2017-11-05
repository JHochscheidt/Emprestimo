package br.com.cooperalfa.emprestimo.entidade;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Emprestimo extends GenericDomain{
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private int quantidadeParcelas;
	
	@Column(nullable = false)
	private Calendar primeiraParcela;
	
	@Column(nullable = false)
	private Calendar dataOperacao;
	
	
	
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

	public Calendar getPrimeiraParcela() {
		return primeiraParcela;
	}

	public void setPrimeiraParcela(Calendar primeiraParcela) {
		this.primeiraParcela = primeiraParcela;
	}

	public Calendar getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Calendar dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	
	
	
	
}
