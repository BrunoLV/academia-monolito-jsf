package br.com.valhala.academia.web.controllers.aluno;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.Visibility;

import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.modelo.enums.EnumSituacaoAluno;
import br.com.valhala.academia.servicos.AlunoService;
import br.com.valhala.academia.servicos.MedidaCorporalService;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class ListaAlunoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno alunoSelecionado;
	
	private MedidaCorporal medidaSelecionada;

	private Collection<Aluno> alunos;
	
	private StreamedContent arquivo;

	@Inject
	private AlunoService alunoService;

	@Inject
	private MedidaCorporalService servicoMedidaCorporal;

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
		alunoService.exclui(aluno);
		alunos.remove(aluno);
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_INFO, Arrays.asList("Excluído com sucesso!"));
	}

	public void excluiAluno() {
		alunoService.exclui(alunoSelecionado);
		alunos.remove(alunoSelecionado);
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_INFO, Arrays.asList("Excluído com sucesso!"));
		executaScript("$('#modal-default').modal('hide')");
	}
	
	public void excluiMedida(Aluno aluno) {
		alunoSelecionado = aluno;
		medidaSelecionada = aluno.getUltimaMedicao();
		executaScript("$('#modal-medicao').modal('show')");
	}
	
	public void excluiMedida() {
		servicoMedidaCorporal.exclui(medidaSelecionada);
		alunoSelecionado.setUltimaMedicao(servicoMedidaCorporal.recuperaUltimaMedicao(alunoSelecionado).orElse(new MedidaCorporal()));
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_INFO, Arrays.asList("Excluído com sucesso!"));
		executaScript("$('#modal-medicao').modal('hide')");
	}

	public Collection<Aluno> getAlunos() {
		return alunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public void imprimeDetalhesAlunos(Long id) {
		Relatorio relatorio = this.alunoService.emiteRelatorioDetalheAluno(id);
		if (relatorio != null) {
			arquivo = new DefaultStreamedContent(new ByteArrayInputStream(relatorio.getArquivo()),
					relatorio.getFormato(), relatorio.getNomeArquivo());
		}
	}

	@PostConstruct
	public void inicializa() {
		alunos = alunoService.buscaTodos();
	}

	public void onToggleRow(ToggleEvent event) {
		Aluno aluno = (Aluno) event.getData();
		aluno.setUltimaMedicao(event.getVisibility() == Visibility.VISIBLE
				? servicoMedidaCorporal.recuperaUltimaMedicao(aluno).orElse(new MedidaCorporal())
				: new MedidaCorporal());
	}

	public void preparaExclusao(Aluno aluno) {
		alunoSelecionado = aluno;
		executaScript("$('#modal-default').modal('show')");
	}

	public void setAlunos(Collection<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

}
