package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.cooperalfa.emprestimo.dao.CargoDAO;
import br.com.cooperalfa.emprestimo.dao.SetorDAO;
import br.com.cooperalfa.emprestimo.entidade.Cargo;
import br.com.cooperalfa.emprestimo.entidade.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CargoBean implements Serializable {
	private Cargo cargo;
	private List<Cargo> cargos;
	private List<Setor> setores;

	public void novo() {
		try {
			this.cargo = new Cargo();
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar novo cargo!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar cargo!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");

			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.excluir(cargo);

			cargos = cargoDAO.listar();

			Messages.addGlobalInfo("Cargo removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao remover cargo!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargo.setNome(cargo.getNome().toUpperCase());
			cargo.getSetor().setNome(cargo.getSetor().getNome().toUpperCase());

			cargoDAO.merge(cargo);

			novo();
			cargos = cargoDAO.listar();

			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

			Messages.addGlobalInfo("Cargo salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar novo cargo!");
			e.printStackTrace();
		}
	}

	@PostConstruct // chama quando a tela é criada
	public void listar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os cargos");
			e.printStackTrace();
		}
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

}
