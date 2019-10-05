package br.com.valhala.academia.web.controllers.evolucao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.servicos.ServicoAluno;
import br.com.valhala.academia.servicos.ServicoMedidaCorporal;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class EvolucaoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAluno;

	private Aluno aluno;
	
	private Collection<MedidaCorporal> medidas = Collections.emptyList();

	public Collection<MedidaCorporal> getMedidas() {
		return medidas;
	}

	public void setMedidas(Collection<MedidaCorporal> medidas) {
		this.medidas = medidas;
	}

	@Inject
	private ServicoAluno servicoAluno;
	
	@Inject
	private ServicoMedidaCorporal servicoMedidaCorporal;

	public void carregaDados() {
		aluno = servicoAluno.buscaPorId(idAluno);
		medidas = servicoMedidaCorporal.obtemTodasMedidas(aluno);
		executaScript("evolucao.controller.renderizaGraficoEvolucao(" + idAluno + ");");
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

}
