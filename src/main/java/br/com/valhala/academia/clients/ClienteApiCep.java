package br.com.valhala.academia.clients;

import java.io.Serializable;

import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.valhala.academia.clients.wrapper.EnderecoApiCep;

@Named
public class ClienteApiCep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public EnderecoApiCep obtemEndereco(String cep) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8081/api-cep");
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
		
		ApiCep apicep = rtarget.proxy(ApiCep.class);
		
		return apicep.buscaCep(cep);
		
	}

}
