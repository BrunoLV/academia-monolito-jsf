package br.com.valhala.academia.db.dao;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;

@Named
public class EstadoDao extends DaoBase<Estado, Long> {

    public EstadoDao() {
        this.classePersistente = Estado.class;
    }

    public Estado buscaEstadoPorUFComMunicipios(final EnumUnidadeFederacao uf) {

        final String jpql = "SELECT e FROM Estado e JOIN FETCH e.municipios WHERE e.uf = :uf";

        final TypedQuery<Estado> query = em.createQuery(jpql, Estado.class);
        query.setParameter("uf", uf);

        Estado estado = query.getSingleResult();

        return estado;

    }

}
