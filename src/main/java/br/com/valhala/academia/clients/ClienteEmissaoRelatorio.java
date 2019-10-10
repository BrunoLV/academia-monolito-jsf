package br.com.valhala.academia.clients;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.clients.wrapper.RequisicaoRelatorio;

@Named
public class ClienteEmissaoRelatorio implements Serializable {

    private static final Logger LOG = LogManager.getLogger(ClienteEmissaoRelatorio.class);

    private static final long serialVersionUID = 1L;

    private String urlEmissaoRelatorio;

    public ClienteEmissaoRelatorio() {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("client.properties")) {
            props.load(stream);
            urlEmissaoRelatorio = props.getProperty("emissor.relatorio.url");
        } catch (IOException e) {
            LOG.warn("[construtor] Ocorreu erro para inicializar a classe. Erro: " + e.getMessage(), e);
        }
    }

    public Relatorio emitiRelatorio(RequisicaoRelatorio requisicao) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlEmissaoRelatorio).path("/");
        Entity<RequisicaoRelatorio> entity = Entity.entity(requisicao, MediaType.APPLICATION_JSON);

        try (Response response = target.request(MediaType.APPLICATION_JSON).post(entity)) {
            return response.readEntity(Relatorio.class);
        }

    }

}
