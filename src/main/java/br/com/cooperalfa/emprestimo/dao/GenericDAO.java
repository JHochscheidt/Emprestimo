package br.com.cooperalfa.emprestimo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cooperalfa.emprestimo.util.HibernateUtil;

public class GenericDAO<Entidade> {

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if(transacao != null) transacao.rollback();
			System.err.println("Erro na transação... " + e);
			throw e;
		}finally {
			sessao.close();
		}
	}
	
}
