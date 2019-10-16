package br.com.valhala.academia.testes.produtores;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ProdutorEntityManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public void fechaEntityManager(@Disposes EntityManager em) {
		if (em.isOpen()) {
			System.out.println("Encerrando entity manager.");
			em.close();
		}
	}

	@Produces
	@RequestScoped
	@Default
	public EntityManager produzEntityManager() {
		System.out.println("Produzindo entity manager");
		return Persistence.createEntityManagerFactory("academia-unit", obtemPropriedades()).createEntityManager();
	}
	
	private Properties obtemPropriedades() {
		Properties props = new Properties();

		ResourceBundle rb = ResourceBundle.getBundle("db-testes");
		Enumeration<String> keys = rb.getKeys();
		
		for (Iterator<String> iterator = keys.asIterator(); iterator.hasNext();) {
			String chave = iterator.next();
			props.put(chave, rb.getObject(chave));
		}
		
		return props;
	}

}
