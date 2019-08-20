package br.com.valhala.academia.web.controllers.aluno;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.db.modelo.Aluno;
import br.com.valhala.academia.db.modelo.enums.EnumSituacaoAluno;
import br.com.valhala.academia.servicos.ServicoAluno;

@Named
@ViewScoped
public class ListaAlunoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno alunoSelecionado;

	@Inject
	private ServicoAluno servico;

	private Collection<Aluno> alunos;

	private StreamedContent arquivo;

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public String defineEstiloSituacao(EnumSituacaoAluno situacao) {
		String estilo = "label label-success";
		switch (situacao) {
		case ATIVO:
			estilo = "label label-success";
			break;
		case INATIVO:
			estilo = "label label-danger";
			break;
		case INADIMPLENTE:
			estilo = "label label-warning";
			break;
		default:
			break;
		}
		return estilo;
	}

	public String edita(Aluno aluno) {
		String url = "/ui/alunos/aluno.xhtml?faces-redirect=true&id=" + aluno.getId();
		return url;
	}

	public void exclui(Aluno aluno) {
		servico.exclui(aluno);
		alunos.remove(aluno);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso", null));
	}

	public void excluiAluno() {
		servico.exclui(alunoSelecionado);
		alunos.remove(alunoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso", null));
		PrimeFaces.current().executeScript("$('#modal-default').modal('hide');");
	}

	public Collection<Aluno> getAlunos() {
		return alunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	@PostConstruct
	public void inicializa() {
		alunos = servico.buscaTodos();
	}

	public void preparaExclusao(Aluno aluno) {
		alunoSelecionado = aluno;
		PrimeFaces.current().executeScript("$('#modal-default').modal('show');");
	}

	public void setAlunos(Collection<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public void valida() {
	}

	public void imprimeDetalhesAlunos(Long id) {
		Relatorio relatorio = this.servico.emitiRelatorioDetalheAluno(id);
		if (relatorio != null) {
			arquivo = new DefaultStreamedContent(new ByteArrayInputStream(relatorio.getArquivo()),
					relatorio.getFormato(), relatorio.getNomeArquivo());
		}

	}

}
