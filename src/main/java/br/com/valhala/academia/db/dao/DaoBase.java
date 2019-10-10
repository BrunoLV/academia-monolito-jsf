package br.com.valhala.academia.db.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class DaoBase<E, I> {

    protected Class<E> classePersistente;

    @Inject
    protected EntityManager em;

    public void atualiza(E entidade) {
        em.merge(entidade);
    }

    public E buscaPorId(I id) {
        return em.find(classePersistente, id);
    }

    public Collection<E> buscaTodos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(classePersistente);
        TypedQuery<E> tq = em.createQuery(cq.select(cq.from(classePersistente)));
        Collection<E> lista = tq.getResultList();
        return lista;
    }

    public void exclui(E entidade) {
        em.remove(entidade);
    }

    public void salva(E entidade) {
        em.persist(entidade);
    }

}
