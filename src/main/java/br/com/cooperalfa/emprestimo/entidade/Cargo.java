package br.com.cooperalfa.emprestimo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class Cargo extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 250, nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Setor setor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
