package br.com.valhala.academia.servicos;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.db.dao.EstadoDao;
import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;

@Named
public class EstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoDao dao;

	public Estado buscaEstadoPorUFComMunicipios(final EnumUnidadeFederacao uf) {
		Estado estado = dao.buscaEstadoPorUFComMunicipios(uf);
		return estado;
	}

}
