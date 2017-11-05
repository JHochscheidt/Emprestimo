package br.com.cooperalfa.emprestimo.dao;

import org.junit.Test;

import br.com.cooperalfa.emprestimo.entidade.Setor;

public class SetorDAOTest {
	@Test
	public void salvar() {
		Setor setor = new Setor();
		setor.setNome("administrativo");
		setor.setDescricao("Setor administrativo");
		
		
		SetorDAO setorDAO = new SetorDAO();
		setorDAO.salvar(setor);
	}
}
