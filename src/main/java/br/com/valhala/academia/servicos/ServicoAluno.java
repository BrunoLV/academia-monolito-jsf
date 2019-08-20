package br.com.valhala.academia.servicos;

import java.io.Serializable;
import java.util.Base64;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.valhala.academia.clients.ClienteEmissaoRelatorio;
import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.clients.wrapper.RequisicaoRelatorio;
import br.com.valhala.academia.db.dao.AlunoDao;
import br.com.valhala.academia.db.interceptores.InterceptadorTransacao;
import br.com.valhala.academia.db.interceptores.Transacional;
import br.com.valhala.academia.db.modelo.Aluno;
import br.com.valhala.academia.db.modelo.Telefone;

@Named
@Interceptors({ InterceptadorTransacao.class })
public class ServicoAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoDao dao;

	@Inject
	private ClienteEmissaoRelatorio clienteEmissaoRelatorio;

	@Transacional
	public void atualiza(Aluno aluno) {
		dao.atualiza(aluno);
	}

	public Aluno buscaPorId(Long id) {
		return dao.buscaPorIdComEnderecos(id);
	}

	public Collection<Aluno> buscaTodos() {
		return dao.buscaTodos();
	}

	@Transacional
	public void exclui(Aluno aluno) {
		dao.exclui(dao.buscaPorId(aluno.getId()));
	}

	@Transacional
	public void salva(Aluno aluno) {

		if (CollectionUtils.isNotEmpty(aluno.getTelefones())) {
			for (Telefone telefone : aluno.getTelefones()) {
				telefone.setAluno(aluno);
			}
		}

		if (aluno.getId() == null) {
			dao.salva(aluno);
		} else {
			dao.atualiza(aluno);
		}
	}

	public Relatorio emitiRelatorioDetalheAluno(Long id) {
		try {
			Aluno aluno = dao.buscaPorIdComEnderecos(id);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(aluno);

			RequisicaoRelatorio requisicao = new RequisicaoRelatorio.Builder().nomeRelatorio("detalhe-aluno")
					.json(Base64.getEncoder().encodeToString(json.getBytes())).build();

			Relatorio relatorio = clienteEmissaoRelatorio.emitiRelatorio(requisicao);
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
