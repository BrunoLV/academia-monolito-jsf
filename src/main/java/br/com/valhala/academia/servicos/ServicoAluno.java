package br.com.valhala.academia.servicos;

import br.com.valhala.academia.clients.ClienteEmissaoRelatorio;
import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.clients.wrapper.RequisicaoRelatorio;
import br.com.valhala.academia.db.dao.AlunoDao;
import br.com.valhala.academia.db.interceptores.InterceptadorTransacao;
import br.com.valhala.academia.db.interceptores.Transacional;
import br.com.valhala.academia.modelo.Aluno;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.Base64;
import java.util.Collection;

@Named
@Interceptors({InterceptadorTransacao.class})
public class ServicoAluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AlunoDao dao;

    @Inject
    private ClienteEmissaoRelatorio clienteEmissaoRelatorio;

    public Aluno buscaPorId(Long id) {
        return dao.buscaPorIdComEnderecos(id);
    }

    public Collection<Aluno> buscaTodos() {
        return dao.buscaTodos();
    }

    @Transacional
    public void exclui(Aluno aluno) {
        Aluno alunoBanco = dao.buscaPorId(aluno.getId());
        if (alunoBanco == null)
            return;
        dao.exclui(alunoBanco);
    }

    @Transacional
    public void salva(Aluno aluno) {
        if (aluno.getId() == null) {
            dao.salva(aluno);
        } else {
            dao.atualiza(aluno);
        }
    }

    public Relatorio emiteRelatorioDetalheAluno(Long id) {
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
