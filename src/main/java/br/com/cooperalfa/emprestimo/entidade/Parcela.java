package br.com.cooperalfa.emprestimo.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Parcela extends GenericDomain {

	@Column(nullable = false)
	private Integer numeroParcela;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valorParcela;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valorPago;

	@Column(nullable = false)
	private Long cod_emprestimo;

	
	
	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Long getCod_emprestimo() {
		return cod_emprestimo;
	}

	public void setCod_emprestimo(Long cod_emprestimo) {
		this.cod_emprestimo = cod_emprestimo;
	}

	
}
