package br.com.cooperalfa.emprestimo.entidade;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Parcela extends GenericDomain{
	
	@Column(nullable = false)
	private Integer numeroParcela;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataVencimento;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valor;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Emprestimo emprestimo;
	
}
