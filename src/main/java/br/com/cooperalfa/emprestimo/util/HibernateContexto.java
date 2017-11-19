package br.com.cooperalfa.emprestimo.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getFabricaDeSessoes().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent evento) {
		HibernateUtil.getFabricaDeSessoes();
	}
	
}
