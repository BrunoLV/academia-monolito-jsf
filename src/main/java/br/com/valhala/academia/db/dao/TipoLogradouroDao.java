package br.com.valhala.academia.db.dao;

import br.com.valhala.academia.modelo.TipoLogradouro;

import javax.inject.Named;

@Named
public class TipoLogradouroDao extends DaoBase<TipoLogradouro, Long> {

    public TipoLogradouroDao() {
        this.classePersistente = TipoLogradouro.class;
    }

}
