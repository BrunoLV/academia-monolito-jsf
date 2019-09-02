package br.com.valhala.academia.web.controllers.medida_corporal;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.servicos.ServicoAluno;
import br.com.valhala.academia.servicos.ServicoMedidaCorporal;
import br.com.valhala.academia.web.controllers.BaseController;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MedidaCorporalController extends BaseController implements Serializable {

    private static final long serialVersionUID = 1L;
	
    private MedidaCorporal medidaCorporal;
    private Aluno aluno;

    private Long idAluno;
    private Long idMedidaCorporal;

    @Inject
    private ServicoAluno servicoAluno;

    @Inject
    private ServicoMedidaCorporal servicoMedidaCorporal;

    public void carregaDados() {

        if (idAluno != null) {
            aluno = servicoAluno.buscaPorId(idAluno);
        }

        if (idMedidaCorporal != null) {
            medidaCorporal = servicoMedidaCorporal.buscaPorId(idMedidaCorporal);
        }

    }

    @PostConstruct
    public void inicializa() {
        medidaCorporal = new MedidaCorporal();
    }

    public String salva() {
        servicoMedidaCorporal.salva(medidaCorporal, aluno);
        return "/ui/alunos/alunos.xhtml?faces-redirect=true";
    }

    public MedidaCorporal getMedidaCorporal() {
        return medidaCorporal;
    }

    public void setMedidaCorporal(MedidaCorporal medidaCorporal) {
        this.medidaCorporal = medidaCorporal;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdMedidaCorporal() {
        return idMedidaCorporal;
    }

    public void setIdMedidaCorporal(Long idMedidaCorporal) {
        this.idMedidaCorporal = idMedidaCorporal;
    }
}
