package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.cooperalfa.emprestimo.dao.EmprestimoDAO;
import br.com.cooperalfa.emprestimo.dao.FuncionarioDAO;
import br.com.cooperalfa.emprestimo.dao.ParcelaDAO;
import br.com.cooperalfa.emprestimo.entidade.Emprestimo;
import br.com.cooperalfa.emprestimo.entidade.Funcionario;
import br.com.cooperalfa.emprestimo.entidade.Parcela;
import br.com.cooperalfa.emprestimo.entidade.Status;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmprestimoBean implements Serializable {
	private Emprestimo emprestimo;
	private List<Emprestimo> emprestimos;
	private List<Funcionario> funcionarios;
	private List<Integer> quantidadeDeParcelas = null;

	public void popular() {
		if (quantidadeDeParcelas == null) {
			quantidadeDeParcelas = new ArrayList<Integer>();
			quantidadeDeParcelas.add(3);
			quantidadeDeParcelas.add(6);
			quantidadeDeParcelas.add(12);
			quantidadeDeParcelas.add(24);
		}
	}

	public void novo() {
		try {
			popular();
			emprestimo = new Emprestimo();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			Date dataAtual = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dataAtualString = formatter.format(dataAtual);
			emprestimo.setDataOperacao(formatter.parse(dataAtualString));

		} catch (ParseException e) {
			Messages.addGlobalError("Erro ao criar a data atual");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
			emprestimos = emprestimoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar exibir empréstimos");
			e.printStackTrace();
		}
	}

	public void salvar() {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		try {
			// retorna um emprestimo somente se o funcionario ja possui um emprestimo ativo
			// se nao houver emprestimos ativos para o funcionario RETORNA UM NoResultException
			Emprestimo emprestimoFunc = emprestimoDAO.buscarFuncionario(emprestimo.getFuncionario());

			// se funcionario nao possui emprestimos ativos
			if (emprestimoFunc == null) {
				// --> PODE FAZER NOVO EMPRESTIMO
				emprestimo.setStatus(Status.ATIVO);
	
				emprestimoDAO.merge(emprestimo);
				
				emprestimoFunc = emprestimoDAO.buscarFuncionario(emprestimo.getFuncionario());
//				// se teve sucesso ao gravar emprestimo
//				// gera as parcelas
				ParcelaDAO parcelaDAO = new ParcelaDAO();
				int qtParcelas = emprestimoFunc.getQuantidadeParcelas();
				for (int i = 0; i < qtParcelas; i++) {
					System.out.println("AQUIIIII\n\n");
					Parcela parcela = new Parcela();
					parcela.setNumeroParcela(i + 1);
					parcela.setValorParcela(emprestimoFunc.getValor());
					BigDecimal valorPago = new BigDecimal(0);
					parcela.setValorPago(valorPago);
					parcela.setDataVencimento(emprestimoFunc.getPrimeiraParcela());
					parcela.setEmprestimo(emprestimoFunc);
					parcelaDAO.merge(parcela);
					
				}
				emprestimos = emprestimoDAO.listar();
				novo();
				Messages.addGlobalInfo("Empréstimo salvo com sucesso");

			}
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar novo empréstimo!");
			e.printStackTrace();
		}
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public List<Integer> getQuantidadeDeParcelas() {
		return quantidadeDeParcelas;
	}

	public void setQuantidadeDeParcelas(List<Integer> quantidadeDeParcelas) {
		this.quantidadeDeParcelas = quantidadeDeParcelas;
	}

}
