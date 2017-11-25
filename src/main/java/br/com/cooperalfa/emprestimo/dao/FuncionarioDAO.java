package br.com.cooperalfa.emprestimo.dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.cooperalfa.emprestimo.entidade.Funcionario;
import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{
	
	public Funcionario buscarPorCpf(String cpf) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
		    CriteriaQuery<Funcionario> query = builder.createQuery(Funcionario.class);
		    Root<Funcionario> u = query.from(Funcionario.class);
		    query.select(u);
		    query.where(builder.equal(u.get("cpf"), cpf));
		    
		    TypedQuery<Funcionario> typedQuery = sessao.createQuery(query);
		    Funcionario funcionario = typedQuery.getSingleResult();
		    return funcionario;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
}
