package br.com.cooperalfa.emprestimo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.cooperalfa.emprestimo.dao.ParcelaDAO;
import br.com.cooperalfa.emprestimo.entidade.Parcela;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ParcelaBean implements Serializable {
	private Parcela parcela;
	private List<Parcela> parcelas;
	private Date dataInicio;
	private Date dataFim;
	
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public List<Parcela> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ParcelaDAO parcelaDAO = new ParcelaDAO();
			parcelas = parcelaDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar exibir parcelas");
			e.printStackTrace();
		}
	}
	
	public void listarPorDatas() {
		try {
			ParcelaDAO parcelaDAO = new ParcelaDAO();
			parcelas = parcelaDAO.listarPorDatas(dataInicio,dataFim);
		}catch(RuntimeException e) {
			Messages.addGlobalError("Não foi possível buscar pelas datas");
			e.printStackTrace();
		}
	}
	
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}	
}
