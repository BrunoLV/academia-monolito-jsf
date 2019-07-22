package br.com.valhala.academia.clients;

import java.io.Serializable;

import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.clients.wrapper.RequisicaoRelatorio;

@Named
public class ClienteEmissaoRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	public Relatorio emitiRelatorio(RequisicaoRelatorio requisicao) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/relatorio").path("/");
		
		Invocation.Builder invokeBuilder = target.request(MediaType.APPLICATION_JSON);
		
		Response response = invokeBuilder.post(Entity.entity(requisicao, MediaType.APPLICATION_JSON));

		Relatorio relatorio = response.readEntity(Relatorio.class);
		return relatorio;
	}

}
