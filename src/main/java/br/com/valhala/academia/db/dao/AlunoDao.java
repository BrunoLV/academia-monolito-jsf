package br.com.valhala.academia.db.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

@Named
public class AlunoDao extends DaoBase<Aluno, Long> {

	public AlunoDao() {
		this.classePersistente = Aluno.class;
	}

	public Aluno buscaPorIdComTodosOsDados(final Long id) {

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

	public void deleta(Aluno aluno) {

		List<MedidaCorporal> medidas = em.createQuery("SELECT mc FROM MedidaCorporal mc WHERE mc.aluno = :aluno", MedidaCorporal.class)
										 .setParameter("aluno", aluno)
										 .getResultList();

		if (CollectionUtils.isNotEmpty(medidas)) {
			medidas.stream().forEach(mc -> em.remove(mc));
		}

		em.remove(aluno);

	}

}
