package br.com.valhala.academia.web.controllers.medida_corporal;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.servicos.AlunoService;
import br.com.valhala.academia.servicos.MedidaCorporalService;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class MedidaCorporalController extends BaseController implements Serializable {

    private static final long serialVersionUID = 1L;

    private MedidaCorporal medidaCorporal;
    private Aluno aluno;

    private Long idAluno;
    private Long idMedidaCorporal;

    @Inject
    private AlunoService alunoService;

    @Inject
    private MedidaCorporalService medidaCorporalService;

    public void carregaDados() {

        if (idAluno != null) {
            aluno = alunoService.buscaPorId(idAluno);
        }

        if (idMedidaCorporal != null) {
            medidaCorporal = medidaCorporalService.buscaPorId(idMedidaCorporal);
        }

    }

    public Long getIdAluno() {
        return idAluno;
    }

    public Long getIdMedidaCorporal() {
        return idMedidaCorporal;
    }

    public MedidaCorporal getMedidaCorporal() {
        return medidaCorporal;
    }

    @PostConstruct
    public void inicializa() {
        medidaCorporal = new MedidaCorporal();
    }

    public String salva() {
        medidaCorporalService.salva(medidaCorporal, aluno);
        return "/ui/alunos/alunos.xhtml?faces-redirect=true";
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdMedidaCorporal(Long idMedidaCorporal) {
        this.idMedidaCorporal = idMedidaCorporal;
    }

    public void setMedidaCorporal(MedidaCorporal medidaCorporal) {
        this.medidaCorporal = medidaCorporal;
    }
}
