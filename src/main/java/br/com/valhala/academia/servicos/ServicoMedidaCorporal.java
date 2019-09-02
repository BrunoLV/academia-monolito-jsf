package br.com.valhala.academia.servicos;

import br.com.valhala.academia.db.dao.MedidaCorporalDao;
import br.com.valhala.academia.db.interceptores.Transacional;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named
public class ServicoMedidaCorporal implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Inject
    private MedidaCorporalDao dao;

    @Transacional
    public void salva(MedidaCorporal medidaCorporal, Aluno aluno) {
        medidaCorporal.setAluno(aluno);
        if (medidaCorporal.getId() == null) {
            dao.salva(medidaCorporal);
        } else {
            dao.atualiza(medidaCorporal);
        }
    }

    public Optional<MedidaCorporal> recuperaUltimaMedicao(Aluno aluno) {
        return dao.recuperaUltimaMedicao(aluno);
    }

    public MedidaCorporal buscaPorId(Long id) {
        return dao.buscaPorId(id);
    }
}
