package br.com.valhala.academia.servicos;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.db.dao.TipoLogradouroDao;
import br.com.valhala.academia.modelo.TipoLogradouro;

@Named
public class TipoLogradouroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoLogradouroDao dao;

	public Collection<TipoLogradouro> listaTiposLogradouros() {
		return dao.buscaTodos();
	}

}
