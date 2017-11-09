package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.cooperalfa.emprestimo.dao.SetorDAO;
import br.com.cooperalfa.emprestimo.entidade.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SetorBean implements Serializable {
	private Setor setor;
	private List<Setor> setores;

	@PostConstruct
	public void listar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os setores");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		this.setor = new Setor();
	}

	public void salvar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			setorDAO.merge(setor);
			
			novo();
			setores = setorDAO.listar();
			Messages.addGlobalInfo("Setor salvo com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao salvar setor!");
			e.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionadoExclusao");
			
			SetorDAO setorDAO = new SetorDAO();
			setorDAO.excluir(setor);
			
			setores = setorDAO.listar();
			
			Messages.addGlobalInfo("Setor removido com sucesso!");
		}catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao remover setor!");
			e.printStackTrace();
		}
		
		
	}
	
	public void editar(ActionEvent evento) {
		setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionado");
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getSetores() {
		return setores;
	}
	

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

}
