package br.com.valhala.academia.db;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("academia-unit");

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        EntityManagerProducer.emf = emf;
    }

    public void fechaEntityManager(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

    @Produces
    @RequestScoped
    public EntityManager produzEntityManager() {
        return emf.createEntityManager();
    }

}
