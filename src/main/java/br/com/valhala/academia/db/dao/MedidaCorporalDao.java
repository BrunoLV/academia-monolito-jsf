package br.com.valhala.academia.db.dao;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Named
public class MedidaCorporalDao extends DaoBase<MedidaCorporal, Long> {

    public MedidaCorporalDao() {
        this.classePersistente = MedidaCorporal.class;
    }

    public Optional<MedidaCorporal> recuperaUltimaMedicao(Aluno aluno) {

        final String jpql = "SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao, m.id DESC";

        TypedQuery<MedidaCorporal> query = em.createQuery(jpql, MedidaCorporal.class);
        query.setParameter("id", aluno.getId());

        return query.getResultList().stream().findFirst();

    }

    public Collection<MedidaCorporal> obtemTodasAluno(Long idAluno) {

        final String jpql = "SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao, m.id ASC";

        TypedQuery<MedidaCorporal> query = em.createQuery(jpql, MedidaCorporal.class);
        query.setParameter("id", idAluno);

        return query.getResultList();

    }
}
