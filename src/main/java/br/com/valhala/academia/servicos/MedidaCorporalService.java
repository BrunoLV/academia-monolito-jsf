package br.com.valhala.academia.servicos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.db.dao.MedidaCorporalDao;
import br.com.valhala.academia.db.interceptores.Transacional;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;

@Named
public class MedidaCorporalService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MedidaCorporalDao dao;

	public MedidaCorporal buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public Optional<MedidaCorporal> recuperaUltimaMedicao(Aluno aluno) {
		return dao.recuperaUltimaMedicao(aluno);
	}

	@Transacional
	public void salva(MedidaCorporal medidaCorporal, Aluno aluno) {
		medidaCorporal.setAluno(aluno);
		if (medidaCorporal.getId() == null) {
			dao.salva(medidaCorporal);
		} else {
			dao.atualiza(medidaCorporal);
		}
	}

	@Transacional
	public void exclui(MedidaCorporal medida) {
		MedidaCorporal medidaCorporal = dao.buscaPorId(medida.getId());
		if (medidaCorporal == null) {
			return;
		}
		dao.exclui(medidaCorporal);
	}

	public Collection<MedidaCorporal> obtemTodasMedidas(Aluno aluno) {
		Collection<MedidaCorporal> medidas = dao.obtemTodasAluno(aluno.getId());
		return medidas;
	}
}
