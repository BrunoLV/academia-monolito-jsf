package br.com.valhala.academia.db.dao;

import javax.inject.Named;

import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;

@Named
public class EstadoDao extends DaoBase<Estado, Long> {

	public EstadoDao() {
		this.classePersistente = Estado.class;
	}

	public Estado buscaEstadoPorUFComMunicipios(final EnumUnidadeFederacao uf) {

		Estado estado = em.createQuery("SELECT e FROM Estado e JOIN FETCH e.municipios WHERE e.uf = :uf", Estado.class)
						  .setParameter("uf", uf)
						  .getSingleResult();

		return estado;

	}

}
