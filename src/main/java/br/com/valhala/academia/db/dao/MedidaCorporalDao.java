package br.com.valhala.academia.db.dao;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

@Named
public class MedidaCorporalDao extends DaoBase<MedidaCorporal, Long> {

	public MedidaCorporalDao() {
		this.classePersistente = MedidaCorporal.class;
	}

	public Collection<MedidaCorporal> obtemTodasAluno(Long idAluno) {

		final String jpql = "SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao ASC, m.id ASC";

		TypedQuery<MedidaCorporal> query = em.createQuery(jpql, MedidaCorporal.class);
		query.setParameter("id", idAluno);

		return query.getResultList();

	}

	public Optional<MedidaCorporal> recuperaUltimaMedicao(Aluno aluno) {

		final String jpql = "SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao DESC, m.id DESC";

		TypedQuery<MedidaCorporal> query = em.createQuery(jpql, MedidaCorporal.class);
		query.setParameter("id", aluno.getId());

		return query.getResultList().stream().findFirst();

	}
}
