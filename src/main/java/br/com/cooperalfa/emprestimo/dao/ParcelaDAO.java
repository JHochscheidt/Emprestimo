package br.com.cooperalfa.emprestimo.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.cooperalfa.emprestimo.entidade.Emprestimo;
import br.com.cooperalfa.emprestimo.entidade.Parcela;
import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class ParcelaDAO extends GenericDAO<Parcela>{

	
	public List<Parcela> listarParcelas(Emprestimo emprestimo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
		    CriteriaQuery<Parcela> query = builder.createQuery(Parcela.class);
		    Root<Parcela> u = query.from(Parcela.class);
		    query.select(u);
		    query.where(builder.equal(u.get("emprestimo"), emprestimo));
		    
		    TypedQuery<Parcela> typedQuery = sessao.createQuery(query);
		    List<Parcela> parcelas = typedQuery.getResultList();
		    return parcelas;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
	}
}
