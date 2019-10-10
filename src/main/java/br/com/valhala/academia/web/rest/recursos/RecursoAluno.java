package br.com.valhala.academia.web.rest.recursos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.valhala.academia.db.dao.MedidaCorporalDao;
import br.com.valhala.academia.modelo.MedidaCorporal;
import br.com.valhala.academia.web.rest.dto.MedidaDto;

@Path("/aluno")
public class RecursoAluno {

    @Inject
    private MedidaCorporalDao medidaCorporalDao;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @GET
    @Path("/{id}/medidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MedidaDto> obtemMedidasAluno(@PathParam("id") Long idAluno) {

        final Collection<MedidaCorporal> medidas = medidaCorporalDao.obtemTodasAluno(idAluno);

        return medidas.stream().map(m -> MedidaDto.builder().comAbdomen(m.getAbdomen()).comAltura(m.getAltura())
                .comAntebracoDireito(m.getAntebracoDireito()).comAntebracoEsquerdo(m.getAntebracoEsquerdo())
                .comBracoDireito(m.getBracoDireito()).comBracoEsquerdo(m.getBracoEsquerdo()).comBusto(m.getBusto())
                .comCintura(m.getCintura()).comCoxaDireita(m.getCoxaDireita()).comCoxaEsquerda(m.getCoxaEsquerda())
                .comDataMedicao(format.format(m.getDataMedicao())).comPanturrilhaDireita(m.getPanturrilhaDireita())
                .comPanturrilhaEsquerda(m.getPanturrilhaEsquerda()).comPescoco(m.getPescoco()).comPeso(m.getPeso())
                .comQuadril(m.getQuadril()).comToraxInferior(m.getToraxInferior())
                .comToraxSuperior(m.getToraxSuperior()).build()).collect(Collectors.toList());

    }

}
