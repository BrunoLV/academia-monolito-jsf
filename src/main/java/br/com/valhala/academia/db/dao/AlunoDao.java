package br.com.valhala.academia.db.dao;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.valhala.academia.db.modelo.Aluno;

@Named
public class AlunoDao extends DaoBase<Aluno, Long> {

	public AlunoDao() {
		this.classePersistente = Aluno.class;
	}

	public Aluno buscaPorIdComEnderecos(final Long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Aluno> criteriaQuery = cb.createQuery(classePersistente);

		Root<Aluno> from = criteriaQuery.from(classePersistente);
		from.fetch("telefones", JoinType.LEFT);
		from.fetch("enderecos", JoinType.LEFT);

		criteriaQuery.where(cb.equal(from.get("id"), id));

		TypedQuery<Aluno> query = em.createQuery(criteriaQuery);
		Aluno aluno = query.getSingleResult();
		return aluno;
	}

}
