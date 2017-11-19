package br.com.cooperalfa.emprestimo.dao;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.com.cooperalfa.emprestimo.entidade.Cargo;
import br.com.cooperalfa.emprestimo.entidade.Setor;

public class CargoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(4L);

		if (setor == null)
			System.out.println("Setor não existe! Impossível adicionar Cargo!!!");
		else {
			CargoDAO cargoDao = new CargoDAO();
			Cargo cargo = new Cargo();
			cargo.setNome("Programador");
			cargo.setSetor(setor);

			cargoDao.salvar(cargo);

		}
	}
	
	@Test
	@Ignore
	public void listar() {
		CargoDAO cargoDAO = new CargoDAO();
		List<Cargo> resultado = cargoDAO.listar();

		for (Cargo cargo : resultado) {
			System.out.println(cargo.getCodigo() + " - " 
					+ cargo.getNome() + " - " 
					+ cargo.getSetor().getNome());
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 9L;
		CargoDAO cargoDAO = new CargoDAO();
		Cargo cargo = cargoDAO.buscar(codigo);

		if (cargo == null)
			System.out.println("Cargo não existe!!!");
		else {
			System.out.println(cargo.getCodigo() + " - " 
					+ cargo.getNome() + " - " 
					+ cargo.getSetor().getNome());
		}
	}
	
	@Test
//	@Ignore
	public void buscarPorSetor() {
		Long codigoSetor = 4L;
		CargoDAO cargoDAO = new CargoDAO();
		List<Cargo> cargos = cargoDAO.buscarPorSetor(codigoSetor);

		if (cargos == null)
			System.out.println("Cargo não existe!!!");
		else {
			for(Cargo cargo : cargos) {
				System.out.println(cargo.getCodigo() + " - " 
					+ cargo.getNome() + " - " 
					+ cargo.getSetor().getNome());
			}
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 10L;
		CargoDAO cargoDAO = new CargoDAO();
		Cargo cargo = cargoDAO.buscar(codigo);

		if (cargo == null)
			System.out.println("Cargo não existe! Sem exclusão!!!");
		else {
			cargoDAO.excluir(cargo);
			System.out.println("Cargo excluído : ");
			System.out.println(cargo.getCodigo() + " - " 
					+ cargo.getNome() + " - "
					+ cargo.getSetor().getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 11L;
		CargoDAO cargoDAO = new CargoDAO();
		Cargo cargo = cargoDAO.buscar(codigo);

		if (cargo == null)
			System.out.println("Cargo não existe! Impossível alterá-lo!!!");
		else {
			cargo.setNome("Programador Junior");
			cargoDAO.editar(cargo);
		}

	}
}
