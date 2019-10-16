package br.com.valhala.academia.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

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
			em.close();
		}
	}

	@Produces
	@RequestScoped
	@Default
	public EntityManager produzEntityManager() {
		return Persistence.createEntityManagerFactory("academia-unit", obtemPropriedades()).createEntityManager();
	}
	
	private Properties obtemPropriedades() {
		Properties props = new Properties();
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			props.load(stream);
		} catch (IOException e) {
		}
		return props;
	}

}
