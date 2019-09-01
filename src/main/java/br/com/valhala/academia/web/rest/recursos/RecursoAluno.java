package br.com.valhala.academia.web.rest.recursos;

import br.com.valhala.academia.db.dao.MedidaCorporalDao;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.web.rest.dto.MedidaDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Path("/aluno")
public class RecursoAluno {

    @Inject
    private MedidaCorporalDao medidaCorporalDao;

    @GET
    @Path("/{id}/medidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MedidaDto> obtemMedidasAluno(@PathParam("id") Long idAluno) {
        final Collection<MedidaCorporal> medidas = medidaCorporalDao.obtemTodasAluno(idAluno);
        final List<MedidaDto> colecao = medidas
                .stream()
                .map(m -> new MedidaDto(m.getDataMedicao()
                        , m.getAltura()
                        , m.getPeso()
                        , m.getPescoco()
                        , m.getToraxSuperior()
                        , m.getToraxInferior()
                        , m.getBusto()
                        , m.getCintura()
                        , m.getAbdomen()
                        , m.getQuadril()
                        , m.getCoxaDireita()
                        , m.getCoxaEsquerda()
                        , m.getPanturrilhaDireita()
                        , m.getPanturrilhaEsquerda()
                        , m.getBracoDireito()
                        , m.getBracoEsquerdo()
                        , m.getAntebracoDireito()
                        , m.getAntebracoEsquerdo()))
                .collect(Collectors.toList());
        return colecao;
    }

}
