package br.com.valhala.academia.web.controllers.evolucao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.servicos.ServicoAluno;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class EvolucaoController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAluno;
	
	private Aluno aluno;
	
	@Inject
	private ServicoAluno servicoAluno;
	
	public void carregaDados() {
		aluno = servicoAluno.buscaPorId(idAluno);
		executaScript("evolucao.controller.renderizaGraficoEvolucao(" + idAluno + ");");
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

}
