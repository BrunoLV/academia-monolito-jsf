package br.com.valhala.academia.web.controllers.aluno;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;
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
import br.com.valhala.academia.servicos.ServicoAluno;
import br.com.valhala.academia.servicos.ServicoMedidaCorporal;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class ListaAlunoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno alunoSelecionado;

	private MedidaCorporal ultimaMedida;

	@Inject
	private ServicoAluno servico;

	@Inject
	private ServicoMedidaCorporal servicoMedidaCorporal;

	private Collection<Aluno> alunos;

	private StreamedContent arquivo;

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
		adicionaMensagensInformativas(Arrays.asList("Excluído com sucesso!"));
	}

	public void excluiAluno() {
		servico.exclui(alunoSelecionado);
		alunos.remove(alunoSelecionado);
		adicionaMensagensInformativas(Arrays.asList("Excluído com sucesso!"));
		executaScript("$('#modal-default').modal('hide')");
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

	public MedidaCorporal getUltimaMedida() {
		return ultimaMedida;
	}

	public void imprimeDetalhesAlunos(Long id) {
		Relatorio relatorio = this.servico.emiteRelatorioDetalheAluno(id);
		if (relatorio != null) {
			arquivo = new DefaultStreamedContent(new ByteArrayInputStream(relatorio.getArquivo()),
					relatorio.getFormato(), relatorio.getNomeArquivo());
		}
	}

	@PostConstruct
	public void inicializa() {
		alunos = servico.buscaTodos();
	}

	public void onToggleRow(ToggleEvent event) {
		Aluno aluno = (Aluno) event.getData();
		if (event.getVisibility() == Visibility.VISIBLE) {
			ultimaMedida = servicoMedidaCorporal.recuperaUltimaMedicao(aluno).orElse(new MedidaCorporal());
		} else {
			ultimaMedida = new MedidaCorporal();
		}
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

	public void setUltimaMedida(MedidaCorporal ultimaMedida) {
		this.ultimaMedida = ultimaMedida;
	}
}
