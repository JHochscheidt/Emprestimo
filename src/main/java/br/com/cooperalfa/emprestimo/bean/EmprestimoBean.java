package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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

	private List<Parcela> parcelas;
	private Parcela parcela;

	private static final BigDecimal juros = new BigDecimal("0.05");

	public void popular() {
		System.out.println();
		if (quantidadeDeParcelas == null) {
			quantidadeDeParcelas = new ArrayList<Integer>();
			quantidadeDeParcelas.add(3);
			quantidadeDeParcelas.add(6);
			quantidadeDeParcelas.add(12);
			quantidadeDeParcelas.add(24);
		}
	}

	public void exibirParcelas(ActionEvent evento) {
		try {
			emprestimo = (Emprestimo) evento.getComponent().getAttributes().get("emprestimoSelecionado");

			ParcelaDAO parcelaDAO = new ParcelaDAO();
			parcelas = parcelaDAO.listarParcelas(emprestimo);
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar parcelas!");
			e.printStackTrace();
		}
	}

	// buscar por parcelas ainda nao baixadas

	public void baixarParcela(ActionEvent evento) {
		try {
			parcela = (Parcela) evento.getComponent().getAttributes().get("parcelaSelecionada");

			ParcelaDAO parcelaDAO = new ParcelaDAO();
			if (parcela.getValorPago().compareTo(parcela.getValorParcela()) == 0) {
				Messages.addGlobalInfo("Parcela já foi baixada!");
			} else {
				parcela.setValorPago(parcela.getValorParcela());
				parcelaDAO.merge(parcela);
				Messages.addGlobalInfo("Parcela baixada!");
				// verificar se depois de baixada a parcela o emprestimo foi quitado
				if (parcelaDAO.buscarParcelasBaixadas(parcela.getEmprestimo()) == 0) {
					Emprestimo empUpdate = parcela.getEmprestimo();
					empUpdate.setStatus(Status.QUITADO);
					EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
					emprestimoDAO.merge(empUpdate);
					emprestimos = emprestimoDAO.listar();
				}
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar baixar parcela");
			e.printStackTrace();
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
			// se nao houver emprestimos ativos para o funcionario RETORNA UM
			// NoResultException
			Emprestimo emprestimoFunc = emprestimoDAO.buscarPorFuncionario(emprestimo.getFuncionario());

			// se funcionario nao possui emprestimos ativos
			if (emprestimoFunc == null) {
				// --> PODE FAZER NOVO EMPRESTIMO
				emprestimo.setStatus(Status.ATIVO);
				emprestimoDAO.merge(emprestimo);

				// e o emprestimo retornado é o que acabou de ser inserido
				emprestimoFunc = emprestimoDAO.buscarPorFuncionario(emprestimo.getFuncionario());

				// calcula juros
				BigDecimal valorEmprestimo = new BigDecimal("0.00");
				valorEmprestimo = emprestimoFunc.getValor()
						.add(emprestimoFunc.getValor().multiply(EmprestimoBean.getJuros()));
				valorEmprestimo = valorEmprestimo.divide(new BigDecimal("1.00"), 2, RoundingMode.UP);

				// calcula valor das parcelas
				BigDecimal valorParcela = new BigDecimal("0.00");
				BigDecimal qtParcelasBG = new BigDecimal(emprestimoFunc.getQuantidadeParcelas());
				valorParcela = valorEmprestimo.divide(qtParcelasBG, 2, RoundingMode.UP);

				GregorianCalendar calendario = new GregorianCalendar();
				calendario.setTime(emprestimoFunc.getPrimeiraParcela());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				int qtParcelas = emprestimoFunc.getQuantidadeParcelas();
				BigDecimal somaParcelas = new BigDecimal("0.00");

				for (int i = 0; i < qtParcelas; i++) {
					somaParcelas = somaParcelas.add(valorParcela);

					//ultima parcela e soma parcelas diferente do total
					if (i == qtParcelas - 1) {
						if (somaParcelas.compareTo(valorEmprestimo) != 0) {
							// add na ultima parcela a diferença
							valorParcela = valorParcela.add(valorEmprestimo.subtract(somaParcelas));
						}
					}
					ParcelaDAO parcelaDAO = new ParcelaDAO();
					Parcela parcela = new Parcela();
					parcela.setNumeroParcela(i + 1);
					parcela.setValorParcela(valorParcela);
					parcela.setValorPago(new BigDecimal("0.00"));
					if (i == 0) {
						parcela.setDataVencimento(emprestimoFunc.getPrimeiraParcela());
					} else {
						String dateString = dateFormat.format(calendario.getTime());
						try {
							parcela.setDataVencimento(dateFormat.parse(dateString));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					parcela.setEmprestimo(emprestimoFunc);
					parcelaDAO.merge(parcela);
					if (calendario.get(GregorianCalendar.MONTH) == 11) {
						// se mes é dezembro
						calendario.add(GregorianCalendar.YEAR, 1);
					}
					calendario.roll(GregorianCalendar.MONTH, 1);

				}
				emprestimos = emprestimoDAO.listar();
				novo();
				Messages.addGlobalInfo("Empréstimo salvo com sucesso");
			} else {
				Messages.addGlobalError("Não foi possível abrir novo empréstimo. Funcionário "
						+ emprestimo.getFuncionario().getNome() + " possui empréstimo pendente!");
				novo();
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

	public static BigDecimal getJuros() {
		return juros;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

}
