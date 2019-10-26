package br.com.valhala.academia.db.dao;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Named;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

@Named
public class MedidaCorporalDao extends DaoBase<MedidaCorporal, Long> {

	public MedidaCorporalDao() {
		this.classePersistente = MedidaCorporal.class;
	}

	public Collection<MedidaCorporal> obtemTodasAluno(Long idAluno) {

		Collection<MedidaCorporal> medidas = em.createQuery(
				"SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao ASC, m.id ASC",
				MedidaCorporal.class).setParameter("id", idAluno).getResultList();

		return medidas;

	}

	public Optional<MedidaCorporal> recuperaUltimaMedicao(Aluno aluno) {

		return em.createQuery(
				"SELECT m FROM MedidaCorporal m WHERE m.aluno.id = :id ORDER BY m.dataMedicao DESC, m.id DESC",
				MedidaCorporal.class).setParameter("id", aluno.getId()).getResultList().stream().findFirst();

	}
}
