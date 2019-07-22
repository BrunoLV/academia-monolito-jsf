package br.com.valhala.academia.db.dao;

import javax.inject.Named;

import br.com.valhala.academia.db.modelo.TipoLogradouro;

@Named
public class TipoLogradouroDao extends DaoBase<TipoLogradouro, Long> {

	public TipoLogradouroDao() {
		this.classePersistente = TipoLogradouro.class;
	}

}
