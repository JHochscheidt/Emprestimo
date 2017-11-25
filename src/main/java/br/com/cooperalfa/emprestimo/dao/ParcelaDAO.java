package br.com.cooperalfa.emprestimo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.cooperalfa.emprestimo.entidade.Emprestimo;
import br.com.cooperalfa.emprestimo.entidade.Parcela;
import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class ParcelaDAO extends GenericDAO<Parcela> {

	public List<Parcela> listarParcelas(Emprestimo emprestimo) {
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

	public List<Parcela> listarPorDatas(Date inicio, Date fim) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Parcela> query = builder.createQuery(Parcela.class);
			Root<Parcela> u = query.from(Parcela.class);
			query.select(u);

			query.where(builder.between(u.get("dataVencimento"), inicio, fim), 
						builder.notEqual(u.get("valorParcela"), u.get("valorPago")));

			TypedQuery<Parcela> typedQuery = sessao.createQuery(query);
			List<Parcela> parcelas = typedQuery.getResultList();
			return parcelas;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

	public int buscarParcelasBaixadas(Emprestimo emprestimo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {

			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Parcela> query = builder.createQuery(Parcela.class);
			Root<Parcela> u = query.from(Parcela.class);
			query.select(u);

			Predicate codEmpEqual = builder.equal(u.get("emprestimo"), emprestimo);
			Predicate valorParcelaNotEqualValorPago = builder.notEqual(u.get("valorParcela"), u.get("valorPago"));

			query.where(codEmpEqual, valorParcelaNotEqualValorPago);

			TypedQuery<Parcela> typedQuery = sessao.createQuery(query);
			List<Parcela> parcelas = typedQuery.getResultList();

			if (parcelas.isEmpty()) {
				parcelas.clear();
				return 0;
			} else {
				parcelas.clear();
				return 1;
			}

		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
