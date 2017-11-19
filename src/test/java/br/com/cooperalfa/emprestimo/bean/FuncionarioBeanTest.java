package br.com.cooperalfa.emprestimo.bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cooperalfa.emprestimo.dao.CargoDAO;
import br.com.cooperalfa.emprestimo.dao.FuncionarioDAO;
import br.com.cooperalfa.emprestimo.dao.SetorDAO;
import br.com.cooperalfa.emprestimo.entidade.Cargo;
import br.com.cooperalfa.emprestimo.entidade.Funcionario;
import br.com.cooperalfa.emprestimo.entidade.Setor;

public class FuncionarioBeanTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		SetorDAO setorDAO = new SetorDAO();
		Setor setor = setorDAO.buscar(4L);

		CargoDAO cargoDAO = new CargoDAO();
		List<Cargo> cargos = cargoDAO.buscarPorSetor(setor.getCodigo());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Jackson");
		funcionario.setSetor(setor);
		funcionario.setCargo(cargos.get(0));
		funcionario.setCpf("078.136.999-12");
		funcionario.setObservacao("adsoiudoiasudoasd");
		Date data = new Date();
		data.getTime();		
		funcionario.setDataAdmissao(data);

		funcionarioDAO.salvar(funcionario);

	}

	

}
