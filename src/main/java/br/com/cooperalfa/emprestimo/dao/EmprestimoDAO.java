package br.com.cooperalfa.emprestimo.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.cooperalfa.emprestimo.entidade.Emprestimo;
import br.com.cooperalfa.emprestimo.entidade.Funcionario;
import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class EmprestimoDAO extends GenericDAO<Emprestimo> {

	public Emprestimo buscarFuncionario(Funcionario codigoFuncionario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
		    CriteriaQuery<Emprestimo> query = builder.createQuery(Emprestimo.class);
		    Root<Emprestimo> u = query.from(Emprestimo.class);
		    query.select(u);
		    query.where(builder.equal(u.get("funcionario"), codigoFuncionario), builder.equal(u.get("status"),"ATIVO"));
		    
		    TypedQuery<Emprestimo> typedQuery = sessao.createQuery(query);
		    Emprestimo emprestimo = typedQuery.getSingleResult();
		    return emprestimo;
		}catch (NoResultException e) {
//			Messages.addGlobalInfo("Funcionário não possui débitos com o setor financeiro!");
			e.printStackTrace();
			return null;
		}	

	}

}
