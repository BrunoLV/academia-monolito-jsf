package br.com.valhala.academia.clients;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.valhala.academia.clients.wrapper.EnderecoApiCep;

@Path("/buscaCep")
public interface ApiCep {

	@GET
	@Path("/{cep}")
	@Produces("application/json")
	EnderecoApiCep buscaCep(@PathParam("cep") String cep);

}
