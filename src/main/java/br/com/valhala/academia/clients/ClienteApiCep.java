package br.com.valhala.academia.clients;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.valhala.academia.clients.wrapper.EnderecoApiCep;

@Named
public class ClienteApiCep implements Serializable {

	private static final Logger LOG = LogManager.getLogger(ClienteApiCep.class);

	private static final long serialVersionUID = 1L;

	private String urlApiCep;

	public ClienteApiCep() {
		Properties props = new Properties();
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("client.properties")) {
			props.load(stream);
			urlApiCep = props.getProperty("api.cep.url");
		} catch (IOException e) {
			LOG.warn("[construtor] Ocorreu erro para iniciar a classe. Erro: " + e.getMessage(), e);
		}
	}

	public EnderecoApiCep buscaCep(String cep) {

		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(urlApiCep);
			ResteasyWebTarget rtarget = (ResteasyWebTarget) target;

			ApiCep apicep = rtarget.proxy(ApiCep.class);

			EnderecoApiCep endereco = apicep.buscaCep(cep);

			return endereco;
		} catch (Exception e) {
			LOG.error("[buscaCep] Ocorreu erro para executar busca do CEP " + cep + ". Erro: " + e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

}
