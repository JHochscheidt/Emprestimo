package br.com.cooperalfa.emprestimo.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;


import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class GenericDAO<Entidade> {
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null)
				transacao.rollback();
			System.err.println("Erro na transação... " + e);
			throw e;
		} finally {
			sessao.close();
		}
	}

	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<Entidade> consulta = builder.createQuery(classe);
			// Specify criteria root
			consulta.from(classe);
			// Execute query
			List<Entidade> resultado = sessao.createQuery(consulta).getResultList();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

	public List<Entidade> listarOrdenado(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			// Create CriteriaQuery
			CriteriaQuery<Entidade> consulta = builder.createQuery(classe);
			// Specify criteria root
			Root<Entidade> root = consulta.from(classe);
			consulta.select(root);
			consulta.orderBy(builder.asc(root.get(campoOrdenacao)));;
			TypedQuery<Entidade> typedQuery = sessao.createQuery(consulta);
			
			// Execute query
			List<Entidade> resultado = typedQuery.getResultList();
			return resultado;			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Entidade resultado = null;
		try {
			resultado = sessao.find(classe, codigo);
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null)
				transacao.rollback();
			System.err.println("Erro na transação... " + e);
			throw e;
		} finally {
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null)
				transacao.rollback();
			System.err.println("Erro na transação... " + e);
			throw e;
		} finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null)
				transacao.rollback();
			System.err.println("Erro na transação... " + e);
			throw e;
		} finally {
			sessao.close();
		}
	}

}
