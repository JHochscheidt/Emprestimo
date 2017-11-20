package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.PersistenceException;

import org.omnifaces.util.Messages;

import br.com.cooperalfa.emprestimo.dao.CargoDAO;
import br.com.cooperalfa.emprestimo.dao.FuncionarioDAO;
import br.com.cooperalfa.emprestimo.dao.SetorDAO;
import br.com.cooperalfa.emprestimo.entidade.Cargo;
import br.com.cooperalfa.emprestimo.entidade.Funcionario;
import br.com.cooperalfa.emprestimo.entidade.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;

	private Setor setor;
	private List<Setor> setores;
	private List<Cargo> cargos;

	public void novo() {
		try {
			funcionario = new Funcionario();

			SetorDAO setorDAO = new SetorDAO();
			setor = new Setor();
			setores = setorDAO.listarOrdenado("nome");

			cargos = new ArrayList<Cargo>();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao criar funcionário!!");
			e.printStackTrace();
		}
	}

	// popular cargos com base no setor selecionado
	public void popular() {
		try {
			if (setor != null) {
				CargoDAO cargoDAO = new CargoDAO();
				cargos = cargoDAO.buscarPorSetor(setor.getCodigo());

			} else {
				cargos = new ArrayList<Cargo>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cargos");
			e.printStackTrace();
		}

	}

	public void salvar(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionario.setSetor(setor);
			funcionario.setNome(funcionario.getNome().toUpperCase());
			funcionarioDAO.merge(funcionario);
			funcionarios = funcionarioDAO.listar();
			novo();
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listarOrdenado("nome");
			
			cargos = new ArrayList<Cargo>();
			Messages.addGlobalInfo("Funcionário salvo com sucesso");
		}catch (PersistenceException e) {
			Messages.addGlobalError("CPF já existe. Não foi possível salvar funcionário!");
			e.printStackTrace();
		}catch(RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar funcionário!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
			setor = setorDAO.buscar(funcionario.getCargo().getSetor().getCodigo());
			funcionario.setSetor(setor);
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar();

		}catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar funcionário!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao remover funcionário!");
			e.printStackTrace();
		}

	}

	@PostConstruct // chama quando a tela é criada
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários");
			e.printStackTrace();
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
