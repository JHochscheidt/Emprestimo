package br.com.cooperalfa.emprestimo.dao;

import java.util.List;


import org.junit.Ignore;
import org.junit.Test;

import br.com.cooperalfa.emprestimo.entidade.Setor;

public class SetorDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Setor setor = new Setor();
		SetorDAO setorDAO = new SetorDAO();
		
		setor.setNome("administrativo");
		setorDAO.salvar(setor);
		
		setor.setNome("financeiro");
		setorDAO.salvar(setor);
		
		setor.setNome("recursos humanos");
		setorDAO.salvar(setor);
		
		setor.setNome("juridico");
		setorDAO.salvar(setor);
		
		setor.setNome("tecnologia");
		setorDAO.salvar(setor);
		
		setor.setNome("Externo");
		setorDAO.salvar(setor);
		
		setor.setNome("Comercial");
		setorDAO.salvar(setor);
		
		setor.setNome("Operacional");
		setorDAO.salvar(setor);
	
	}
	
	@Test
	@Ignore
	public void listar() {
		SetorDAO setorDAO = new SetorDAO();
		List<Setor> resultado = setorDAO.listar();
		
		System.out.println("quantidade de registros encontrados " + resultado.size());
		
		for(Setor setor : resultado) {
			System.out.println(setor.getNome());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 10L;
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(codigo);
		
		if(setor == null ) System.out.println("Nenhum registro encontrado");
		else System.out.println(setor.getNome());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 9L;
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(codigo);
		if(setor == null) System.out.println("Setor não existe");
		else {
			setorDAO.excluir(setor);
			System.out.println("Setor removido: " + setor.getCodigo());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 8L;
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(codigo);
		
		if(setor == null) System.out.println("Setor não existe");
		else {
			setor.setNome("Teste");
			setorDAO.editar(setor);
		}
		
	}
	
	@Test
//	@Ignore
	public void merge() {
//		Setor setor = new Setor();
//		SetorDAO setorDAO = new SetorDAO();
//		setor.setNome("administrativo teste");
//		setorDAO.merge(setor);
		
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(9L);
		setor.setNome("teste");
		setorDAO.merge(setor);
		
		
	}
}
