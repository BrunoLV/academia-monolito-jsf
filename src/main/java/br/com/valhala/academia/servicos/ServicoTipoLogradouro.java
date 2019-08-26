package br.com.valhala.academia.servicos;

import br.com.valhala.academia.db.dao.TipoLogradouroDao;
import br.com.valhala.academia.modelo.TipoLogradouro;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
public class ServicoTipoLogradouro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TipoLogradouroDao dao;

    public Collection<TipoLogradouro> listaTiposLogradouros() {
        return dao.buscaTodos();
    }

}
